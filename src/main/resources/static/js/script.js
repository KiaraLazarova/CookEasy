let registerForm = document.querySelector('#registerForm');

registerForm.addEventListener('submit', event => {
    let password = registerForm.querySelector('#password');
    let confirmPassword = registerForm.querySelector('#confirmPassword');

    if(password.value !== confirmPassword.value) {
        event.preventDefault();
        alert('Password must match with confirm password.');
        password.value = '';
        confirmPassword.value = '';
    }
});