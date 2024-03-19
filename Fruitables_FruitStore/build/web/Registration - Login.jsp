<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Registration</title>
        <link rel="stylesheet" href="css/Registration - Login styles.css">
        <link rel="stylesheet" href="css/speech-bubble.css">
        <!--Fonts and icons-->
        <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap">
        <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        
        <%
            String message = request.getParameter("message");
            request.setAttribute("messageExist", message != null);
        %>
        
    </head>
    <!--Start page-->
    <body>
        <!--This header is local and can't be render between multiple webpages-->
        <header>
            <h1><a href="/Home">Fruitables</a></h1>
            <div id="header-flex">
                <div id="header-flex-1">
                    <span class="material-symbols-outlined" id="user-and-website-infor">
                        <a href="./Registration - Login.html">info</a>
                    </span>
                    <div id="term-and-policy">
                        <a href="../Privacy Policy - Legal Terms/Fruitable - Privacy Policy.html">Privacy Policy</a>
                        <span> / </span>
                        <a href="#">Legal Terms</a>
                    </div>
                </div>
                <!--Theme color button-->
                <div id="header-flex-2">
                    <div id="switch-color-button" title="Theme color">
                        <div id="Color-1" class="default-color-1"></div>
                        <div id="Color-2" class="default-color-2"></div>
                        <div id="Color-3" class="default-color-3"></div>
                    </div>
                <!--Version control-->
                    <p>v 1.0.00</p>
                </div>
            </div>
        </header>
        <main>
            <!--Guildance header for user-->
            <h2 id="Account-header">Already have an account? Then click Log in to continue!</h2>
            <!--Switcher between Sign in/Log in-->
            <div id="Slide-button">
                <button class="Account_buttons color-2" id="Sign-in" title="Move to sign in page">Sign in</button>
                <button class="Account_buttons" id="Log-in" title="Move to log in page">Log in</button>
            </div>
            <p id="form_text">
                This website collects user's data to enhance experience and track your activities on this website 
            </p>
            <!--Registration/Sign in form-->
            <form id="Register" method="post" action="DispatchServlet">
                <h2>Register new Account</h2>
                <h3 class="Regist_header">Username:</h3>
                <input class="field" type="text" name="txtUsername" placeholder="Your username (maximum 40 characters)" maxlength="40" required>
                <h3 class="Regist_header">Password:</h3>
                <input id="password" class="field" type="password" name="txtPassword" placeholder="xxxxxxxx" value="" minlength="4" required>
                <h3 id="con_pass_label" class="Regist_header">Confirm your password: </h3>
                <input id="confirm_password" class="field" type="password" placeholder="xxxxxxxx" minlength="4" required>
                <h3 class="Regist_header">Your email:</h3>
                <input class="field" type="email" name="txtEmail" placeholder="user@gmail.com" inputmode="email" required>
                <h3 class="Regist_header">Phone number:</h3>
                <input class="field" type="tel" name="txtPhoneNumber" placeholder="0123456789" maxlength="11" inputmode="tel" required>
                <hr>
                
                <div class="Confirmation">
                    <input type="checkbox" id="Sign-in-checkbox" required>
                    <p>
                        By clicking Continue, I agree to this website's <a href="Fruitable - Privacy Policy.html">terms and policies</a>
                        and give permission to use my personal data for user experience and data tracking
                        .
                    </p>
                </div>
                <!--Cancel button and Continue button-->
                <section id="Registration_buttons">
                    <button class="Regist_button" type="submit" name="btAction" value="home"><a href="DispatchServlet?btAction=home" id="Regist_button_link">Back to home page</a></button>
                    <button class="Regist_button registerSubmit" type="button" formaction="FunctionDispatchServlet" name="btAction" value="register" id="continue">
                        <span class="material-symbols-outlined"></span>
                        Sign up
                    </button>
                </section>
            </form>
            <!--Sign up/Log in form-->
            <form id="LogIn" class="show-hide">
                <h2>Log in with your account</h2>
                <h3 class="Regist_header" minlength="4">Username:</h3>
                <input class="field" type="text" name="txtUsername" placeholder="Account's username" required>
                <h3 class="Regist_header" minlength="4">Password:</h3>
                <input class="field" type="password" name="txtPassword" placeholder="Enter your password" required>
                <hr>
                
                <c:if test="${messageExist}">
                    <p class="message"><%= request.getParameter("message") %></p>
                </c:if>
                                        
                <!--Home page button and Log in button-->
                <section id="Registration_buttons">
                    <button class="LogIn_button" type="button" name="btAction" value="home"><a href="DispatchServlet?btAction=home" id="Regist_button_link">Back to home page</a></button>
                    <button class="LogIn_button loginSubmit" type="submit" formaction="FunctionDispatchServlet" name="btAction" value="login" id="LogIn-button-fit">
                        <span class="material-symbols-outlined"></span>
                        Log in
                    </button>
                </section>
            </form>
            <form><input id="registration_state" type="hidden" value="${state}"></form>
        </main>
        <footer id="in-Main-footer">
            <h4><strong>@Designed By HTML Codex Distributed By ThemeWagon</strong></h4>
            <address>Address: Long Thanh My Ward, Thu Duc City, HCMC, Vietnam</address>
            <time datetime="datetime"></time>
        </footer>
        <!--JavaScript connect-->
        <script src="js/MessageAppear.js" defer></script>
        <script src="js/Registration - Login interactive.js"></script>
        <script src="js/ConfirmPassword.js" defer></script>
    </body>
    <!--End page-->
</html>