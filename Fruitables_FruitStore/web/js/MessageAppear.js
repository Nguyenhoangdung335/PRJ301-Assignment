// Message div initilize
//

function messageAppear (message) {
    var MessageDiv = document.createElement('div');
    MessageDiv.classList.add("speech-bubble");
    MessageDiv.classList.add("round");
    MessageDiv.classList.add("t");
    MessageDiv.classList.add("message");
    MessageDiv.classList.add("appears");
    MessageDiv.addEventListener('animationend', () => MessageDiv.classList.add('float'));
    MessageDiv.addEventListener('mouseover', () => MessageDiv.classList.add('prompt_remove') );
    MessageDiv.addEventListener('mouseout', () => MessageDiv.classList.remove('prompt_remove') );
    MessageDiv.addEventListener('click', () => {
        MessageDiv.classList.remove('float');
        MessageDiv.classList.remove('appears');
        MessageDiv.classList.add('disappears');
        MessageDiv.addEventListener('animationend', () => MessageDiv.remove());
    });
    
    MessageDiv.textContent = message;
    document.body.appendChild(MessageDiv); 
}