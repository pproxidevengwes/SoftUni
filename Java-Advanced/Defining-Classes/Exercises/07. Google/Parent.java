public class Parent {
    private String parentName;
    private String parentBDay;

    public Parent(String parentName, String parentBDay) {
        this.parentName = parentName;
        this.parentBDay = parentBDay;
    }

    @Override
    public String toString() {
        return parentName + " " + parentBDay;
    }
}
