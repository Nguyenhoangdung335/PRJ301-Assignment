/*This .js document must be separated from other JavaScript docs*/
/* global HTMLInputElement */

//Gets DOM Objects
var title = document.getElementsByTagName("title")[0];
var header = document.querySelector("header");
var clock = document.querySelector("time");
var Input = document.querySelectorAll("input");
var stateField = document.querySelector("input#registration_state[type='hidden']");
var boardText = document.querySelectorAll("form h2, form h3, form p");
var RegistButton = document.querySelectorAll(".Regist_button");
var LogInButton = document.querySelectorAll(".LogIn_button");
var TermAndPolicyLinks = document.querySelectorAll("#term-and-policy a");
var ThemeColor = document.querySelector("#switch-color-button");
var accountHeader = document.querySelector("#Account-header");
var color1 = document.querySelector("#Color-1");
var color2 = document.querySelector("#Color-2");
var color3 = document.querySelector("#Color-3");
var SignIn = document.querySelector("#Sign-in");
var LogIn = document.querySelector("#Log-in");
var Register = document.querySelector("#Register");
var SignUp = document.querySelector("#LogIn");
var ContinueButton = document.getElementById("continue");

//HTML Text adjustments
var accountText = accountHeader.innerHTML;
var titleText_Registration = title.innerHTML;
var tilteText_LogIn = "Log in";
//Theme colors change button
ThemeColor.addEventListener("mousedown",function(){
    color1.classList.toggle("color-1");
    color2.classList.toggle("color-2");
    color3.classList.toggle("color-3");
    header.classList.toggle("background-change");
    Register.classList.toggle("color-2");
    SignUp.classList.toggle("color-2");
    
    var MessageDiv = document.querySelector('div.message');
    if (MessageDiv !== null) {
        MessageDiv.classList.toggle("color-2");
        if (MessageDiv.classList.contains("color-2")) {
            MessageDiv.style.setProperty('--border-color', 'black');
            MessageDiv.style.setProperty('--text-color', 'black');
        } else {
            MessageDiv.style.setProperty('--border-color', 'white');
            MessageDiv.style.setProperty('--text-color', 'white');
        }
    }
    
    //Button color change
    for (var i = 0; i < RegistButton.length; ++i){
        RegistButton[i].classList.toggle("button-change-color");
        LogInButton[i].classList.toggle("button-change-color");
        TermAndPolicyLinks[i].classList.toggle("background-change");
    }
    //In-board text color change
    for (var i = 0; i < boardText.length; ++i){
        boardText[i].classList.toggle("switch-white-black");
    }
});
//Visibility of Sign-in board - Button Sign-in slide effects
SignIn.addEventListener("click",function(){
    if (accountHeader.innerHTML === accountText) accountHeader.innerHTML = "Log in to website";
    else accountHeader.innerHTML = accountText;
    if (title.innerHTML === titleText_Registration) title.textContent = tilteText_LogIn;
    else title.innerHTML = titleText_Registration;
/*--------------------------------------------------------*/
    //Slider color and X-axis movement
    if (!Register.classList.contains("show-hide")){
        if (SignIn.classList.contains("color-2")){
            SignIn.classList.remove("color-2");
            LogIn.classList.add("color-1");
        }
        LogIn.classList.add("slideTo2");
        SignIn.classList.add("slideTo1");
    }
    else{
        if (LogIn.classList.contains("color-1")){
            LogIn.classList.remove("color-1");
            SignIn.classList.add("color-2");
        }
        LogIn.classList.remove("slideTo2");
        SignIn.classList.remove("slideTo1");
    }
    
    SignUp.classList.toggle("show-hide");
    SignUp.classList.toggle("translate_on_Y");
    Register.classList.toggle("show-hide");
    
    //Delete all input fields in Sign-in when switch to Log in
    regisResetField();
    window.history.replaceState({}, "", "/Registration?state=register");
});

function regisResetField () {
    for (var i = 0; i < Input.length - 3; ++i){
        if (i === 1 || i === 2) Input[i].value = "";
        else Input[i].value = "";
    }
}

//Visibility of Log in board - Button Log in slide effects
LogIn.addEventListener("click",function(){
    if (accountHeader.innerHTML === "Log in to website") accountHeader.innerHTML = accountText;
    else accountHeader.innerHTML = "Log in to website";
    if (title.innerHTML === tilteText_LogIn) title.textContent = titleText_Registration;
    else title.innerHTML = tilteText_LogIn;
/*--------------------------------------------------------*/
    //Slider color and X-axis movement
    if (!SignUp.classList.contains("show-hide")){
        if (LogIn.classList.contains("color-1")){
            LogIn.classList.remove("color-1");
            SignIn.classList.add("color-2");
        }
        LogIn.classList.remove("slideTo2");
        SignIn.classList.remove("slideTo1");
    }
    else{
        if (SignIn.classList.contains("color-2")){
            SignIn.classList.remove("color-2");
            LogIn.classList.add("color-1");
        }
        LogIn.classList.add("slideTo2");
        SignIn.classList.add("slideTo1");
    }
    SignUp.classList.toggle("show-hide");
    SignUp.classList.toggle("translate_on_Y");
    Register.classList.toggle("show-hide");
    //Delete all input fields in Log in when switch to Sign-in
    loginResetField();
    window.history.replaceState({}, "", "/Registration?state=login");
});

