package com.example.landing.controller;

import com.example.landing.model.ContactForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ContactController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ModelAttribute("contactForm")
    public ContactForm getContactForm() {
        return new ContactForm();
    }

    /**
     * Serves the single-page landing page.
     */
    @GetMapping("/")
    public String showLandingPage(@ModelAttribute("contactForm") ContactForm contactForm) {
        return "index";
    }

    /**
     * Handles contact form submissions.
     * Accepts individual @RequestParam parameters (as specified in the starter requirements)
     * while performing server-side Bean Validation using ContactForm.
     */
    @PostMapping("/contact")
    public String handleContactSubmission(
            @Valid @ModelAttribute("contactForm") ContactForm contactForm,
            BindingResult bindingResult,
            @RequestParam(name = "name", required = false) String paramName,
            @RequestParam(name = "email", required = false) String paramEmail,
            @RequestParam(name = "message", required = false) String paramMessage,
            RedirectAttributes redirectAttributes,
            Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Please correct the errors in the form below.");
            if (bindingResult.hasFieldErrors("name")) {
                model.addAttribute("nameError", bindingResult.getFieldError("name").getDefaultMessage());
            }
            if (bindingResult.hasFieldErrors("email")) {
                model.addAttribute("emailError", bindingResult.getFieldError("email").getDefaultMessage());
            }
            if (bindingResult.hasFieldErrors("message")) {
                model.addAttribute("messageError", bindingResult.getFieldError("message").getDefaultMessage());
            }
            return "index";
        }

        // Print submitted data to the console in the exact required format:
        // Name: <name>, Email: <email>, Message: <message>
        String name = (paramName != null && !paramName.isEmpty()) ? paramName.trim() : contactForm.getName().trim();
        String email = (paramEmail != null && !paramEmail.isEmpty()) ? paramEmail.trim() : contactForm.getEmail().trim();
        String message = (paramMessage != null && !paramMessage.isEmpty()) ? paramMessage.trim() : contactForm.getMessage().trim();

        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println("================================================================================");
        System.out.println("[NEW CONTACT FORM SUBMISSION] Timestamp: " + timestamp);
        System.out.println("Name: " + name + ", Email: " + email + ", Message: " + message);
        System.out.println("================================================================================");

        // Flash attribute persists across redirect for a clean POST-Redirect-GET pattern
        redirectAttributes.addFlashAttribute("successMessage", "Form submitted successfully!");

        return "redirect:/#contact";
    }
}
