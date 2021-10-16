let assert = require('chai').assert;
let {sum} = require('./SumOfNumbers');

describe('calculate sum', () => {
    it('should return positive sum as a result of sum of only positive numbers in an array', ( )=> {
        assert.equal(1,sum([-2, 3]));
    });
    it('should return negative sum as a result of sum of only hegative numbers in an array', ( )=> {
        assert.equal(-4,sum([3, -7]));
    });
    it('should return positive or negative sum as a result of sum of positive and negativr numbers in an array', ( )=> {
        assert.equal(-10,sum([-8,-2]));
    });
    it('should return 0 as a result of sum of only numbers equal to 0 in an array', ( )=> {
        assert.equal(0,sum([0,0]));
    });
    
});
