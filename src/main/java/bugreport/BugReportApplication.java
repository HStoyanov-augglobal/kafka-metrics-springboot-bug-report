package bugreport;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(KafkaConfig.class)
@EnableScheduling
public class BugReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugReportApplication.class, args);
    }

    @Bean
    public KafkaTemplate<String, String> testKafkaTemplateWithTagsProvider(KafkaConfig kafkaConfig) {
        Map<String, Object> props = kafkaConfig.getKafkaConfig();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory);

        int CHOSEN_CASE = 1;
        switch (CHOSEN_CASE) {
            // CASE 1
            // Problem in this kafka template - when a tags provider is set, the spring_kafka_template metrics breaks
            // It will always be 0, the tag "topic" will not show
            case 1 -> template.setMicrometerTagsProvider(producerRecord -> Map.of("topic", producerRecord.topic()));

            // CASE 2
            // Even with a static value, still broken, tag not showing, metric stuck at 0
            case 2 -> template.setMicrometerTagsProvider(producerRecord -> Map.of("topic", "static value"));

            // CASE 3
            // If map of tags is EMPTY, the spring_kafka_template metric will work again as expected
            case 3 -> template.setMicrometerTagsProvider(producerRecord -> Map.of());
        }

        return template;
    }

    @Bean
    public KafkaTemplate<String, String> testKafkaTemplateWithoutTagsProvider(KafkaConfig kafkaConfig) {
        Map<String, Object> props = kafkaConfig.getKafkaConfig();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory);
        return template;
    }
}
