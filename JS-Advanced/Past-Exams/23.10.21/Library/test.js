const assert = require('chai').assert
const expect = require('chai').expect
const library = require('./library')
 
describe('Problem 3', () => {
    it('Calculate price', () => {
        assert.strictEqual(library.calcPriceOfBook('hary', 1980), 'Price of hary is 10.00')
        assert.strictEqual(library.calcPriceOfBook('hary', 1981), 'Price of hary is 20.00')
    })
    it('name is not a string', () => {
        assert.throw(() => library.calcPriceOfBook(2, 1981), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook([], 1981), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook({}, 1981), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook(true, 1981), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook(null, 1981), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook(undefined, 1981), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook(false, 1981), 'Invalid input')
    })
 
    it("year is not integer", () => {
        assert.throw(() => library.calcPriceOfBook('hary', 1981.2), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook('hary', []), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook('hary', '1981.2'), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook('hary', {}), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook('hary', true), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook('hary', null), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook('hary', undefined), 'Invalid input')
        assert.throw(() => library.calcPriceOfBook('hary', '2'), 'Invalid input')
    })
 
    it('Find exist book', () => {
        assert.strictEqual(library.findBook(["Troy", "Life Style", "Torronto"], "Torronto"), "We found the book you want.")
        assert.strictEqual(library.findBook(["Troy", "Life Style", "Torronto"], "Thor"), "The book you are looking for is not here!")
    })
 
    it('Empty array', () => {
        assert.throw(() => library.findBook([], "Troy"), "No books currently available")
    })
 
    it('arrange book', () => {
        assert.strictEqual(library.arrangeTheBooks(0), "Great job, the books are arranged.")
        assert.strictEqual(library.arrangeTheBooks(40), "Great job, the books are arranged.")
        assert.strictEqual(library.arrangeTheBooks(13), "Great job, the books are arranged.")
    })
    it("arrange too many books", () => {
        assert.strictEqual(library.arrangeTheBooks(41), "Insufficient space, more shelves need to be purchased.")
        assert.strictEqual(library.arrangeTheBooks(141), "Insufficient space, more shelves need to be purchased.")
    })
 
    it('Invalid Number of Books', () => {
        assert.throw(() => library.arrangeTheBooks(-1), "Invalid input")
        assert.throw(() => library.arrangeTheBooks(1.5), "Invalid input")
        assert.throw(() => library.arrangeTheBooks([-1]), "Invalid input")
        assert.throw(() => library.arrangeTheBooks({}), "Invalid input")
        assert.throw(() => library.arrangeTheBooks(true), "Invalid input")
    })
})
