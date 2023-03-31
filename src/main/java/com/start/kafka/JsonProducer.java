package com.start.kafka;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.start.kafka.model.Address;
import com.start.kafka.model.Employee;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JsonProducer {
    public static void main(String[] args) {
        //create properties
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        //Create Producer
        KafkaProducer<String, Employee> producer = new KafkaProducer(prop);
        //Create Data
        Address add = new Address("India");
        Address add3 = new Address("USA");
        Employee emp1 = new Employee(111, "Manish Singh 1", 33, add);
        Employee emp2 = new Employee(112, "Manish Singh 2", 34, add);
        Employee emp3 = new Employee(113, "Manish Singh 3", 43, add3);
        List<Employee> empList = new ArrayList();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        //object Mapping
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        for(int i=0;i<empList.size();i++) {
            JsonNode jsonNode = mapper.valueToTree(empList.get(i));
            //Create ProducerRecord
            Integer key = 1;
            ProducerRecord prodRecord = new ProducerRecord("replication_topic", Integer.toString(key), jsonNode);

            //Send Record
            producer.send(prodRecord, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.println("Topic:" + recordMetadata.topic());
                        System.out.println("Partition:" + recordMetadata.partition());
                        System.out.println("Offset:" + recordMetadata.offset());
                    } else {
                        e.printStackTrace();
                    }
                }
            });
        }//end of for loop
        producer.flush();
        producer.close();
        System.out.println("Data Send");
    }
}
