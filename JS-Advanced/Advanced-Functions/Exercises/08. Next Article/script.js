function getArticleGenerator(articles) {

    return () => {
        if (articles.length > 0) {
            let divContentElement = document.getElementById('content');
            let articleElement = document.createElement('article');
            let firstElement = articles.shift();
            articleElement.textContent = firstElement;
            divContentElement.appendChild(articleElement);
        }
    }
}
