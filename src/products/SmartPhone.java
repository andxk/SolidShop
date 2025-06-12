package products;

public class SmartPhone extends Product implements IIndustrialGoods, IScreenable, IMobile, IWeighted {
    // Interface Segregation Principle - имплементируем несколько простых интерфейсов вместо одного сложного
    // Dependency Inversion Principle - разделив интерфейсы мы не зависим от их общей функциональности
    private int warranty;
    private int rom;
    private int ram;
    private int weight;
    private double screenSize;

    public SmartPhone(String name, int price, String producer) {
        super(name, price, producer);
    }

    public SmartPhone setWarranty(int warranty) {
        this.warranty = warranty;
        return this;
    }

    public SmartPhone setRom(int rom) {
        this.rom = rom;
        return this;
    }

    public SmartPhone setRam(int ram) {
        this.ram = ram;
        return this;
    }

    public SmartPhone setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public SmartPhone setScreenSize(int screenSize) {
        this.screenSize = screenSize;
        return this;
    }

    @Override
    public int getWarrantyMonths() {
        return warranty;
    }

    @Override
    public int getRomSize() {
        return rom;
    }

    @Override
    public int getRamSize() {
        return ram;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public double getScreenSize() {
        return screenSize;
    }
}
