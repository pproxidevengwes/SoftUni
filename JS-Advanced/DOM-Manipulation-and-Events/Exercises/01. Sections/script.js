function create(words) {
   let content = document.getElementById('content');

   for (const word of words) {
      let div = document.createElement('div');
      let p = document.createElement('p');
      p.innerHTML = word;
      p.style.display = 'none';
      div.appendChild(p);
      div.addEventListener('click', onClick);
      content.appendChild(div);
   }
   
   function onClick(ev) {
      ev.target.children[0].style.display = 'block';
   }
}
