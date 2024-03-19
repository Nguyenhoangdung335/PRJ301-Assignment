var decreaseBt = document.querySelector('button#decrease_quantity');
var increaseBt = document.querySelector('button#increase_quantity');
var numInput = document.querySelector('input#product_quantity');

decreaseBt.addEventListener('click', function(event) {
    let num = parseInt(numInput.value);
    if (num > 0) {
        numInput.value = num - 1;
    }
});

increaseBt.addEventListener('click', function(event) {
    let num = parseInt(numInput.value);

    numInput.value = num + 1;
});