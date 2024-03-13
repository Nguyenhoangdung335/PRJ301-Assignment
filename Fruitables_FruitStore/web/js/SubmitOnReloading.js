var form = document.querySelector('form.submitOnReload');
var saveCartDiv = document.querySelector('div#saveCartBt');
form.addEventListener('submit', function(event) {
    
    let hiddenInput = document.createElement('input') || forms[i].querySelector('input[name="btAction"]');
    hiddenInput.type = 'hidden';
    hiddenInput.name = 'btAction';
    hiddenInput.value = 'saveCart';
    
    var formData = new URLSearchParams(new FormData(form));

    fetch('./FunctionDispatchServlet', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        var message = saveCartDiv.createElement('p');
        message.class = 'message';
        message.textContent = 'Save cart successfully';
        saveCartDiv.appendChild(message);
    })
    .catch((error) => console.error('Error:', error));
});

const navigationEntries = performance.getEntriesByType('navigation');

if (navigationEntries.length > 0) {
    const navigationType = navigationEntries[0].type;

    if (navigationType === 'reload') {
        saveCart();
        console.log('reload');
    }
}

window.addEventListener('popstate', function(event) {
    if (event.state === null) {
        saveCart();
        console.log('popstate');
    }
});

window.addEventListener('beforeunload', function () {saveCart(); console.log('beforeUnload')});

function saveCart () {
    var form = document.querySelector('form.submitOnReload');
    form.submit();
}