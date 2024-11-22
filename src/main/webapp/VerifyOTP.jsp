<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify OTP</title>
    <link rel="stylesheet" href="css/verifyotp.css">
</head>
<body>
    <div class="container">
        <div class="header">
            Verify OTP
        </div>
        <div class="content">
            <p>We have sent a One-Time Password (OTP) to your email. Please enter it below to verify your account.</p>
            <p>The OTP can resend in <span id="timer">60</span> seconds.</p>
        </div>

        <form id="otpForm" action="verify-otp" method="POST">
            <div class="otp-input">
                <input type="text" maxlength="1" name="otp-1" id="otp-1" onkeyup="moveToNext(this, 1)" required>
                <input type="text" maxlength="1" name="otp-2" id="otp-2" onkeyup="moveToNext(this, 2)" required>
                <input type="text" maxlength="1" name="otp-3" id="otp-3" onkeyup="moveToNext(this, 3)" required>
                <input type="text" maxlength="1" name="otp-4" id="otp-4" onkeyup="moveToNext(this, 4)" required>
                <input type="text" maxlength="1" name="otp-5" id="otp-5" onkeyup="moveToNext(this, 5)" required>
                <input type="text" maxlength="1" name="otp-6" id="otp-6" onkeyup="moveToNext(this, 6)" required>
            </div>

            <button type="submit" class="submit-btn" disabled>Verify OTP</button>
        </form>

        <div class="resend-section">
            <p>Didn't receive the OTP or it expired?</p>
            <form action="user" method="POST">
                <input type="hidden" name="action" value="resend-otp">
                <button type="submit" class="resend-btn" id="resendBtn">Resend OTP</button>
            </form>
        </div>

        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
    </div>

    <script src="js/OTP.js"></script>
</body>
</html>
