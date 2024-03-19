var passwordField = document.querySelector('input#password');
var conPassLabel = document.querySelector('#con_pass_label');
var confirmPasswordField = document.querySelector('input#confirm_password');

var errorMsg = document.createElement('span');
errorMsg.style.color = 'red';
errorMsg.style.fontStyle = 'italic';
errorMsg.style.fontSize = '0.7em';
errorMsg.style.fontWeight = '800';
errorMsg.textContent = 'Confirm password is different';

confirmPasswordField.addEventListener('change', function(event) {
    let passValue = passwordField.value;
    let confirmValue = confirmPasswordField.value;
    console.log(passValue + ' ' + confirmValue);
    if (passValue !== confirmValue) {
        conPassLabel.appendChild(errorMsg);
    } else if (passValue === confirmValue) {
        var errorMessage = conPassLabel.querySelector('span');
        if (errorMessage !== null) errorMessage.remove();
    }
});
