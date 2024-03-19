//--------------Get containers
var imgContainer = document.querySelector('div#product_img');
var infoContainer = document.querySelector('div#product_info');
var descContainer = document.querySelector('div#review-description');
var reviewForm = document.querySelector('form#reviewForm');
//------------------------------------------------

//--------------Get all fields that can be changed
var pImage = imgContainer.querySelector('img');
var pId = infoContainer.querySelector('p#product_id');
var pName = infoContainer.querySelector('p#product_name');
var pCategory = infoContainer.querySelector('span#product_category');
var pPrice = infoContainer.querySelector('span#product_price');
var pStock = infoContainer.querySelector('span#product_in_stock');
var pDescription = infoContainer.querySelector('p#product_description');
var pType = infoContainer.querySelector('span#product_type');
var starDiv = infoContainer.querySelector('div#product_star');
var addForm = infoContainer.querySelector('form.addToCartFunction');
//------------------------------------------------
var adminStatus = document.querySelector('input#admin_status');
var viewType = document.querySelector('input#view_type');
//--------------Create fields for admin-------------
var changeImgLabel = document.createElement('label');
var changeImgField = document.createElement('textarea');
var inputId = pId.querySelector('input[name="productId"]');
var inputName = document.createElement('input');
var selectCategory = document.createElement('select');
var inputPrice = document.createElement('input');
var selectType = document.createElement('select');
var inputStock = document.createElement('input');
var inputDescription = document.createElement('textarea');
var hiddenActionInput = document.createElement('input');
var actionBt = document.createElement('button');

var type = '';

const navigationEntries = performance.getEntriesByType('navigation');
if (navigationEntries.length > 0) {
    const navigationType = navigationEntries[0].type;

    if (navigationType === 'reload') {
        var id = pId.querySelector('input').value;
        if (id === null || id.trim().length === 0)
            window.location.href = "/DispatchServlet?btAction=home";
        else 
            window.location.href = "/DispatchServlet?btAction=view&productID=" + id;
    }
}

window.addEventListener('load', function(event) {
    if (adminStatus !== null) {
        if (adminStatus.value.toLowerCase() === 'true' && viewType.value.toLowerCase() === 'update'){
            type = 'update';
            switchToAdmin();
            addValue();
        } else if (adminStatus.value.toLowerCase() === 'true' && viewType.value.toLowerCase() === 'new') {
            type = 'new';
            document.title = 'New Product';
            switchToAdmin();
        }
    }
});

var settingBt = document.querySelector('button.setting_button');
if (settingBt !== null) {
    settingBt.addEventListener('click', function(event) {
        var path = window.location.href;
        if (path.includes('view')) {
            var id = pId.querySelector('input').value;
            window.history.replaceState({}, "", "/ProductDetail?productID=" + id + "&type=update");
            type = 'update';
            switchToAdmin();
            addValue();
        } else if (path.includes('update')) {
            var id = pId.querySelector('input').value;
            window.location.href = "/DispatchServlet?btAction=view&productID=" + id;
        } else if (path.includes('new')) {
            window.location.href = "/DispatchServlet?btAction=view&productID=";
        }
    });
}



