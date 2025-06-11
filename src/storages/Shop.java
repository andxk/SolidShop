package storages;

import products.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Shop extends ProductStorage{

    @Override
    public List<Product> getProducts() {
        return inStockFilter(super.getProducts());
    }

    @Override
    public List<Product> getFilterProducts(String filter) {
        return inStockFilter(super.getFilterProducts(filter));
    }

    @Override
    public void printProductList(List<Product> list) {
        System.out.println();
        int n = 1;
//        list = inStockFilter(list);
        for (Product p : list) {
//            if (p.getCount() > 0) {
                System.out.printf("%3d - %s, цена: %d, доступно: %d %n", n, p.toString(), p.getPrice(), p.getCount());
//            }
            n++;
        }
    }
}
