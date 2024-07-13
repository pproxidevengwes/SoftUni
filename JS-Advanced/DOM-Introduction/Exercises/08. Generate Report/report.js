function generateReport() {
    const output = document.getElementById('output');
    const rows = Array.from(document.querySelectorAll('tbody tr'));
    const cols = Array.from(document.querySelectorAll('thead tr th'));
    let result = [];
 
    for (let i = 0; i < rows.length; i++) {
        let current = {}; 
        for (let j = 0; j < cols.length; j++) {
            if (cols[j].firstElementChild.checked) {
                current[cols[j].firstElementChild.name] = rows[i].children[j].textContent;
            }
        }
 
        if (current){
            result.push(current);
        }
    }
    output.innerHTML = JSON.stringify(result);
}
