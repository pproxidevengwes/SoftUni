package CounterStriker.models.guns;

public class Pistol extends GunImpl {
    public Pistol(String name, int bulletCount) {
        super(name, bulletCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount()>=1){
            setBulletsCount(getBulletsCount() - 1);
            return 1;
        }
        return super.fire();

    }
}
