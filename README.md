# Bug report for broken kafka template metric with tag provider

Edit `src/main/resources/application.properties` or supply via environment properties for your own topic and kafka bootstrap server:

* `bugreport.bootstrapServers=host:port`
* `bugreport.topic=metrics-bug-report-test-topic`

For Kafka ssl config, if applicable to you, set:

* `bugreport.sslSecurityProtocol=SSL`
* `bugreport.sslKeyPassword=some pass`
* `bugreport.sslKeystorePath=/path/to/your/keystore/key`
* `bugreport.sslKeystorePassword=some pass`
* `bugreport.sslTruststorePath=/path/to/your/truststore/key`
* `bugreport.sslTruststorePassword=some pass`
