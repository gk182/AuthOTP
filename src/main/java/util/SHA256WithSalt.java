package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256WithSalt {
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // chuyển đổi salt sang chuỗi Hex
        StringBuilder hexString = new StringBuilder();
        for (byte b : salt) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0'); // Bổ sung số 0 nếu cần
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Hàm để hash mật khẩu với salt
    public static String hashPasswordWithSalt(String password, String salt) {
        try {
            // Tạo instance của SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Kết hợp mật khẩu với salt
            String saltedPassword = password + salt;

            // Hash mật khẩu đã kết hợp với salt
            byte[] hashedBytes = digest.digest(saltedPassword.getBytes());

            // Chuyển byte array thành chuỗi dạng Hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // Bổ sung số 0 nếu cần
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi khi tạo SHA-256 instance", e);
        }
    }

//    public static void main(String[] args) {
//        String password = "admin";
//        String salt = generateSalt();
//        System.out.println("Salt: " + salt);
//        String hashedPassword = hashPasswordWithSalt(password, salt);
//        System.out.println("Mật khẩu gốc: " + password);
//        System.out.println("Mật khẩu sau khi hash với salt SHA-256: " + hashedPassword);
//    }
}
