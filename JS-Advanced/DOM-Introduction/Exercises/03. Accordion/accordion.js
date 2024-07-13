function toggle() {
    const button = document.getElementsByClassName('button')[0];
    let text = document.getElementById('extra');

    if (button.textContent == 'More') {
        text.style.display = 'block';
        button.textContent = 'Less';
    } else if (button.textContent == 'Less') {
        text.style.display = 'none';
        button.textContent = 'More';
    }
}
