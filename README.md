# Java Web Application with OTP Authentication

This project is a Java web application that implements user registration, login, and OTP (One-Time Password) authentication. The application is designed with a modern UI using glassmorphism and is responsive across devices.

## Features

- **User Registration and Login**
  - Register with username, email, and password
  - Passwords are hashed using SHA256 with salt
  - Login with username/email and password
  - Modern UI with glassmorphism effects

- **OTP Authentication**
  - Send OTP via email when logging in from a new device
  - OTP expires in 60 seconds
  - Countdown timer display
  - Option to resend OTP when expired
  - Handle cases for incorrect or expired OTP

- **Session and Device Management**
  - Store information about logged-in devices
  - Check device on login
  - Manage login sessions securely

- **User Interface**
  - Modern design with glassmorphism
  - Responsive on various devices
  - Smooth effects and animations
  - Clear error messages

- **Security**
  - Password encryption
  - Two-factor authentication with OTP
  - Device login check
  - Secure session management

## Future Improvements

- **Account Management**
  - Change password
  - Update personal information
  - Manage logged-in devices

- **Advanced Security**
  - Lock account after multiple failed login attempts
  - Log login history
  - Notify on new device login

- **UI Enhancements**
  - Add dark mode
  - Customize user interface
  - Add more animations and effects

- **Additional Features**
  - Password recovery
  - SMS-based OTP
  - Social media login

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/gk182/AuthOTP
   ```

2. **Navigate to the project directory:**
   ```bash
   cd AuthOTP
   ```

3. **Build the project:**
   - Use your preferred IDE (e.g., Eclipse, IntelliJ IDEA) to build the project.

4. **Deploy on Tomcat:**
   - Ensure Apache Tomcat is installed and configured.
   - Deploy the application on Tomcat.

5. **Access the application:**
   - Open a web browser and navigate to `http://localhost:8080/your-app-name`.

## Technologies Used

- **Backend:** Java, Servlet, JSP
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL
- **Server:** Apache Tomcat
- **Libraries:** JavaMail for email sending

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
