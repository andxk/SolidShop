package products;

import java.util.Objects;

public class Product {
    protected String name;
    protected int price;
    protected String producer;
    protected int count;


    public Product(String name, int price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
        this.count = count;
    }

    public Product(Product sourceProduct) {
        this.name = sourceProduct.name;
        this.price = sourceProduct.price;
        this.producer = sourceProduct.producer;
        this.count = 0;
    }

    public Product(String name, int price) {
        this(name, price, "");
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0) {
            this.price = price;
        }
    }


    public int incCount(int added) {
        count += added;
        return count;
    }

    public int decCount(int losted) {
        count -= losted;
        if (count < 0) count = 0;
        return count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }


    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }


    public boolean contains (String findStr) {
        String productString = name + " " + producer + " " + price;
        return productString.contains(findStr);
    }


    public int key() {
        return hashCode();
    }

    @Override
    public String toString() {
        return name + /*" " + price + " руб.*/ " (" + producer + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(name, product.name) && Objects.equals(producer, product.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, producer);
    }
}
