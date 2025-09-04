package com.rest.endpoint.services;

import com.rest.events.UserRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    // This method will automatically be called whenever a message appears on the "user_registered" topic
    @KafkaListener(topics = "user_registered", groupId = "notification_group")
    public void handleUserRegistration(UserRegisteredEvent event) {
        LOGGER.info(
                "Received User Registration Event for user: {} with email {}",
                event.getUsername(),
                event.getEmail()
        );

        // --- SIMULATE SENDING A WELCOME EMAIL ---
        // This is where you would integrate with a real email service (e.g., SendGrid, AWS SES)
        try {
            // Simulate a slow network operation
            Thread.sleep(2000);
            LOGGER.info("Welcome email sent to {}", event.getEmail());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Error while sending email", e);
        }
    }

    private void sendWelcomeEmail(String email) {
        try {
            LOGGER.info("Sending welcome email to {}...", email);
            Thread.sleep(2000); // Represents calling an external email service
            LOGGER.info("Welcome email sent successfully to {}.", email);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Error during simulated email sending", e);
        }
    }
}
