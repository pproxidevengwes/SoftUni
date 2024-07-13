function solution() {
    let productData = [];
    let sections = document.querySelectorAll(".card");
    let listOfGifts = sections[1].children[1];
    let sent = sections[2].children[1];
    let discarded = sections[3].children[1];

    document.querySelector("button").addEventListener("click", (e) => {
        let productName = document.querySelector("input[type=text]");

        if (!productName.value) {
            return;
        }

        listOfGifts.innerHTML = "";

        productData.push(productName.value); // BBB  AAA

        productData.sort((a, b) => a.localeCompare(b))
            .forEach((product) => {
                let productElement = createProduct(product);

                let sendBtn = generateButton("id", "sendButton", "Send");
                let discardBtn = generateButton("id", "discardButton", "Discard");

                productElement.appendChild(sendBtn);
                productElement.appendChild(discardBtn);

                listOfGifts.appendChild(productElement);
            });
        listOfGifts.addEventListener("click", moveProducts);

        productName.value = "";
    });

    function moveProducts(e) {
        if (e.target.id === "sendButton") {
            moveToSection(e, sent);
        }

        if (e.target.id === "discardButton") {
            moveToSection(e, discarded);
        }
    }

    function createProduct(input) {
        let productElement = document.createElement("li");
        productElement.classList.add("gift");
        productElement.textContent = input;
        return productElement;
    }

    function generateButton(attrName, attrValue, textContent) {
        let button = document.createElement("button");
        button.setAttribute(attrName, attrValue);
        button.textContent = textContent;
        return button;
    }

    function remove(array, item) {
        var index = array.indexOf(item);

        if (index !== -1) {
            array.splice(index, 1);
        }
    }

    function moveToSection(e, section) {
        let liTextContent = e.target.parentNode.textContent
            .split("SendDiscard").map((x) => x.trim()).join("");

        let li = createProduct(liTextContent);
        e.target.parentNode.remove();
        remove(productData, liTextContent);
        section.appendChild(li);
    }
}
