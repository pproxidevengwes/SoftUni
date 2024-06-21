function solve() {
    const inputTask = document.querySelector('#task');
    const inputDescr = document.querySelector('#description');
    const inputDate = document.querySelector('#date');
    const [sect1, sect2, sect3, sect4] = document.querySelectorAll('section');
    const addBtn = document.querySelector('#add');

    addBtn.addEventListener('click', addToDo);

    function addToDo(e) {
        e.preventDefault();
        if (inputTask.value.trim() === '' || inputDescr.value.trim() === '' || inputDate.value.trim() === '') {
            return
        }

        const article = document.createElement('article');
        const h3 = document.createElement('h3');
        h3.textContent = inputTask.value;

        const pDescr = document.createElement('p');
        pDescr.textContent = `Description: ${inputDescr.value}`;

        const pDate = document.createElement('p');
        pDate.textContent = `Due Date: ${inputDate.value}`;

        const divBtn = document.createElement('div');
        divBtn.classList.add('flex');

        const startBtn = document.createElement('button');
        startBtn.classList.add('green');
        startBtn.textContent = 'Start';
        startBtn.addEventListener('click', startArticle);

        const deleteBtn = document.createElement('button');
        deleteBtn.classList.add('red');
        deleteBtn.textContent = 'Delete';
        deleteBtn.addEventListener('click', deleteArticle);

        divBtn.appendChild(startBtn);
        divBtn.appendChild(deleteBtn);
        article.appendChild(h3);
        article.appendChild(pDescr);
        article.appendChild(pDate);
        article.appendChild(divBtn);
        sect2.lastElementChild.appendChild(article);

        function deleteArticle() {
            article.remove();
        }

        function startArticle() {
            startBtn.remove();
            const finishBtn = document.createElement('button');
            finishBtn.classList.add('orange');
            finishBtn.textContent = 'Finish';
            finishBtn.addEventListener('click', finishArticle);
            divBtn.appendChild(finishBtn);
            sect3.lastElementChild.appendChild(article);
        }

        function finishArticle() {
            divBtn.remove();
            sect4.lastElementChild.appendChild(article);
        }
    }
}
