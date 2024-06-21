public class European implements Person {
    private final String name;

    public European(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String sayHello() {
        return this.name;
    }
}
