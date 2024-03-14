var form = document.querySelector('form.saveCartForm');
var saveCartBt = document.querySelector('button.saveCartBt');
var save_message = document.querySelector('p.save_message');

saveCartBt.addEventListener('click', function(event) {
    var user = document.querySelector('a#user_username');
    if (user === null) {
        alert('Please log in to save shopping cart');
    } else if (IsEmpty()) {
        alert('There is no item to save, please try again with item in cart');
    } else {
        event.preventDefault();

        let hiddenInput = document.createElement('input') || form.querySelector('input[name="btAction"]');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'btAction';
        hiddenInput.value = saveCartBt.value;
        hiddenInput.dataset.isSent = 1;
        form.appendChild(hiddenInput);

        var isSentInput = form.querySelectorAll('input[data-is-sent="1"]');
        var formData = new FormData();
        for (let input of isSentInput) {
            let name = input.name;
            let value = input.value;
            if (name === 'txtQuantity' && parseFloat(value) <= 0) {
                alert('Quantity must not be zero when saving cart, please try again!');
                return;
            }            
            formData.append(name, value);
        }

        formData = new URLSearchParams(formData);

        fetch('./FunctionDispatchServlet', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
        })
        .catch((error) => console.error('Error:', error));
    }
});

function IsEmpty () {
    var rows = document.querySelectorAll('tr.productRow');
    return rows.length === 0;
}