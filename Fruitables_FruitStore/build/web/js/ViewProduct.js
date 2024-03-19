var itemContainers = document.querySelectorAll('form.viewProduct');

itemContainers.forEach(function(item) {
    item.addEventListener('click', function(event) {
        var productID = item.querySelector('input[name="productID"]');
        window.location.href = "DispatchServlet?btAction=view&productID=" + productID.value;
    });
});