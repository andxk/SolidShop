package products;

public class Bread extends Product implements IWeighted {
    protected int weight; // grams

    public Bread(String name, int price, String producer, int weight) {
        super(name, price, producer);
        this.weight = weight;
    }


    @Override
    public int getWeight() {
        return weight;
    }
}