function loginResetField () {
    for (var i = 6; i < 8; ++i){
        Input[i].value = "";
    }
}

//Start checking terms and policies checkbox
setInterval(function(){
    if (!document.getElementById("Sign-in-checkbox").checked){
        ContinueButton.disabled = true;
        ContinueButton.style.cursor = "not-allowed";
    }
    else{
        ContinueButton.disabled = false;
        ContinueButton.style.cursor = "pointer";
    }
},300);
//End checking terms and policies checkbox
/*----------Fetch hidden input's values to switch form display and slider re-styling----------*/
switchRegisState();
/*-----End fetching data-----*/
//Start system clock
function getHourAndMinute(){
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth();
    var year = date.getFullYear();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    day = day<10 ? "0" + day : day;
    month = month<10 ? "0" + (month + 1) : (month + 1); //Month starts at 0 for January
    hours = hours<10 ? "0" + hours : hours;
    minutes = minutes<10 ? "0" + minutes : minutes;
    var time = day+'/'+month+'/'+year+ "  " +hours+':'+minutes;
    clock.textContent = time;
}
setInterval(getHourAndMinute,1000);
//End system clock

var registrationState = document.querySelector('input#registration_state');
var originalDescriptor = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value');
Object.defineProperty(registrationState, 'value', {
    get: function() {return originalDescriptor.get.call(registrationState);},
    set: function(newValue) {
        originalDescriptor.set.call(registrationState, newValue);
        switchRegisState();
    }
});

//Register button submit function 
var registerBt = document.querySelector('button.registerSubmit');
var registerForm = document.querySelector('form#Register');
registerBt.addEventListener('click', function(event) {
    event.preventDefault();
    
    var registerBtReplica = document.createElement('input');
    registerBtReplica.type = 'hidden';
    registerBtReplica.name = 'btAction';
    registerBtReplica.value = registerBt.value;
    registerForm.appendChild(registerBtReplica);
    
    var formData = '';
    var isEmptyField = false;
    registerForm.querySelectorAll('input').forEach( input => {
        let name = encodeURIComponent(input.name);
        let value = encodeURIComponent(input.value.trim());
        if (value.length === 0) isEmptyField = true;
        formData += name + '=' + value + '&';
    });
    formData = formData.slice(0, -1);
    if (isEmptyField) {
        messageAppear('Input field must not be left empty, please enter value');
        return;
    }
    console.log(formData);

    fetch('./FunctionDispatchServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        messageAppear(data.message);
        stateField.value = data.state;
        window.scrollTo({top: 0, behavior: 'smooth'});
        regisResetField();
        loginResetField();
    })
    .catch((error) => console.error('Error:', error));
});


//Login button submit function 
var loginBt = document.querySelector('button.loginSubmit');
var loginForm = document.querySelector('form#LogIn');
loginBt.addEventListener('click', function(event) {
    event.preventDefault();
    
    var loginBtReplica = document.createElement('input');
    loginBtReplica.type = 'hidden';
    loginBtReplica.name = 'btAction';
    loginBtReplica.value = loginBt.value;
    loginForm.appendChild(loginBtReplica);
    
    var formData = '';
    loginForm.querySelectorAll('input').forEach( input => {
        let name = encodeURIComponent(input.name);
        let value = encodeURIComponent(input.value);
        formData += name + '=' + value + '&';
    });
    formData = formData.slice(0, -1);
    console.log(formData);

    fetch('./FunctionDispatchServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.state === 'success')
            window.location.href = 'DispatchServlet?btAction=home';
        else {
            messageAppear(data.message);
            stateField.value = data.state;
            window.scrollTo({top: 0, behavior: 'smooth'});
            regisResetField();
            loginResetField();
        }
    })
    .catch((error) => console.error('Error:', error));
});

function switchRegisState () {
    if (stateField.value === "register"){
        //Switch board
        SignUp.classList.add("show-hide");
        Register.classList.remove("show-hide");
        //Log in button effects
        LogIn.classList.remove("color-1");
        LogIn.classList.remove("slideTo2");
        //Sign-in button effects
        SignIn.classList.remove("slideTo1");
        SignIn.classList.add("color-2");
    }
    else if (stateField.value === "login"){
        //Switch board
        SignUp.classList.remove("show-hide");
        Register.classList.add("show-hide");
        //Log in button effects
        LogIn.classList.add("color-1");
        LogIn.classList.add("slideTo2");
        //Sign-in button effects
        SignIn.classList.add("slideTo1");
        SignIn.classList.remove("color-2");
    }
}
