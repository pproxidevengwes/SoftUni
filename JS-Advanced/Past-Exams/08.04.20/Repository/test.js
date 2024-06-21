let { Repository } = require("./solution.js");
const { assert, expect } = require('chai')

describe(`testing Repository class`, () => {
    let instance = {}

    beforeEach(() => instance = new Repository({
        name: 'string',
        age: 'number',
        birthday: 'object'
    }))

    describe('testing count prop', () => {
        it(`returns stored entities count`, () => {
            expect(instance.count).to.equal(0)
        })
    })

    describe('testing .add()', () => {
        it(`returns result id`, () => {
            expect(instance.add({ name: 'Pesho', age: 22, birthday: {} })).to.equal(0)
        })
    })

    describe(`testing .getId()`, () => {
        it(`invalid id -> throw`,
            () => {
                expect(() => instance.getId(1)).to
                    .throw('Entity with id: 1 does not exist!')
            })
    })

    describe(`testing .update()`, () => {
        it(`id does not exist -> throw`,
            () => {
                expect(() => instance.update(0, {},)).to
                    .throw(`Entity with id: 0 does not exist!`)
            })
        it(`missing prop -> throw Error`, () => {
            instance.add({ name: '', age: 0, birthday: {}, })
            expect(() => instance.update(
                0, { age: 1, birthday: { date: 0 } }
            )).to.throw
        })
        it(`incorrect type -> throw TypeError`, () => {
            instance.add({ name: '', age: 0, birthday: {}, })
            expect(() => instance.update(
                0, { name: 0, age: 1, birthday: { date: 0 } }
            )).to.throw(TypeError)
        })
    })

    describe(`testing .del()`, () => {
        it(`deletes by id`, () => {
            instance.add({ name: '', age: 1, birthday: {} })
            instance.add({ name: '', age: 1, birthday: {} })
            instance.del(1)
            expect(instance.data.has(1)).to.equal(false)
        })
        it(`invalid id`, () => {
            expect(() => instance.del(-1)).to
                .throw(`Entity with id: -1 does not exist!`)
        })
    })
})
