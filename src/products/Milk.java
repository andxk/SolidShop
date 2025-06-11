package products;

public class Milk extends Product implements IVolumetrical {
    protected int volume; // millilitres

    public Milk(String name, int price, String producer, int volume) {
        super(name, price, producer);
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return volume;
    }
}
