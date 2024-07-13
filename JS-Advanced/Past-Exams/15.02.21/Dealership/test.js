const { expect, assert } = require('chai')
const dealership = require('./dealership')

describe('tests for dealership', () => {
    
    describe('tests for newCarCost()', () => {
        it('old car not in list discount', () => {
            expect(dealership.newCarCost('Lada Niva', 33333)).to.equal(33333)
        })
        it('new car price discount', () => {
            expect(dealership.newCarCost('Audi A4 B8', 35000)).to.equal(20000)
        })
    });

    describe('tests for carEquipment()', () => {
        it('add extras', () => {
            const extras = ['heated seats', 'sliding roof', 'carbon fiber'];
            const wanted = [0, 2]
            expect(JSON.stringify(dealership.carEquipment(extras, wanted))).to.be.equal(JSON.stringify(['heated seats', 'carbon fiber']));
        });
    });

    describe('tests for euroCategory', () => {
        it('add discount', () => {
            expect(dealership.euroCategory(4)).to.be.equal('We have added 5% discount to the final price: 14250.');
        });

        it('no discount', () => {
            expect(dealership.euroCategory(3)).to.be.equal('Your euro category is low, so there is no discount from the final price!');
        });
    });
});
