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
            <p>The OTP will expire in <span id="timer">60</span> seconds.</p>
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

    <script>
        // Chức năng xử lý input OTP
        function moveToNext(field, index) {
            // Chỉ cho phép nhập số
            field.value = field.value.replace(/[^0-9]/g, '');
            
            // Nếu đã nhập và không phải ô cuối
            if (field.value && index < 6) {
                document.getElementById('otp-' + (index + 1)).focus();
            }
            
            // Kiểm tra nếu tất cả các ô đã được điền
            checkComplete();
        }

        function checkComplete() {
            let complete = true;
            for (let i = 1; i <= 6; i++) {
                const input = document.getElementById('otp-' + i);
                if (!input || !input.value) {
                    complete = false;
                    break;
                }
            }
            
            // Enable/disable nút submit dựa trên trạng thái điền form
            document.querySelector('.submit-btn').disabled = !complete;
        }

        // Xử lý Backspace
        document.querySelectorAll('.otp-input input').forEach((input, index) => {
            input.addEventListener('keydown', (e) => {
                if (e.key === 'Backspace' && !input.value && index > 0) {
                    // Focus vào ô trước đó khi nhấn Backspace trên ô trống
                    document.getElementById('otp-' + index).focus();
                }
            });
        });

        // Timer đếm ngược
        let timeLeft = 60;
        const timerDisplay = document.getElementById('timer');
        const resendBtn = document.getElementById('resendBtn');
        const otpForm = document.getElementById('otpForm');
        
        const timer = setInterval(() => {
            timeLeft--;
            timerDisplay.textContent = timeLeft;
            
            if (timeLeft <= 0) {
                clearInterval(timer);
                resendBtn.disabled = false;
                // Vô hiệu hóa form OTP
                document.querySelectorAll('.otp-input input').forEach(input => {
                    input.disabled = true;
                });
                document.querySelector('.submit-btn').disabled = true;
                
                // Hiển thị thông báo hết hạn
                timerDisplay.parentElement.innerHTML = '<span style="color: #dc3545;">OTP has expired. Please request a new one.</span>';
                
                // Thêm class để làm mờ form
                otpForm.classList.add('expired');
            }
        }, 1000);

        // Reset form khi click resend
        document.querySelector('form[action="user"]').addEventListener('submit', () => {
            timeLeft = 60;
            resendBtn.disabled = true;
            
            // Kích hoạt lại form
            document.querySelectorAll('.otp-input input').forEach(input => {
                input.disabled = false;
                input.value = ''; // Xóa giá trị cũ
            });
            
            // Xóa class expired
            otpForm.classList.remove('expired');
            
            // Reset thông báo timer
            timerDisplay.parentElement.innerHTML = 'The OTP will expire in <span id="timer">60</span> seconds.';
        });
    </script>
</body>
</html>