function switchToAdmin () {
    var typeList;
    var categoryList;
    async function fetchData() {
        try {
            const response = await fetch('GetProductTypeServlet');
            console.log('Response:', response);
            const data = await response.json();
            console.log('Data:', data);
            return data;
        } catch (error) {
            console.error('Error:', error);
        }
    }

    fetchData().then(data => {
        typeList = data.typeList;
        categoryList = data.categoryList;
        
        //--------------Create field to change img link
        changeImgLabel.textContent = 'Image link: ';
        changeImgLabel.setAttribute('for', 'imgField');
        changeImgLabel.classList.add('fw-bold');
        changeImgLabel.style.margin = '0.3em auto';
        changeImgLabel.style.display = 'block';
        changeImgLabel.style.textAlign = 'center';
        imgContainer.appendChild(changeImgLabel);
        
        changeImgField.name = 'txtImageLink';
        changeImgField.id = 'imgField';
        changeImgField.rows = '3';
        changeImgField.dataset.isSent = '1';
        changeImgField.classList.add('form-control', 'border-2');
        changeImgField.style.resize = 'none';
        changeImgField.setAttribute('required', 'true');
        changeImgField.addEventListener('change', function(event) {
            if (changeImgField.value.trim().length !== 0)
                pImage.src = changeImgField.value;
        });
        imgContainer.appendChild(changeImgField);
        
        //--------------Change all info fields into input tags
        pId.style.display = 'block';
        inputId.type = 'text';
        inputId.dataset.isSent = '1';
        inputId.setAttribute('readonly', 'true');
        inputId.classList.add('form-control', 'border-2');
        inputId.style.width = "20%";
        inputId.style.display = "inline-block";
        
        //  Name field  
        pName.classList.forEach((addedClass) => {
            inputName.classList.add(addedClass);
        });
        inputName.classList.add('form-control', 'border-2');
        if (pName.id !== null) inputName.id = pName.id;
        inputName.name = 'txtName';
        inputName.dataset.isSent = '1';
        pName.parentNode.replaceChild(inputName, pName);
        //-----------------

        //  Category field
        pCategory.classList.forEach((addedClass) => {
            selectCategory.classList.add(addedClass);
        });
        categoryList.forEach((category) => {
            var option = document.createElement('option');
            option.value = category.id;
            option.text = category.name;
            if (category.name === pCategory.textContent)
                option.setAttribute('selected', true);
            selectCategory.appendChild(option);
        });
        selectCategory.classList.add('form-select', 'border-2');
        selectCategory.style.display = 'inline';
        selectCategory.style.width = '50%';
        if (pCategory.id !== null) selectCategory.id = pCategory.id;
        selectCategory.name = 'slCategory';
        selectCategory.dataset.isSent = '1';
        pCategory.parentNode.replaceChild(selectCategory, pCategory);
        //-----------------

        //  Price field
        pPrice.classList.forEach((addedClass) => {
            inputPrice.classList.add(addedClass);
        });
        inputPrice.classList.add('form-control', 'border-2');
        inputPrice.style.display = 'inline';
        inputPrice.style.width = '30%';
        if (pPrice.id !== null) inputPrice.id = pPrice.id;
        inputPrice.type = 'number';
        inputPrice.min = '0';
        inputPrice.max = '100';
        inputPrice.name = 'txtPrice';
        inputPrice.dataset.isSent = '1';
        pPrice.parentNode.replaceChild(inputPrice, pPrice);
        //-----------------

        //  Product Type field
        pType.classList.forEach((addedClass) => {
            selectType.classList.add(addedClass);
        });
        typeList.forEach((type) => {
            var option = document.createElement('option');
            option.value = type.typeId;
            option.text = type.name;
            if (type.name === pType.textContent)
                option.setAttribute('selected', true);
            selectType.appendChild(option);
        });
        selectType.classList.add('form-select', 'border-2');
        selectType.style.display = 'inline';
        selectType.style.width = '30%';
        if (pType.id !== null) selectType.id = pType.id;
        selectType.name = 'slType';
        selectType.dataset.isSent = '1';
        pType.parentNode.replaceChild(selectType, pType);
        //-----------------

        // Stock field
        pStock.classList.forEach((addedClass) => {
            inputStock.classList.add(addedClass);
        });
        inputStock.classList.add('form-control', 'border-2');
        inputStock.style.display = 'inline';
        inputStock.style.width = '50%';
        if (pStock.id !== null) inputStock.id = pStock.id;
        inputStock.type = 'number';
        inputPrice.min = '0';
        inputPrice.max = '50000';
        inputStock.name = 'txtStock';
        inputStock.dataset.isSent = '1';
        pStock.parentNode.replaceChild(inputStock, pStock);
        //-----------------

        //  Description field
        pDescription.classList.forEach((addedClass) => {
            inputDescription.classList.add(addedClass);
        });
        inputDescription.classList.add('form-control', 'border-2');
        if (pDescription.id !== null) inputDescription.id = pDescription.id;
        inputDescription.rows = '4';
        inputDescription.cols = '20';
        inputDescription.style.resize = 'none';
        inputDescription.name = 'txtDescription';
        inputDescription.dataset.isSent = '1';
        pDescription.parentNode.replaceChild(inputDescription, pDescription);
        //-----------------
        
        //---------------ACTION BUTTON-------------------
        hiddenActionInput.type = 'hidden';
        hiddenActionInput.name = 'btAction';
        if (type === 'update') 
            hiddenActionInput.value = 'updateproduct';
        else if (type === 'new')
            hiddenActionInput.value = 'createproduct';
        hiddenActionInput.dataset.isSent = '1';
        infoContainer.appendChild(hiddenActionInput);
        
        
        actionBt.classList.add('btn', 'border', 'border-secondary', 'rounded-pill', 'px-3', 'text-primary');
        actionBt.id = 'updateBt';
        actionBt.name = 'btAction';
        if (type === 'update') {
            actionBt.value = 'updateproduct';
            actionBt.textContent = 'Update Product';
        } else if (type === 'new') {
            actionBt.value = 'createproduct';
            actionBt.textContent = 'Create Product';
        }
        actionBt.addEventListener('click', function(event) {
            event.preventDefault();
    
            let isEmptyField = false;
            var formInputs = document.querySelectorAll('[data-is-sent="1"]');
            var formData = '';
            formInputs.forEach((input) => {
                let name = encodeURIComponent(input.name);
                let value = encodeURIComponent(input.value);
                if (type === 'update' || (type === 'new' && name !== 'productId')) {
                    if (value.trim().length === 0) isEmptyField = true;
                    formData += name + "=" + value + "&";
                }
            });
            if (isEmptyField) {
                messageAppear('Data must not be empty, please try again');
                return;
            }
            formData = formData.slice(0, -1);
            console.log('FormData: ' + formData);

            fetch('./FunctionDispatchServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                messageAppear(data.message);
            })
            .catch((error) => console.error('Error:', error));
        });
        infoContainer.appendChild(actionBt);

        //  Delete star, quantity, addToCart button, review container
        starDiv.remove();
        addForm.remove();
        descContainer.remove();
        reviewForm.remove();
    });
}

function addValue () {
    changeImgField.value = pImage.src;
    inputName.value = pName.textContent;
    inputPrice.value = pPrice.textContent;
    inputStock.value = pStock.textContent;
    inputDescription.value = pDescription.textContent;
}