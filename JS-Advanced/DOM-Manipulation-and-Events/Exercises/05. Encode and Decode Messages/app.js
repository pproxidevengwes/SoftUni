function encodeAndDecodeMessages() {
    let encodeText = document.getElementsByTagName('textarea')[0];
    let decodeText = document.getElementsByTagName('textarea')[1];
    let encodeButton = document.getElementsByTagName('button')[0];
    let decodeButton = document.getElementsByTagName('button')[1];

    encodeButton.addEventListener('click', encode);
    decodeButton.addEventListener('click', decode);

    function encode(ev) {
        const result = [];

        for (let ch of encodeText.value) {
            result.push(String.fromCharCode(ch.charCodeAt(0) + 1));
        }
        decodeText.value = result.join('');
        encodeText.value = '';
    }

    function decode(ev) {
        const result = [];
        
        for (let ch of decodeText.value) {
            result.push(String.fromCharCode(ch.charCodeAt(0) - 1));
        }
        decodeText.value = result.join('');
    }
}
