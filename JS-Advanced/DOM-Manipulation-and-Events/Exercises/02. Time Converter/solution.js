function attachEventsListeners() {
    let daysBtn = document.getElementById('daysBtn');
    let hoursBtn = document.getElementById('hoursBtn');
    let minutesBtn = document.getElementById('minutesBtn');
    let secondsBtn = document.getElementById('secondsBtn');

    let daysInput = document.getElementById('days');
    let hoursInput = document.getElementById('hours');
    let minutesInput = document.getElementById('minutes');
    let secondsInput = document.getElementById('seconds');

    daysBtn.addEventListener('click', () => {
        secondsInput.value = daysInput.value * (24 * 60 * 60);
        hoursInput.value = secondsInput.value / (60 * 60);
        minutesInput.value = secondsInput.value / 60;
    });

    hoursBtn.addEventListener('click', () => {
        secondsInput.value = hoursInput.value * (60 * 60);
        daysInput.value = secondsInput.value / (24 * 60 * 60);
        minutesInput.value = secondsInput.value / 60;
    });

    minutesBtn.addEventListener('click', () => {
        secondsInput.value = minutesInput.value * 60;
        daysInput.value = secondsInput.value / (24 * 60 * 60);
        hoursInput.value = secondsInput.value / (60 * 60);
    });

    secondsBtn.addEventListener('click', () => {
        minutesInput.value = secondsInput.value / 60;
        hoursInput.value = secondsInput.value / (60 * 60);
        daysInput.value = secondsInput.value / (60 * 60 * 24);
    });
}
