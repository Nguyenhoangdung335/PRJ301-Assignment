var productRow = document.querySelectorAll('tr.productRow');
var form = document.querySelector('form.deleteCartFunction');

for (let i = 0; i < productRow.length; i++) {
    var btActionBt = productRow[i].querySelector('button.deleteCartBt');
    btActionBt.addEventListener('click', function(event) {
        event.preventDefault();
        
        var productID = productRow[i].querySelector('input[name="productID"]');
        
        var url = './FunctionDispatchServlet?btAction=deletecart&productID=' + productID.value;
        
        fetch(url, {
            method: 'POST',
        })
        .then(response => response.json())
        .then(data => {
                document.getElementById('cart_item_num').textContent = data.cartItemNum;
                productRow[i].remove();
                calculateFinalPrice();
                changeQuantity();
        })
        .catch((error) => console.error('Error:', error));
    });
}