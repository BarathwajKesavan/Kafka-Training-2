package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org. apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());
        String groupId ="my-fourth-application";
        String bootstrapServers = "127.0.0.1:9092";
        String groupID = "My Fourth Application";
        String topic = "replication_topic";
        Properties property=new Properties();
        property.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        property.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        property.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        property.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        property.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        KafkaConsumer<String,String> consumer= new KafkaConsumer<String, String>(property);
       // consumer.subscribe(Arrays.asList(topic)); //dont use topic while using offset. Either of offset or topic has to be used
        TopicPartition partitionToRead = new TopicPartition(topic,0);
        long offset = 5L;
        consumer.assign(Arrays.asList(partitionToRead));
        consumer.seek(partitionToRead,offset);

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String,String> record: records){
                System.out.println(record.key()+record.value()+record.offset()+record.topic()+record.partition());
            }
        }

    }
}
