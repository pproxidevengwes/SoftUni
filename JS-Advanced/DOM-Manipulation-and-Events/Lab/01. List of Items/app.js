function addItem() {
    let ulElement = document.getElementById('items');
    let input = document.getElementById('newItemText');
    let newElement = document.createElement('li');
    newElement.textContent = input.value;
    ulElement.appendChild(newElement);
}
