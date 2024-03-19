var addrAddBt = document.querySelector('button#address_add_button');
var plusMarkIcon = addrAddBt.querySelector('i');
var username = document.querySelector('input#username');
var password = document.querySelector('input#password');
var email = document.querySelector('input#email');
var phoneNumber = document.querySelector('input#phone_number');
var address = document.querySelector('select#address');
var updateUserBt = document.querySelector('button#user_update_button');
var logoutBt = document.querySelector('button#logout_button');

var newAddrInput = document.createElement('input');
var newAddrAddBt = document.createElement('button');
var xMarkIcon = document.createElement('i');

xMarkIcon.classList.add('fa-solid', 'fa-xmark');

newAddrInput.classList.add('text-end', 'form-control', 'border-2', 'my-3');
newAddrInput.name = 'txtNewAddr';
newAddrInput.type = 'text';
newAddrInput.placeholder = 'Add new Address';
newAddrInput.style.display = 'inline-block';

newAddrAddBt.classList.add('btn', 'border', 'border-secondary', 'rounded-pill', 'text-primary', 'fw-bold', 'my-3');
newAddrAddBt.name = 'newAddrAddBt';
newAddrAddBt.type = 'button';
newAddrAddBt.textContent = 'Add';
newAddrAddBt.addEventListener('click', function(event) {
    event.preventDefault();
    
    if (newAddrInput.value.trim().length === 0) {
        messageAppear('New address must not be empty');
        return;
    }
    
    var formData = encodeURIComponent(newAddrInput.name) + "=" + encodeURIComponent(newAddrInput.value) + "&btAction=addaddr";
    
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
        addrAddBt.click();
        createAddrOption();
    })
    .catch((error) => console.error('Error:', error));
});

var isAdding = false;
addrAddBt.addEventListener('click', function(event) {
    if (!isAdding) {
        isAdding = true;
        addrAddBt.replaceChild(xMarkIcon, plusMarkIcon);
        address.parentNode.replaceChild(newAddrInput, address);
        newAddrInput.parentNode.appendChild(newAddrAddBt);
    } else {
        isAdding = false;
        addrAddBt.replaceChild(plusMarkIcon, xMarkIcon);
        newAddrInput.parentNode.replaceChild(address, newAddrInput);
        newAddrAddBt.remove();
    }
});

updateUserBt.addEventListener('click', function(event) {
    event.preventDefault();
    
    
    var formData = 'btAction=adduser&';
    var hasEmptyField = false;
    var sentInputs = document.querySelectorAll('[data-is-sent="1"]');
    sentInputs.forEach((input) => {
        let name = encodeURIComponent(input.name);
        let value = encodeURIComponent(input.value.trim());
        if (value.length === 0) {
            hasEmptyField = true;
            return;
        }
        formData += name + '=' + value + '&';
    });
    formData = formData.slice(0, -1);
    if (hasEmptyField) {
        messageAppear('Data field must not be empty, please enter a value');
        return;
    }
    
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
    })
    .catch((error) => console.error('Error:', error));
});

logoutBt.addEventListener('click', function() {
    window.location.href = 'FunctionDispatchServlet?btAction=logout';
});

function createAddrOption() {
    var lastIndex = address.querySelectorAll('option');
    lastIndex = lastIndex[lastIndex.length - 1];
    
    var newOption = document.createElement('option');
    newOption.value = lastIndex.value;
    newOption.setAttribute('selected', 'true');
    newOption.textContent = newAddrInput.value;
    address.appendChild(newOption);
}