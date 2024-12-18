
import login from "./login.js";

// Lấy thẻ button "Đăng nhập"
const btnLogin = document.querySelector('.btn_login');

// Lấy overlay và modal
const overlay = document.querySelector('.overlay');
const modal = document.querySelector('.modal');
// Sự kiện click vào nút "Đăng nhập"
btnLogin.addEventListener('click', function () {
    overlay.style.display = 'block';
    modal.style.display = 'block';
    login();
});
const closeBtn = document.querySelector('#close-btn');
const closeBtn1 = document.querySelector('#close-btn1');



// Sự kiện khi nhấn nút close-btn
closeBtn.addEventListener('click', function () {
    overlay.style.display = 'none';
    modal.style.display = 'none';
});

// Sự kiện khi nhấn nút close-btn1
closeBtn1.addEventListener('click', function () {
    overlay.style.display = 'none';
    modal.style.display = 'none';
});

// Đảm bảo overlay ẩn khi nhấn ra ngoài modal
overlay.addEventListener('click', function () {
    overlay.style.display = 'none';
    modal.style.display = 'none';
});






