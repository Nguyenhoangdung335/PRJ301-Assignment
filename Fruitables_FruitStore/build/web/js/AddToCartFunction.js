var forms = document.querySelectorAll('form.addToCartFunction');

for (let i = 0; i < forms.length; i++) {
    var btActionBt = forms[i].querySelector('button.addToCartBt');
    btActionBt.addEventListener('click', function(event) {
        event.stopPropagation();
        event.preventDefault();

        let hiddenInput = document.createElement('input') || forms[i].querySelector('input[name="btAction"]');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'btAction';
        hiddenInput.value = btActionBt.value;

        forms[i].appendChild(hiddenInput);
        
        var formData = new URLSearchParams(new FormData(forms[i]));

        fetch('./FunctionDispatchServlet', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
                document.getElementById('cart_item_num').textContent = data.cartItemNum;
                messageAppear(data.message);
                console.log(data.message);
        })
        .catch((error) => console.error('Error:', error));
    });
}