function attachEventsListeners() {
    const ratios = {
        km: 0.001,
        m: 1,
        cm: 100,
        mm: 1000,
        mi: (1 / 1609.34),
        yrd: (1 / 0.9144),
        ft: (1 / 0.3048),
        in: (1 / 0.0254)
    }

    function convert(value, unit) {
        const inMeters = value / ratios[unit];
        return {
            km: inMeters * ratios.km,
            m: inMeters,
            cm: inMeters * ratios.cm,
            mm: inMeters * ratios.mm,
            mi: inMeters * ratios.mi,
            yrd: inMeters * ratios.yrd,
            ft: inMeters * ratios.ft,
            in: inMeters * ratios.in
        }
    }

    let inputElement = document.getElementById('inputDistance');

    let outputElement = document.getElementById('outputDistance');

    let inputOptions = document.querySelector('#inputUnits');
    let outputOptions = document.querySelector('#outputUnits');

    let convertButton = document.getElementById('convert');

    convertButton.addEventListener('click', onClick);

    function onClick(e) {
        let convertFrom = inputOptions.value;
        let convertTo = outputOptions.value;
        let inputDistance = inputElement.value;

        const outputDistance = convert(Number(inputDistance), convertFrom);
        outputElement.value = outputDistance[convertTo];
    }
}
