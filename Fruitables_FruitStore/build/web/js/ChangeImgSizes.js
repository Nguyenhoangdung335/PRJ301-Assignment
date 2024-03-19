window.onload = function() {
    var containers = document.querySelectorAll('div.imgContainer');
    containers.forEach((container) => {
        container.style.display = 'flex';
        container.style.flexDirection = 'column';
        container.style.justifyContent = 'center';
        var img = container.querySelector('img');
        if (img.width > container.offsetWidth && img.height <= container.offsetHeight) {
            img.style.width = "100%";
            img.style.margin = "auto 0";
        } else if (img.height > container.offsetHeight && img.width <= container.offsetWidth) {
            img.style.height = "100%";
            img.style.margin = "0 auto";
        } else {
            img.style.width = "100%";
        }
    });
};