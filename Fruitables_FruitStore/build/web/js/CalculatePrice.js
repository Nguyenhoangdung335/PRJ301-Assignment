//var inputdocument.querySelectorAll('input[class="txtQuantity"]')
var rows = document.querySelectorAll('tr.productRow');
var selectShipType = document.querySelector('select#shipping_type');

selectShipType.addEventListener('change', function() {
    var finalPrice = document.querySelector('p[id="finalPrice"]');
    var totalPrice = document.querySelector('p[id="totalPrice"]');
    var selectedOption = this.options[this.selectedIndex];
    let price = selectedOption.getAttribute("data-price");
    
    let newfinalPrice = (parseFloat(totalPrice.textContent) + parseFloat(price));
    
    finalPrice.textContent = newfinalPrice.toFixed(2);
});

rows.forEach(function(row) {
    var quantityInput = row.querySelector('input[name="txtQuantity"]');
    var price = row.querySelector('p.txtPrice');
    var subTotal = row.querySelector('p.txtSubTotal');
    
    let newSubTotal = parseFloat(quantityInput.value) * parseFloat(price.textContent);
    subTotal.textContent = newSubTotal.toFixed(2);
    chooseCoupon(row);
    calculateFinalPrice();
    changeQuantity();
    
    quantityInput.addEventListener('input', function(event) {
        let newSubTotal = parseFloat(quantityInput.value) * parseFloat(price.textContent);
        subTotal.textContent = newSubTotal.toFixed(2);
        chooseCoupon(row);
        calculateFinalPrice();
        changeQuantity();
    });
});

function makeChange (quantity, price, subTotalField, row) {
    let newSubTotal = parseFloat(quantity) * parseFloat(price);
    subTotalField.textContent = newSubTotal.toFixed(2);
    chooseCoupon(row);
    calculateFinalPrice();
    changeQuantity();
}


function calculateFinalPrice () {
    var totalPrice = document.querySelector('p[id="totalPrice"]');
    var finalPrice = document.querySelector('p[id="finalPrice"]');
    var subTotals = document.querySelectorAll('p.txtSubTotal');
    
    let newTotalPrice = 0;
    subTotals.forEach(function(subTotal) {
        newTotalPrice += parseFloat(subTotal.textContent);
    });
    totalPrice.textContent = newTotalPrice.toFixed(2);
    finalPrice.textContent = (newTotalPrice + 20).toFixed(2);
}

function changeQuantity () {
    var cartNum = document.querySelector('span#cart_item_num');
    var totalQuantityField = document.querySelector('p#totalQuantity');
    var quantityFields = document.querySelectorAll('input[name="txtQuantity"]');
    
    let totalQuantity = 0;
    quantityFields.forEach(function (quantity) {
        totalQuantity += parseFloat(quantity.value);
    });
    
    cartNum.textContent = totalQuantity.toFixed(1);
    totalQuantityField.textContent = totalQuantity.toFixed(1);
}

function chooseCoupon (row) {
    var quantityInput = row.querySelector('input[name="txtQuantity"]');
    var subTotal = row.querySelector('p.txtSubTotal');
    var viewCoupon = row.querySelector('p.coupon_field');
    var chosenCoupon = row.querySelector('input.chosen_coupon[name="configID"]');
    var hiddenCoupon = row.querySelectorAll('input.variation[type="hidden"]');
    var nameValue = Array.prototype.map.call(hiddenCoupon, function(input) {
        return parseFloat(input.name);
    });
    
    let quantity = parseFloat(quantityInput.value);
    var hasQualify = false;
    for (let name of nameValue) {
        if (quantity >= name) {
            var qualifyCoupon = row.querySelector('input.variation[type="hidden"][name="' + name + '.0"]');
            let percentage = parseFloat(qualifyCoupon.value);
            viewCoupon.textContent = ((1 - percentage) * 100).toFixed(0) + "%";
            viewCoupon.dataset.value = name;
            viewCoupon.dataset.percent = percentage;
            viewCoupon.dataset.id = parseInt(qualifyCoupon.dataset.id);
            chosenCoupon.value = parseInt(qualifyCoupon.dataset.id);
            hasQualify = true;
        }
    }
    if (!hasQualify) {
        viewCoupon.textContent = "0%";
        viewCoupon.dataset.value = 0;
        viewCoupon.dataset.percent = 1;
        viewCoupon.dataset.id = -1;
        chosenCoupon.value = -1;
    }
    
    subTotal.textContent = (parseFloat(subTotal.textContent) * parseFloat(viewCoupon.dataset.percent)).toFixed(2);
}