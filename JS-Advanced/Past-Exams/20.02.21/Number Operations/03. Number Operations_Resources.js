const { expect } = require('chai');
const { numberOperations } = require('./03. Number Operations_Resources');

describe('tests for numberOperations', () => {

    describe('tests for powNumber()', () => {
        it('should pow the number', () => {
            expect(numberOperations.powNumber(2)).to.be.equal(4);
        });
    });

    describe('tests for numberChecker()', () => {
        it('should throw an error with NaN', () => {
            const errorMsg = 'The input is not a number!';
            expect(() => numberOperations.numberChecker('a')).to.throw(errorMsg);
        });

        it('should return that the number is lower than 100', () => {
            expect(numberOperations.numberChecker('99')).to.be.equal('The number is lower than 100!');
        });

        it('should return that the number is greater or equal to 100', () => {
            expect(numberOperations.numberChecker(100)).to.be.equal('The number is greater or equal to 100!');
        });
    });

    describe('tests for sumArrays()', () => {
        it('should sum the arrays', () => {
            const firstArray = [-1, 4, 6, 7];
            const secondArray = [3, 2, 1];
            const expected = JSON.stringify([2, 6, 7, 7]);
            expect(JSON.stringify(numberOperations.sumArrays(firstArray, secondArray))).to.be.equal(expected);
        });
    });
});
