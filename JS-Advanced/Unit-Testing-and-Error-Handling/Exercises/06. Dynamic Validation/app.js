function validate() {
    let emailElement = document.getElementById('email');

    let regex = /^[a-z]+@[a-z]+\.[a-z]+$/;

    emailElement.addEventListener('change', onChange);

    function onChange(e) {
        if(regex.test(emailElement.value)) {
            emailElement.classList.remove('error');
        } else {
            emailElement.classList.add('error');
        }
    }
}
