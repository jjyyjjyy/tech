package me.jy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Instant;
import java.util.Properties;

/**
 * @author jy
 */
public class KafkaApplication {

    private static final String KAFKA_SERVER = "localhost:9092";
    private static final String TOPIC = "demo-topic";
    private static final String CLIENT_ID = "demo-client";

    private static Properties getProductProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        return properties;
    }

    /**
     * 发消息有三种模式:
     * 1. fire-and-forget
     * 2. sync
     * 3. async
     */
    public static void send() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProductProperties());
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "Hello, Kafka! " + Instant.now().getEpochSecond());
        producer.send(record);
    }

    public static void main(String[] args) {
        send();
    }
}
