package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Spring boot startup and configuration file.
 * @author sumit
 *
 */
@SpringBootApplication
public class PrimeGeneratorApplication {
	
	@Autowired
    private ObjectMapper objectMapper;


    @PostConstruct
    public void setup() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

	public static void main(String[] args) {
		SpringApplication.run(PrimeGeneratorApplication.class, args);
	}
}
