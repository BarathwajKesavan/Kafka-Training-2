package com.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        String bootstrapServers = "127.0.0.1:9092";
        Properties property=new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

       KafkaProducer<String,String> producer = new KafkaProducer<String,String>(property);
    //record using keys
       ProducerRecord<String,String> record = new ProducerRecord<>("replication_topic","1","Helloworld1");
        ProducerRecord<String,String> record2 = new ProducerRecord<>("replication_topic","2","Helloworld2");
        ProducerRecord<String,String> record3 = new ProducerRecord<>("replication_topic","3","Helloworld3");

    //record without keys
        ProducerRecord<String,String> record1 = new ProducerRecord<>("replication_topic","Barath:Helloworld");

       producer.send(record);
        producer.send(record1);
        producer.send(record2);
        producer.send(record3, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("Offset "+ recordMetadata.offset()+"Topic "+recordMetadata.topic() );
            }
        });

        //use flush when you need to push the message
       producer.flush();
       producer.close();

    }
}
