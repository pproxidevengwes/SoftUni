function lockedProfile() {
    const buttons = Array.from(document.querySelectorAll('button'));
    buttons.forEach(btn => btn.addEventListener('click', showMore));

    function showMore(ev) {
        const locked = ev.target.parentNode.getElementsByTagName('input')[0];
        const more = ev.target.parentNode.getElementsByTagName('div')[0];
        if (locked.checked == false && ev.target.textContent === 'Show more') {
            more.style.display = 'block';
            ev.target.textContent = 'Hide it';
        } else if (locked.checked == false && ev.target.textContent === 'Hide it') {
            more.style.display = 'none';
            ev.target.textContent = 'Show more';
        }
    }
}
