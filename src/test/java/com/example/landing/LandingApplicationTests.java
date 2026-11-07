package com.example.landing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LandingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void getLandingPage_returnsIndexViewWithStatus200() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("NextGen")))
                .andExpect(content().string(containsString("action=\"/contact\"")));
    }

    @Test
    void submitContactForm_withValidData_redirectsWithFlashAttribute() throws Exception {
        mockMvc.perform(post("/contact")
                        .param("name", "Jane Doe")
                        .param("email", "jane.doe@example.com")
                        .param("message", "Hello! This is a test message."))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/#contact"))
                .andExpect(flash().attribute("successMessage", "Form submitted successfully!"));
    }

    @Test
    void submitContactForm_withInvalidEmail_returnsIndexWithErrors() throws Exception {
        mockMvc.perform(post("/contact")
                        .param("name", "Jane Doe")
                        .param("email", "invalid-email-address")
                        .param("message", "Valid message content here."))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeHasFieldErrors("contactForm", "email"))
                .andExpect(content().string(containsString("Please provide a valid email address.")));
    }

    @Test
    void submitContactForm_withEmptyMessage_returnsIndexWithErrors() throws Exception {
        mockMvc.perform(post("/contact")
                        .param("name", "Jane Doe")
                        .param("email", "jane@example.com")
                        .param("message", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeHasFieldErrors("contactForm", "message"))
                .andExpect(content().string(containsString("Message cannot be empty.")));
    }
}
