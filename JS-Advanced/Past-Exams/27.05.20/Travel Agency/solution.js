window.addEventListener('load', solution);

function solution() {
  let fullNameInput = document.getElementById('fname');
  let emailInput = document.getElementById('email');
  let phoneNumberInput = document.getElementById('phone');
  let addressInput = document.getElementById('address');
  let postalCodeInput = document.getElementById('code');

  let submitBtn = document.getElementById('submitBTN');
  submitBtn.addEventListener('click', submitData);
  let editBtn = document.getElementById('editBTN');
  editBtn.addEventListener('click', editInformation);
  let continueBtn = document.getElementById('continueBTN');
  continueBtn.addEventListener('click', continueFunctionality);

  function submitData() {
    let fullName = fullNameInput.value;
    let email = emailInput.value;
    let phoneNumber = phoneNumberInput.value;
    let address = addressInput.value;
    let postalCode = postalCodeInput.value;

    fullNameInput.value = '';
    emailInput.value = '';
    phoneNumberInput.value = '';
    addressInput.value = '';
    postalCodeInput.value = '';

    if (fullName.trim() !== '' && email.trim() !== '') {
      let infoPreview = document.getElementById('infoPreview');

      let fullNameLi = document.createElement('li');
      fullNameLi.textContent = `Full Name: ${fullName}`;
      let emailLi = document.createElement('li');
      emailLi.textContent = `Email: ${email}`;
      let phoneNumberLi = document.createElement('li');
      phoneNumberLi.textContent = `Phone Number: ${phoneNumber}`;
      let addressLi = document.createElement('li');
      addressLi.textContent = `Address: ${address}`;
      let postalCodeli = document.createElement('li');
      postalCodeli.textContent = `Postal Code: ${postalCode}`

      infoPreview.appendChild(fullNameLi);
      infoPreview.appendChild(emailLi);
      infoPreview.appendChild(phoneNumberLi);
      infoPreview.appendChild(addressLi);
      infoPreview.appendChild(postalCodeli);

      submitBtn.disabled = true;
      editBtn.disabled = false;
      continueBtn.disabled = false;
    }
  }

  function editInformation(e) {
    let ulElement = e.target.parentElement.parentElement;
    let ulArray = Array.from(ulElement.querySelectorAll('li'))

    document.getElementById('infoPreview').textContent = '';

    let fullName = ulArray[0].textContent.split(': ')[1];
    let email = ulArray[1].textContent.split(': ')[1];
    let phoneNumber = ulArray[2].textContent.split(': ')[1];
    let address = ulArray[3].textContent.split(': ')[1];
    let postalCode = ulArray[4].textContent.split(': ')[1];

    fullNameInput.value = fullName;
    emailInput.value = email;
    phoneNumberInput.value = phoneNumber;
    addressInput.value = address;
    postalCodeInput.value = postalCode;

    submitBtn.disabled = false;
    editBtn.disabled = true;
    continueBtn.disabled = true;
  }

  function continueFunctionality(e) {
    let blockElement = document.getElementById('block');
    blockElement.innerHTML = '';
    
    let thankYouElement = document.createElement('h3');
    thankYouElement.textContent = "Thank you for your reservation!";
    blockElement.appendChild(thankYouElement);
  }
}
