# Bug report for broken kafka template metric with tag provider

Metrics available at `http://localhost:8080/actuator/prometheus`.

To run:

* With kafka without SSL:

```bash
mvn package
export bugreport_bootstrapServers='host:port' bugreport_topic='metrics-bug-report-test' && java -jar target/metrics-bug-1.0.0-SNAPSHOT.jar
```

* With kafka with SSL:

```bash
mvn package
export bugreport_bootstrapServers='host:port' bugreport_topic='metrics-bug-report-test-topic' bugreport_sslSecurityProtocol='SSL' bugreport_sslKeyPassword='YOUR KEY PASS' bugreport_sslKeystorePath='path/to/kafka.keystore.jks' bugreport_sslKeystorePassword='YOUR KEYSTORE PASS' bugreport_sslTruststorePath='/path/to/kafka.truststore.jks' bugreport_sslTruststorePassword='YOUR TRUSTSTORE PASS' && java -jar target/metrics-bug-1.0.0-SNAPSHOT.jar
```

## Run via ide

Edit `src/main/resources/application.properties` or supply via environment properties for your own topic and kafka bootstrap server:

* `bugreport_bootstrapServers=host:port`
* `bugreport_topic=metrics-bug-report-test-topic`

For Kafka ssl config, if applicable to you, set:

* `bugreport_sslSecurityProtocol=SSL`
* `bugreport_sslKeyPassword=some pass`
* `bugreport_sslKeystorePath=/path/to/your/keystore/key`
* `bugreport_sslKeystorePassword=some pass`
* `bugreport_sslTruststorePath=/path/to/your/truststore/key`
* `bugreport_sslTruststorePassword=some pass`
