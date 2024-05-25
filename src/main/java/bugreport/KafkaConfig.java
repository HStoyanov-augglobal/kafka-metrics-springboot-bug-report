package bugreport;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

@Data
@ConfigurationProperties(prefix = "bugreport")
public class KafkaConfig {
    @Value("${bugreport_bootstrapServers:#{null}}")
    private final String bootstrapServers;
    @Value("${bugreport_topic:#{null}}")
    private final String topic;
    @Value("${bugreport_sslEnabledProtocols:#{null}}")
    private final String sslEnabledProtocols;
    @Value("${bugreport_sslKeyPassword:#{null}}")
    private final String sslKeyPassword;
    @Value("${bugreport_sslTruststorePath:#{null}}")
    private final String sslTruststorePath;
    @Value("${bugreport_sslKeystorePassword:#{null}}")
    private final String sslKeystorePassword;
    @Value("${bugreport_sslKeystoreType:#{null}}")
    private final String sslKeystoreType;
    @Value("${bugreport_sslSecurityProtocol:#{null}}")
    private final String sslSecurityProtocol;
    @Value("${bugreport_sslKeystorePath:#{null}}")
    private final String sslKeystorePath;
    @Value("${bugreport_sslTruststorePassword:#{null}}")
    private final String sslTruststorePassword;
    @Value("${bugreport_sslTruststoreType:#{null}}")
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
