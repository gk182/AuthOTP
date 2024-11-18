package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    // Phương thức gửi email
    public static boolean sendEmail(String recipient, String subject, String otp) {
        // Cấu hình SMTP
        String host = "smtp.gmail.com";
        String port = "587";
        String senderEmail = "phamgiakhai278@gmail.com"; // Email gửi đi
        String senderPassword = "ndrx hmgh kdyd fuvt"; // Mật khẩu ứng dụng của Google

        // Thiết lập thuộc tính cho SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Xác thực tài khoản
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Tạo email với định dạng HTML
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);

            // Nội dung email (HTML)
            String htmlContent = "<!DOCTYPE html>"
            	    + "<html><head><style>"
            	    + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 0; margin: 0; }"
            	    + ".email-container { background: #ffffff; border-radius: 8px; margin: 20px auto; padding: 20px; width: 600px; }"
            	    + ".header { background-color: #007bff; color: white; padding: 10px; text-align: center; font-size: 24px; }"
            	    + ".content { text-align: center; padding: 20px; font-size: 16px; color: #333; }"
            	    + ".otp { font-size: 20px; font-weight: bold; color: #ffffff; background-color: #007bff; padding: 10px 20px; margin: 20px auto; display: inline-block; border-radius: 4px; }"
            	    + ".footer { text-align: center; font-size: 12px; color: #888; padding: 10px; }"
            	    + "</style>"
            	    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
            	    + "</head>"
            	    + "<body>"
            	    + "<div class='email-container'>"
            	    + "<div class='header'><h1>OTP Verification</h1></div>"
            	    + "<div class='content'>"
            	    + "<h2>Hello!</h2>"
            	    + "<p>You have requested an OTP to verify your account. Below is your OTP:</p>"
            	    + "<div class='otp'>" + otp + "</div>"
            	    + "<p>Note: This OTP will expire in 5 minutes. If you did not make this request, please ignore this email.</p>"
            	    + "</div>"
            	    + "<div class='footer'>© 2024 Your Company. All Rights Reserved.</div>"
            	    + "</div>"
            	    + "</body></html>";

            message.setContent(htmlContent, "text/html");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm main để kiểm tra
//    public static void main(String[] args) {
//        String recipient = "khaipham182@gmail.com";
//        String subject = "Xác thực OTP";
//        String otp = OTPGenerator.generateOTP();
//
//        boolean success = sendEmail(recipient, subject, otp);
//        if (success) {
//            System.out.println("Email đã được gửi thành công.");
//        } else {
//            System.out.println("Gửi email thất bại.");
//        }
//    }
}
