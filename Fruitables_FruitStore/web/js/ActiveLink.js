var path = window.location.pathname;

var input = document.createElement('input') || document.getElementById('active_link');
input.type = 'hidden';
input.id = 'active_link';
input.value = path;
input.name = 'activeLink';

if (!document.getElementById('active_link')) {
    document.body.appendChild(input);
}

switch (input.value) {
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