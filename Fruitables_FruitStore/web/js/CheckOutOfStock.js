window.addEventListener('load', function(event) {
    var outOfStockObjects = document.querySelectorAll('.check_out_of_stock');
    var path = window.location.pathname;
    
    if (path.includes('/ProductDetail')) {
        console.log(window.location.href);
        outOfStockObjects.forEach((object) => {
            var productImg = object.querySelector('div#product_img').querySelector('div.border.rounded');
            var productInfo = object.querySelector('div#product_info');
            checkStock(productInfo, productImg);
        }); 
    } else {
        outOfStockObjects.forEach((object) => checkStock(object, object));
    }
});

function checkStock (infoContainer, appliedOutOfStock) {
    var stockField = infoContainer.querySelector('.stockField');
    var addToCartBt = infoContainer.querySelector('button.addToCartBt[name="btAction"]');
    
    if (parseFloat(stockField.textContent) <= 0.0) {
        appliedOutOfStock.classList.add('out_of_stock');
        addToCartBt.setAttribute('disabled', true);
        addToCartBt.style.pointerEvents = 'auto';
        addToCartBt.style.cursor = 'not-allowed';
    }
}