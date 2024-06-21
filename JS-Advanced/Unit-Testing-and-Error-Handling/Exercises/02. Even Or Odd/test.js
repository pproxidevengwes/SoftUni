const isOddOrEven = require('./evenOrOdd');
const {assert} = require('chai');

describe ('Test Odd or Even', () => {
    it('should return undefined when passed argument is not a string', () => {
        assert.isUndefined(isOddOrEven(5));
        assert.isUndefined(isOddOrEven(undefined));
        assert.isUndefined(isOddOrEven(null));
    });

    it('should return even when string length is even', () => {
        assert.equal(isOddOrEven('hi'), 'even');
        assert.equal(isOddOrEven(''), 'even');
        assert.equal(isOddOrEven('task'), 'even');
    });

    it('should return odd when string length is odd', () => {
        assert.equal(isOddOrEven('hello'), 'odd');
        assert.equal(isOddOrEven('h'), 'odd');
    });
});
