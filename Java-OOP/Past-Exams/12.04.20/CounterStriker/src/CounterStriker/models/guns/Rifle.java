package CounterStriker.models.guns;

public class Rifle extends GunImpl{
    public Rifle(String name, int bulletCount) {
        super(name, bulletCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount()>=10){
            setBulletsCount(getBulletsCount() - 10);
            return 10;
        }
        return super.fire();
    }
}
