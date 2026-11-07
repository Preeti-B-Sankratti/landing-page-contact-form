package com.example.landing.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactForm {

    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
    private String name;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotBlank(message = "Message cannot be empty.")
    @Size(max = 1000, message = "Message must not exceed 1000 characters.")
    private String message;

    public ContactForm() {
    }

    public ContactForm(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
