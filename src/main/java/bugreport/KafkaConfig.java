package bugreport;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

@Data
@ConfigurationProperties(prefix = "bugreport")
public class KafkaConfig {
    private final String bootstrapServers;
    private final String topic;
    private final String sslEnabledProtocols;
    private final String sslKeyPassword;
    private final String sslTruststorePath;
    private final String sslKeystorePassword;
    private final String sslKeystoreType;
    private final String sslSecurityProtocol;
    private final String sslKeystorePath;
    private final String sslTruststorePassword;
    private final String sslTruststoreType;

    public Map<String, Object> getKafkaConfig() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, requireNonNull(bootstrapServers));

        if ("SSL".equals(sslSecurityProtocol)) {
            props.put("security.protocol", sslSecurityProtocol);
            props.put("ssl.enabled.protocols", requireNonNullElse(sslEnabledProtocols, "TLSv1.2,TLSv1.1,TLSv1"));

            props.put("ssl.key.password", requireNonNull(sslKeyPassword));

            props.put("ssl.keystore.location", requireNonNull(sslKeystorePath));
            props.put("ssl.keystore.password", requireNonNull(sslKeystorePassword));
            props.put("ssl.keystore.type", requireNonNullElse(sslKeystoreType, "JKS"));

            props.put("ssl.truststore.location", requireNonNull(sslTruststorePath));
            props.put("ssl.truststore.password", requireNonNull(sslTruststorePassword));
            props.put("ssl.truststore.type", requireNonNullElse(sslTruststoreType, "JKS"));
        }

        return props;
    }
}
