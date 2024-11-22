// Chức năng xử lý input OTP
function moveToNext(field, index) {
    // Chỉ cho phép nhập số
    field.value = field.value.replace(/[^0-9]/g, '');
    
    // Nếu đã nhập và không phải  cuối
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
    const submitBtn = document.querySelector('.submit-btn');
    submitBtn.disabled = !complete;

    // Log trạng thái của nút submit
    console.log('Submit button enabled:', !submitBtn.disabled);
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

// Khóa nút resend ngay từ đầu
resendBtn.disabled = true;

const timer = setInterval(() => {
    timeLeft--;
    timerDisplay.textContent = timeLeft;
    
    if (timeLeft <= 0) {
        clearInterval(timer);
        resendBtn.disabled = false; // Mở khóa nút resend
        
        // Hiển thị thông báo hết hạn
        timerDisplay.parentElement.innerHTML = '<span style="color: #dc3545;">OTP has expired. Please request a new one.</span>';
        
        
    }
}, 1000);

// Xử lý khi người dùng nhấn nút submit
otpForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Ngăn chặn gửi form để xử lý xác thực OTP

    // Logic xác thực OTP ở đây
    const isValid = validateOTP(); // Đảm bảo validateOTP() được định nghĩa

    if (isValid) {
        // Nếu OTP hợp lệ, gửi form
        otpForm.submit();
    } else {
        // Nếu OTP không hợp lệ, hiển thị thông báo lỗi
        document.querySelector('.error-message').innerText = 'Invalid OTP. Please try again.';
    }
});

// Reset form khi click resend
document.querySelector('form[action="user"]').addEventListener('submit', () => {
    timeLeft = 60;
    resendBtn.disabled = true; // Khóa lại nút resend khi gửi yêu cầu mới
    
    // Kích hoạt lại form
    document.querySelectorAll('.otp-input input').forEach(input => {
        input.disabled = false;
        input.value = ''; // Xóa giá trị cũ
    });
    
    // Xóa class expired
    otpForm.classList.remove('expired');
    
    // Reset thông báo timer
    timerDisplay.parentElement.innerHTML = 'The OTP can resend in <span id="timer">60</span> seconds.';
});
function validateOTP() {
    const otp = Array.from({ length: 6 }, (_, i) => document.getElementById('otp-' + (i + 1)).value).join('');
    return otp.length === 6 && /^[0-9]+$/.test(otp); // Đảm bảo đủ 6 số
}
