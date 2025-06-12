package products;

public class TV extends Product implements IIndustrialGoods, IScreenable {
    // Open Closed Principle - имплементируя эти интерфейсы мы добавляем
    //   классу новые свойства, такие как "Гарантия" и "Диагональ экрана" не изменяя его код
    private int warranty;
    private double screenSize;

    public TV(String name, int price, String producer) {
        super(name, price, producer);
    }

    public TV setWarranty(int warranty) {
        this.warranty = warranty;
        return this;
    }

    public TV setScreenSize(double screenSize) {
        this.screenSize = screenSize;
        return this;
    }

    @Override
    public int getWarrantyMonths() {
        return warranty;
    }

    @Override
    public double getScreenSize() {
        return screenSize;
    }
}
