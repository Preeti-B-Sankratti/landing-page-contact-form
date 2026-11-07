# Java Full Stack Web Development Internship – Task 1: Landing Page + Contact Form

A modern, responsive landing page with a validated contact form connected to a Spring Boot backend that processes form submissions and prints formatted records directly to the console.

---

## 🚀 Features

- **Modern Single-Page Design**:
  - Sticky glassmorphism navigation bar with responsive mobile menu toggle and smooth scrolling.
  - Vibrant Hero section with headline, badge, call-to-action button, and gradient background.
  - Interactive Feature cards with hover lift animations and SVG icons.
  - About section highlighting key company metrics (120+ Projects Delivered, 98% Happy Clients, 5+ Years Experience).
  - Modern Contact section with client-side character counter and accessible form controls.
  - Responsive footer with navigation and social links.
- **Robust Spring Boot Backend**:
  - Server-side template rendering via Thymeleaf.
  - Form data reception matching `@PostMapping("/contact")` with `@RequestParam String name, email, message`.
  - Server-side data validation (`@NotBlank`, `@Email`, `@Size(max = 1000)`).
  - Friendly field-level error messages retaining user input upon invalid submission.
  - Post-Redirect-Get (PRG) pattern using `RedirectAttributes` flash attribute displaying `"Form submitted successfully!"`.
  - Console logging with timestamps, structured separators, and formatted output:
    ```
    Name: <name>, Email: <email>, Message: <message>
    ```

---

## 🛠️ Tech Stack

- **Java**: 17 / 21
- **Backend Framework**: Spring Boot 3.2.5
- **Dependencies**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-thymeleaf`
  - `spring-boot-starter-validation`
  - `spring-boot-devtools`
  - `spring-boot-starter-test`
- **Frontend**: Plain HTML5, CSS3 (Modern Flexbox, CSS Grid, Custom Properties, Responsive Breakpoints @ 768px & 480px, Google Font *Poppins*), minimal vanilla JavaScript.
- **Build Tool**: Apache Maven (Maven Wrapper included).

---

## 📁 Project Structure

```text
landing-page-contact-form/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/landing/
    │   │   ├── LandingApplication.java
    │   │   ├── controller/
    │   │   │   └── ContactController.java
    │   │   └── model/
    │   │       └── ContactForm.java
    │   └── resources/
    │       ├── application.properties
    │       ├── static/
    │       │   └── css/
    │       │       └── style.css
    │       └── templates/
    │           └── index.html
    └── test/
        └── java/com/example/landing/
            └── LandingApplicationTests.java
```

---

## ⚙️ Prerequisites

- Java Development Kit (JDK 17 or higher, e.g., Adoptium OpenJDK / Oracle JDK).
- Internet connection (first build downloads dependencies).

Verify your Java installation:
```bash
java -version
```

---

## 🏃‍♂️ How to Run

### Option 1: Using Maven Wrapper (Terminal / Command Prompt)

On Windows:
```powershell
.\mvnw.cmd spring-boot:run
```

On Linux / macOS:
```bash
./mvnw spring-boot:run
```

### Option 2: Running the Packaged JAR

1. Build the application:
   ```powershell
   .\mvnw.cmd clean package
   ```
2. Run the generated JAR:
   ```powershell
   java -jar target/landing-page-contact-form-0.0.1-SNAPSHOT.jar
   ```

### Option 3: In an IDE (IntelliJ IDEA / Eclipse / VS Code)

1. Open the project folder in your IDE.
2. Allow Maven to import project dependencies.
3. Locate `src/main/java/com/example/landing/LandingApplication.java`.
4. Right-click and choose **Run 'LandingApplication'** (or click the green Run button).

The server will start on port **8080** (`http://localhost:8080`).

---

## 🧪 How to Test

1. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```
2. Navigate smoothly across sections using the sticky header links.
3. Scroll down or click **"Get In Touch"** to jump directly to the **Contact** section.
4. Fill out the form:
   - **Name**: `Jane Doe`
   - **Email**: `jane.doe@example.com`
   - **Message**: `Hello! We'd love to schedule a demo of your platform.`
5. Click the **Send** button.
6. The page will redirect with a green banner:
   ```
   Form submitted successfully!
   ```
7. Check the running terminal / IDE console to see the formatted output.

### Testing Edge Cases & Validation:
- Leave the fields empty or type an invalid email (e.g. `jane@invalid`):
  - Notice the field-level validation messages appear in red without losing your other inputs.
- Enter a message exceeding 1000 characters:
  - Both client-side counter and server-side validation will guard against oversized submissions.

---

## 🖥️ Sample Console Output

When a form is submitted, the backend logs a formatted entry with a timestamp and divider:

```text
================================================================================
[NEW CONTACT FORM SUBMISSION] Timestamp: 2026-10-11 15:41:16
Name: Alex Morgan, Email: alex@example.com, Message: Testing form submission in production
================================================================================
```

---

## 💡 What I Learned (Full-Stack Flow)

During the development of this project, I explored the complete lifecycle of a modern web request:

1. **Frontend Architecture & Semantic HTML**:
   - Structured a mobile-first, semantic single-page layout without bulky external frameworks.
   - Leveraged modern CSS variables for consistent theming and smooth transitions for interactivity.
2. **HTTP Form Submission**:
   - Wired a standard HTML `<form action="/contact" method="post">` with exact field keys (`name`, `email`, `message`).
3. **Spring MVC Controller Binding & Validation**:
   - Implemented `@PostMapping("/contact")` supporting both `@ModelAttribute @Valid ContactForm` and explicit `@RequestParam` bindings.
   - Handled validation errors gracefully by storing invalid field feedback and returning the view directly, preventing loss of user inputs.
4. **Post-Redirect-Get (PRG) Pattern**:
   - Prevented accidental duplicate form submissions upon browser refreshes by redirecting successful POSTs back to `GET /` using `RedirectAttributes.addFlashAttribute()`.
5. **Automated Testing**:
   - Validated controller behaviors and status codes using Spring's `MockMvc` integration tests for both successful submissions (HTTP 302 Redirect) and invalid inputs (HTTP 200 with error attributes).
