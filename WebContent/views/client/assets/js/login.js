//import Validator from "./validator.js";
function login() {

    const $ = document.querySelector.bind(document);
    const $$ = document.querySelectorAll.bind(document);

//    // Xác thực và xử lý đăng nhập
//    Validator({
//        form: '#form-1',
//        formGroupSelector: ".form-group",
//        errorSelector: '.form-message',
//        rules: [
//            Validator.isRequired('#username', 'Tài khoản không được để trống'),
//            Validator.isRequired('#password', 'Mật khẩu không được để trống')
//        ],
//        onSubmit: data => {
//            fetch('../api/auth/token', {
//                method: 'POST',
//                headers: {
//                    'Content-Type': 'application/json',
//                },
//                body: JSON.stringify(data)
//            })
//                .then(response => {
//                    if (!response.ok) throw new Error('Tài khoản hoặc mật khẩu không đúng');
//                    return response.json();
//                })
//                .then(data2 => {
//                    localStorage.setItem("TOKEN", data2.result.token);
//                    localStorage.setItem("USERNAME", data.username);
//                    sessionStorage.setItem("USERNAME", data.username);
//                    window.location.href = data.username === "admin" ? "./admin" : "../public/home";
//                })
//                .catch(error => {
//                    $("#message").innerText = error.message;
//                });
//        }
//    });
//
//    // Xác thực và xử lý đăng ký
//    Validator({
//        form: '#form-2',
//        formGroupSelector: ".form-group",
//        errorSelector: '.form-message',
//        rules: [
//            Validator.isRequired('#email2', 'Email không được để trống'),
//            Validator.isEmail('#email2', 'Email không hợp lệ'),
//
//            Validator.isRequired('#username2', 'Tài khoản không được để trống'),
//            Validator.minLength('#username2', 5, 'Tài khoản phải có ít nhất 5 kí tự'),
//            Validator.isRequired('#password2', 'Mật khẩu không được để trống'),
//            Validator.minLength('#password2', 6, 'Mật khẩu phải có ít nhất 6 kí tự'),
//            Validator.isRequired('#confirm-password', 'Vui lòng nhập lại mật khẩu'),
//            Validator.isConfirmed('#confirm-password', () => $('#password2').value, 'Mật khẩu nhập lại không khớp')
//        ],
//        onSubmit: data => {
//            fetch('../api/users', {
//                method: 'POST',
//                headers: {
//                    'Content-Type': 'application/json',
//                },
//                body: JSON.stringify(data)
//            })
//                .then(response => {
//                    if (!response.ok) throw new Error('Username hoặc email đã tồn tại');
//                    return response.json();
//                })
//                .then(() => {
//                    alert("Đăng ký thành công!");
//                    $(".btn-login").click();
//                })
//                .catch(error => {
//                    $("#message2").innerText = error.message;
//                });
//        }
//    });

    // Chuyển đổi giữa form đăng nhập và đăng ký
    $(".btn-signup").addEventListener('click', () => {
        $(".container1.login").style.display = "none";
        $(".container1.signup").style.display = "block";
    });

    $(".btn-login").addEventListener('click', () => {
        $(".container1.signup").style.display = "none";
        $(".container1.login").style.display = "block";
    });

    // Quên mật khẩu
    $("#forgot").addEventListener('click', event => {
        event.preventDefault();
        window.location.href = "forgot.html";
    });




}
export default login;