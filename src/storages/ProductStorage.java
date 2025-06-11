package storages;

import products.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public abstract class ProductStorage {
    protected HashMap<Integer, Product> products = new HashMap<>();

    public void addProduct(Product newProduct, int addCount) {
        int key = newProduct.key();
        if (products.containsKey(key)) {
            addCount += products.get(key).getCount();
        }
        newProduct.setCount(addCount);
        products.put(key, newProduct);
    }


    public void updateProduct(Product product) {
        products.put(product.key(), product);
    }


    public Product get(Product product) {
        return products.getOrDefault(product.key(), product);
    }


    // Оставляем только товары с ненулевым остатком
    public List<Product> inStockFilter(List<Product> list) {
        Iterator<Product> it = list.iterator();
        while (it.hasNext()) {
            Product p = it.next();
            if (p.getCount() == 0) {
                it.remove();
            }
        }
        return list;
    }


    public List<Product> getProducts() {
        return new ArrayList<Product>(products.values());
    }

    public List<Product> getFilterProducts(String filter) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : products.values()) {
            if (p.contains(filter)) {
                result.add(p);
            }
        }
        return result;
    }


    public abstract void printProductList(List<Product> list);

    public void printProductList(String filter) {
        printProductList(getFilterProducts(filter));
    }

    public void printProductList() {
        printProductList(getProducts());
    }

    public int getProductsCount() {
        return products.size();
    }

}
