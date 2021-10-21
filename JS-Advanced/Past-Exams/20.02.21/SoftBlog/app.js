function solve() {
   let createBtn = document.querySelector('.create');
   createBtn.addEventListener('click', createArticle);

   function createArticle(e) {
      e.preventDefault();
      let postsWrapperElement = e.currentTarget.parentElement.parentElement.parentElement.parentElement;
      let mainSection = postsWrapperElement.querySelector('main section');

      let creatorName = postsWrapperElement.querySelector('#creator');
      let creator = creatorName.value;

      let titleName = postsWrapperElement.querySelector('#title');
      let title = titleName.value;

      let categoryName = postsWrapperElement.querySelector('#category');
      let category = categoryName.value;

      let contentName = postsWrapperElement.querySelector('#content');
      let content = contentName.value;

      let article = document.createElement('article');

      let h1 = document.createElement('h1');
      h1.textContent = title;
      article.appendChild(h1);

      let categoryParagraph = document.createElement('p');
      categoryParagraph.textContent = 'Category:'

      let categoryStrong = document.createElement('strong');
      categoryStrong.textContent = category;
      categoryParagraph.appendChild(categoryStrong);
      article.appendChild(categoryParagraph);

      let creatorParagraph = document.createElement('p');
      creatorParagraph.textContent = 'Creator:'

      let creatorStrong = document.createElement('strong');
      creatorStrong.textContent = creator;
      creatorParagraph.appendChild(creatorStrong);
      article.appendChild(creatorParagraph);

      let contentParagraph = document.createElement('p');
      contentParagraph.textContent = content;
      article.appendChild(contentParagraph);

      let div = document.createElement('div');
      div.className = 'buttons';

      let deleteBtn = document.createElement('button');
      deleteBtn.className = 'btn delete';
      deleteBtn.textContent = 'Delete';
      div.appendChild(deleteBtn);

      let archiveBtn = document.createElement('button');
      archiveBtn.className = 'btn archive';
      archiveBtn.textContent = 'Archive';
      div.appendChild(archiveBtn);

      article.appendChild(div);

      mainSection.appendChild(article);

      deleteBtn.addEventListener('click', deleteContent);
      archiveBtn.addEventListener('click', archiveContent);

      creatorName.value = '';
      titleName.value = '';
      categoryName.value = '';
      contentName.value = '';

   }

   function deleteContent(e) {
      let article = e.target.parentElement.parentElement;
      article.remove();
   }

   function archiveContent(e) {
      let article = e.target.parentElement.parentElement;
      let title = article.querySelector('h1');

      let parrentElement = e.target.parentElement.parentElement.parentElement.parentElement.parentElement;
      let archiveSection = parrentElement.querySelector('.archive-section');
      let ol = archiveSection.querySelector('ol');

      let li = document.createElement('li');
      li.textContent = title.textContent;
      ol.appendChild(li);

      ol = Array.from(ol.querySelectorAll('li'))
         .sort((a, b) => (a.textContent).localeCompare(b.textContent))
         .forEach(li => ol.appendChild(li));

      article.remove();
   }

}
