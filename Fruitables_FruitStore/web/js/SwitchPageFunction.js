var switchPageBt = document.querySelectorAll('button.switch_page');
var typeField = document.querySelector('input#mainTypeField[name="type"][type="hidden"]');
var valueField = document.querySelector('input#mainValueField[name="txtSearchValue"][type="search"]');
var btActionField = document.querySelector('input#switch_page_function[name="btAction"]');
switchPageBt.forEach((button) => {
    button.addEventListener('click', function (event) {
        var formData = '';
        formData += button.name + '=' + button.value + '&';
        formData += typeField.name + '=' + typeField.value + '&';
        formData += valueField.name + '=' + valueField.value + '&';
        formData += btActionField.name + '=' + btActionField.value + '&';
        formData = formData.slice(0, -1);
        
        window.location.href = '/DispatchServlet?' + formData;
    });
});