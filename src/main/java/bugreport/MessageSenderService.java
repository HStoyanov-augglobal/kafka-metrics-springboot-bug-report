package bugreport;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class MessageSenderService {
    public final KafkaTemplate<String, String> testKafkaTemplateWithTagsProvider;
    public final KafkaTemplate<String, String> testKafkaTemplateWithoutTagsProvider;
    public final KafkaConfig kafkaConfig;

    private final AtomicLong messageCounter = new AtomicLong();

    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.SECONDS)
    void sendMessages() {
        long currentMessage = messageCounter.getAndIncrement();
        String messageData = "value %d".formatted(currentMessage);

        testKafkaTemplateWithTagsProvider.send(kafkaConfig.getTopic(), "with-tags-provider-key_%d".formatted(currentMessage), messageData);

        testKafkaTemplateWithoutTagsProvider.send(kafkaConfig.getTopic(), "without-tags-provider-key_key %d".formatted(currentMessage), messageData);
    }
}
