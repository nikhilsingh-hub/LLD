package KafkaMessageProducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaFileProducer {

    private final KafkaProducer<String, String> producer;

    public KafkaFileProducer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.producer = new KafkaProducer<>(props);
    }

    public void sendFromFile(String topic, String key, String content) {
        try {

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, content);

            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.printf("Sent file content to %s [partition %d, offset %d]%n",
                            metadata.topic(), metadata.partition(), metadata.offset());
                }
            });

        } catch (Exception e) {
            System.err.println("Error reading file: ");
            e.printStackTrace();
        }
    }

    public void close() {
        producer.close();
    }

    public static void main(String[] args) {

        DTO8002 dto = new DTO8002();
        String suffix = "";
        String bootstrapServers = dto.serverIp;
        String topic = dto.topic;
        String key = dto.key;
        String value = dto.value;

        KafkaFileProducer kafkaProducer = new KafkaFileProducer(bootstrapServers);
        kafkaProducer.sendFromFile(topic, key, value);
        kafkaProducer.close();
    }
}
