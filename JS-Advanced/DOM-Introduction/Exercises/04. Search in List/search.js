function search() {
   const towns = document.getElementById('towns').children;
   const result = document.getElementById('result');
   const searched = document.getElementById('searchText').value;
   let matches = 0;

   for (i = 0; i < towns.length; i++) {
      towns[i].style.fontWeight = 'none';
      towns[i].style.textDecoration = 'none';

      if (towns[i].textContent.includes(searched)) {
         towns[i].style.fontWeight = 'bold';
         towns[i].style.textDecoration = 'underline';
         matches ++;
      }
   }

   result.textContent = `${matches} matches found`;
}
