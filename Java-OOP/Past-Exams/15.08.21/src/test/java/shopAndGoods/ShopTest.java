package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private Shop shop;
    private Goods goods;

    @Before
    public void setUp() {
        shop = new Shop();
        goods = new Goods("Pavel", "456");
    }

    @Test
    public void testShopConstructor() {
        Assert.assertEquals(12, this.shop.getShelves().size());
    }

    @Test
    public void testGoodsConstructor() {
        Assert.assertEquals("Pavel", this.goods.getName());
        Assert.assertEquals("456", this.goods.getGoodsCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoods() throws OperationNotSupportedException {
        this.shop.addGoods("S0", this.goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsIfItsNull() throws OperationNotSupportedException {
        this.shop.addGoods(null, this.goods);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsIfShelfIsTaken() throws OperationNotSupportedException {
        this.shop.addGoods("S1", this.goods);
        this.shop.addGoods("S1", new Goods("Pesho","901"));

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsIfAlreadyExist() throws OperationNotSupportedException {
        this.shop.addGoods("S1", this.goods);
        this.shop.addGoods("S2", this.goods);
    }

    @Test
    public void testAddGoodsIfItsCorrect() throws OperationNotSupportedException {
        this.shop.addGoods("S1", this.goods);
        Assert.assertEquals(this.goods, this.shop.getShelves().get("S1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsIfNotExist() {
        this.shop.removeGoods("S0", this.goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsIfNotExistGoods() throws OperationNotSupportedException {
        this.shop.addGoods("S1", this.goods);
        this.shop.removeGoods("S1", new Goods("Gosho", "789"));
    }

    @Test
    public void testRemoveGoodsIfItsCorrect() throws OperationNotSupportedException {
        this.shop.addGoods("S1", this.goods);
        this.shop.removeGoods("S1", this.goods);
        Assert.assertEquals(null, this.shop.getShelves().get("S1"));
    }
}
