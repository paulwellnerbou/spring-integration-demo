package com.example.springintegrationdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringIntegrationDemoApplicationTests {

    @LocalServerPort
    int port;
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testSpringIntegrationHttpEndpoint() {
        ResponseEntity<String> entity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/path/my-argument", String.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
        System.out.println(entity.getBody());
    }
}
