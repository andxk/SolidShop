package products;

public class Milk extends Product implements IVolumetrical {
    // Single Responsibility Principle - класс Milk описывает только молоко, а не любые продукты
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
