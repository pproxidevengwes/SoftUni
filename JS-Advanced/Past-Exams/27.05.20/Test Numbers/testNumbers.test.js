const testNumbers = require('./testNumbers');
const assert = require('chai').assert;

describe("Test Numbers", function() {
    describe("sumNumbers function", function() {
        it("should work when two numbers are given as params", function() {
            assert.equal(testNumbers.sumNumbers(2,3), 5);
            assert.equal(testNumbers.sumNumbers(-2, -3), -5);
            assert.equal(testNumbers.sumNumbers(0, 0), 0);
        });
        it("should return undefined when non-numbers are provided", function() {
            assert.isUndefined(testNumbers.sumNumbers('1', '2'));
            assert.isUndefined(testNumbers.sumNumbers(1, '2'));
        })
        it("should return rounded decimal result to the second number", () => {
            assert.equal(testNumbers.sumNumbers(1.2345, 2.2345), 3.47);
        })
     });
     describe("numberChecker", function() {
        it("should identify even number", function() {
            assert.equal(testNumbers.numberChecker('2'), "The number is even!");
            assert.equal(testNumbers.numberChecker(2), "The number is even!");
            assert.equal(testNumbers.numberChecker(-2), "The number is even!");
            assert.equal(testNumbers.numberChecker(0), "The number is even!");
        });
        it("should identify odd number", function() {
            assert.equal(testNumbers.numberChecker('3'), "The number is odd!");
            assert.equal(testNumbers.numberChecker(3), "The number is odd!");
            assert.equal(testNumbers.numberChecker(-3), "The number is odd!");
        });
        it("should fail when non-number is provided", function() {
            assert.throws(()=>testNumbers.numberChecker(undefined), "The input is not a number!" );
            
        });
     });
     describe("averageSumArray", function() {
        it("should calculate average", function() {
            assert.equal(testNumbers.averageSumArray([2, 3, 4]), 3);
            assert.equal(testNumbers.averageSumArray([-2, 3, 5]), 2);
        });
     });

});
