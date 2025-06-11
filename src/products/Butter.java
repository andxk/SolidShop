package products;

public class Butter extends Product implements IWeighted {
    protected int weight;

    public Butter(String name, int price, String producer, int weight) {
        super(name, price, producer);
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
