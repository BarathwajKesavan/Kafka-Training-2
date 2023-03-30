package com.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import  org.apache.kafka.clients.producer.KafkaProducer;
import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        String bootstrapServers = "127.0.0.1:9092";
        Properties property=new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

       KafkaProducer<String,String> producer = new KafkaProducer<String,String>(property);

       ProducerRecord<String,String> record = new ProducerRecord<>("replication_topic","Barath","Barath:Helloworld");

       producer.send(record);
       producer.flush();
       producer.close();

    }
}
