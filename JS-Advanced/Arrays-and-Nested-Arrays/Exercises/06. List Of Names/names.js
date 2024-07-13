function solve(arr) {
    let result = arr.sort((a, b) => a.localeCompare(b));
  
    for (const name of result) {
        console.log(`${result.indexOf(name) + 1}.${name}`);
    }
}

solve(["John", "Bob", "Christina", "Ema"]);
