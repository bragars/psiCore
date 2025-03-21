package com.rest.Feature;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.rest.Application;
import com.rest.TestConfig;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { Application.class, TestConfig.class })
public class ApplicationTests {

    @Test
    public void contextLoads() {
        // This test simply verifies that the Spring context loads without errors
    }
}
