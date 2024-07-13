import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Company company;
    private Car car;

    private List<Parent> parents;
    private List<Child> children;
    private List<Pokemon> pokemons;

    public Person() {
        this.parents = new ArrayList<>();;
        this.children = new ArrayList<>();;
        this.pokemons = new ArrayList<>();;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public List<Child> getChildren() {
        return children;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Company:").append("\n");
        if (company != null) {
            sb.append(company.toString()).append("\n");
        }
        sb.append("Car:").append("\n");
        if (car != null) {
            sb.append(car.toString()).append("\n");
        }

        sb.append("Pokemon:").append("\n");
        for (Pokemon pokemon : pokemons) {
            sb.append(pokemon).append("\n");
        }


        sb.append("Parents:").append("\n");
        for (Parent parent : parents) {
            sb.append(parent).append("\n");
        }


        sb.append("Children:").append("\n");
        for (Child child : children) {
            sb.append(child).append("\n");

        }

        return sb.toString();
    }
}
