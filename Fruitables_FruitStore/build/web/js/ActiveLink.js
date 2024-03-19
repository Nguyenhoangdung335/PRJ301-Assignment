var path = window.location.pathname;

switch (path) {
    case '/Home':
        document.getElementById('nav-shop').classList.remove('active');
        document.getElementById('nav-home').classList.add('active');
        break;
    case '/Search':
        document.getElementById('nav-home').classList.remove('active');
        document.getElementById('nav-shop').classList.add('active');
        break;
    case '/YourCart':
        document.getElementById('nav-home').classList.remove('active');
        document.getElementById('nav-shop').classList.remove('active');
        break;
}