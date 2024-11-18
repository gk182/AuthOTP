package util;

import java.security.SecureRandom;
import java.util.Random;

public class OTPGenerator {
    public static String generateOTP() {
    	Random rand = new Random();
        StringBuilder otp = new StringBuilder();

        // Tạo OTP gồm 6 chữ số ngẫu nhiên
        for (int i = 0; i < 6; i++) {
            otp.append(rand.nextInt(10));  // Chỉ thêm các chữ số từ 0 đến 9
        }

        return otp.toString();
    }
    public static void main(String[] args) {
      System.out.print(generateOTP());
  }
}
