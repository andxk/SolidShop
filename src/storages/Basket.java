package storages;

import products.Product;
import java.util.List;

public class Basket extends ProductStorage {
    // Magic Numbers Principle - используем константы вместо чисел
    protected static final int MIN_SHIPPING_COST = 100; // минимальная стоимость доставки
    protected static final int RUB_KM_COST = 30; // стоимость доставки за километр

    protected ProductStorage shop;
    protected int distance;


    public Basket(ProductStorage shop) {
        this.shop = shop;
    }

    @Override
    public void addProduct(Product newProduct, int addCount) {
        int key = newProduct.key();
        if (!shop.products.containsKey(key)) return;
        // Проверяем, чтобы не купить больше единиц, чем у есть у продавца
        Product sellerProduct = shop.get(newProduct);
        if (sellerProduct.getCount() < addCount) {
            addCount = sellerProduct.getCount();
        }
        super.addProduct(new Product(newProduct), addCount);
        sellerProduct.decCount(addCount);
        shop.updateProduct(sellerProduct);
    }


    public void setDistance(int distance) {
        if (distance >= 0) {
            this.distance = distance;
        }
    }


    public void returnAll() {
        for (Product p : getProducts()) { // Корректируем кол-во товара у продавца после покупки
            Product sellerProduct = shop.get(p);
            sellerProduct.incCount(p.getCount());
            shop.updateProduct(sellerProduct);
        }
        products.clear();
    }


    public void clear() {
        products.clear();
        distance = 0;
    }


    public void pay() {
        System.out.println("Проводим оплату...");
        System.out.println("Cписываем товары...");
        clear();
        System.out.println("Заказ оплачен и передан в доставку!");
    }


    public int getFullPrice() {
        if (products.isEmpty()) return 0;
        int sum = 0;
        for (Product p : getProducts()) {
            sum += p.getPrice() * p.getCount();
        }
        sum += Math.max(MIN_SHIPPING_COST, distance * RUB_KM_COST); // + стоимость доставки
        return sum;
    }


    @Override
    public void printProductList(List<Product> list) {
        System.out.println();
        int n = 1;
        for (Product p : list) {
            int productSumPrice = p.getPrice() * p.getCount();
            String s = String.format("%3d - %s, цена: %d, кол-во: %d, сумма: %d",
                    n++, p.toString(), p.getPrice(), p.getCount(), productSumPrice);
            System.out.println(s);
        }
        System.out.println("---");
        System.out.println("Стоимость (с доставкой): " + getFullPrice());
    }
}
