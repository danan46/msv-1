package com.accenture;

import java.util.Properties;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Path("/record")
public class GreetingResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	Properties props = new Properties();
    	props.put("bootstrap.servers", "localhost:9092");
    	props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	
    	Producer<String, String> producer = new KafkaProducer<String, String>(props);
    	try {
    	producer.send(new ProducerRecord<String, String>("inventory", "apple"));
    	}catch (Exception e) {
			producer.close();
		}
    	producer.close();
        return "Message Sents";
    }
}