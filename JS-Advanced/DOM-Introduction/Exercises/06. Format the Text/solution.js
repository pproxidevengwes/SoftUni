function solve() {
  let text = document.getElementById('input').value;
  let sentances = text.split('.').map(x => x + '.');
  sentances.pop();
  let paragraph = [];

  while (sentances.length > 0) {
    let result = sentances.splice(0, 3).join('');
    paragraph.push(`<p>${result}</p>`);
  }
  let output = document.getElementById('output');
  output.innerHTML = paragraph;
}
