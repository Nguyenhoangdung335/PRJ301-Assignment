var form = document.querySelector('form.makeOrderForm');
var makeOrderBt = document.querySelector('button#makeOrderBt');
var message = document.querySelector('p.save_message');

document.querySelectorAll('tr.productRow').forEach(function(row){
    var subTotalField = row.querySelector('p.txtSubTotal');
    let numStr = subTotalField.textContent.replace(/[^0-9.]/g, "");
    
    var hiddenSubTotal = document.createElement('input');
    hiddenSubTotal.type = 'hidden';
    hiddenSubTotal.name = 'subTotal';
    hiddenSubTotal.value = parseFloat(numStr);
    hiddenSubTotal.dataset.isSent = 1;
    row.appendChild(hiddenSubTotal);
});

var finalPriceField = document.querySelector('p#finalPrice');
let numStr = finalPriceField.textContent.replace(/[^0-9.]/g, "");

var hiddenFinalPrice = document.createElement('input');
hiddenFinalPrice.type = 'hidden';
hiddenFinalPrice.name = 'finalPrice';
hiddenFinalPrice.value = parseFloat(numStr);
hiddenFinalPrice.dataset.isSent = 1;
finalPriceField.appendChild(hiddenFinalPrice);


makeOrderBt.addEventListener('click', function(event) {
    var user = document.querySelector('a#user_username');
    if (user === null) {
        alert('Please log in to make order');
    } else if (IsEmpty()) {
        alert('There is no item to make order, please try again with item in cart');
    } else {
        event.preventDefault();
        
        var btActionInput = form.querySelector('input[name="btAction"]');
        if (btActionInput === null) {
            let hiddenInput = document.createElement('input');
            hiddenInput.type = 'hidden';
            hiddenInput.name = 'btAction';
            hiddenInput.value = makeOrderBt.value;
            hiddenInput.dataset.isSent = 1;
            form.appendChild(hiddenInput);
        } else {
            btActionInput.value = makeOrderBt.value;
        }

        var isSentInput = document.querySelectorAll("input[data-is-sent=\"1\"], select[data-is-sent=\"1\"]");
        var formData = '';
        for (let input of isSentInput) {
            let name = encodeURIComponent(input.name);
            let value = encodeURIComponent(input.value);
            if (name === 'txtQuantity' && parsefloat(value) <= 0) {
                alert('Quantity must not be zero when making order, please try again!');
                return;
            }
            formData += name + '=' + value + '&';
        }
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
            alert(data.message);
            if (data.state === 'success') {
                clearAllProduct();
                window.scrollTo(0, 0);
            }
        })
        .catch((error) => console.error('Error:', error));
    }
});

function clearAllProduct () {
    var rows = document.querySelectorAll('tr.productRow');
    rows.forEach(function(row){
        row.remove();
        changeQuantity();
        calculateFinalPrice();
    });
}