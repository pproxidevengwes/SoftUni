function odds(numbers) {

    console.log(numbers
        .filter((v, i) => i % 2 == 1)
        .map(x => x * 2)
        .reverse()
        .join(' '));
}

odds([10, 15, 20, 25]);
odds([3, 0, 10, 4, 7, 3]);
