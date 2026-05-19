# Server-Vault
ServerVault is a secure, full-stack web application designed to centralize and manage client server credentials and connection details. It provides a streamlined interface for technical teams to store, retrieve, and organize server metadata in one place


# ServerVault

> A secure web application with Spring Boot backend and plain HTML/JS frontend.

**ServerVault** is a full‑stack application where:
- **Frontend** is built with **plain HTML, CSS, and JavaScript**.
- **Backend** runs on **Spring Boot (Java)** inside IntelliJ IDEA.
- Data is stored and retrieved from a **SQL database** (e.g., MySQL, H2, or PostgreSQL).

This project demonstrates **user authentication, form handling, and REST‑style API communication** without heavy frontend frameworks.

---

## 📌 Features

- User login and password validation.
- Create new user forms with server‑side persistence.
- Client‑side validation for required fields and password length.
- Password toggle (show/hide) in login form.
- Simple layout using **Bootstrap 5** from CDN.
- REST‑style endpoints (`/api/login/createLogin`, `/api/createUser`, etc.) for backend communication.

---

## 🛠️ Tech Stack

- **Frontend**
  - HTML5
  - CSS (custom + Bootstrap 5 CDN)
  - Vanilla JavaScript (ES6+)
- **Backend**
  - Java 17+
  - Spring Boot
  - Spring Web (MVC / REST)
  - Spring Data JPA (or JDBC)
- **Database**
  - MS-SQL
- **Tooling**
  - IntelliJ IDEA
  - Maven
  - Plain HTML pages served from `src/main/resources/static/` (or via controller)

---

## 🚀 Getting Started

### 1. Prerequisites

- Java 17 or higher
- Maven
- A database of your choice (MySQL, H2, PostgreSQL, etc.)
- Node (only if you want to run JS linters; not required for plain HTML/JS)

### 2. Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ServerVault.git
   cd ServerVault
   ```

2. Configure the database in `src/main/resources/application.properties` (example for MySQL):
   ```properties
   spring.datasource.url=jdbc:sqlserver://localhost:3306/servervault
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build the project:
   ```bash
   mvn clean package
   ```

4. Run the Spring Boot app:
   ```bash
   mvn spring-boot:run
   ```
   or
   ```bash
   java -jar target/servervault-0.0.1-SNAPSHOT.jar
   ```

5. Open the frontend:
   - Login page: `http://localhost:8080/FORMS/LoginPage/login.html`
   - Create user: `http://localhost:8080/FORMS/LoginPage/createNewLogin.html`

---

## 🌐 Endpoints (Backend)

| Endpoint                      | Method | Description |
|-------------------------------|--------|-------------|
| `POST /api/login/createLogin` | POST   | Authenticate user (username + password). |
| `POST /api/createUser`        | POST   | Create a new user (mobile, username, password). |
| `GET /listOfUsers`            | GET    | View list of users (if implemented). |

> ⚠️ Adjust paths and controller mappings to match your actual `@PostMapping` and `@GetMapping` annotations.

---

## 📂 Project Structure (Simplified)
