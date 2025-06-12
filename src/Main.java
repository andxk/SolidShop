import products.*;
import storages.Basket;
import storages.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Command {
    void execute();
}


public class Main {


    public static void shopFilling(Shop shop) {
        //Liskov substitution principle - наследники класса Product полностью играют роль предка
        shop.addProduct(new Milk("Молоко 1л", 100, "ВоронежМолоко", 1000), 5);
        shop.addProduct(new Bread("Хлеб белый", 40, "Хлебозавод №3", 600), 10);
        shop.addProduct(new Bread("Хлеб черный", 45, "Хлебозавод №1", 700), 15);
        shop.addProduct(new Butter("Масло сливочное", 200, "АО Маслобойка", 180), 10);
        shop.addProduct(new SmartPhone("X5", 9900, "Huaway"), 3);
        shop.addProduct(new TV("43C3454", 20000, "Рубин").setScreenSize(43).setWarranty(24), 2);
    }


    //принцип DRY - повторяющийся запрос ввода из консоли переносим в отдельный метод
    public static List<Integer> userAnswer(String query) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println(query);
        List<Integer> result = new ArrayList<>();
        String input = "";
        while (input.isEmpty()) {
            input = sc.nextLine();
            if (input.equals("end") || input.equals("утв")) return result;
            String[] parts = input.split(" ");
            for (String s : parts) {
                try {
                    result.add(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибочный ввод. Ожидаются числа...");
                    input = "";
                }
            }
        }
        return result;
    }


    public static void showMenu(String[] names) {
        System.out.println();
        System.out.println("Список команд: \n");
        for (int i = 0; i < names.length; i++) {
            if (!names[i].isEmpty()) {
                System.out.printf("%2d - %s %n", i + 1, names[i]);
            }
        }

    }


    private static void addProductToBasket(Basket basket, Shop shop, List<Product> list) {
        final int INPUT_CNT = 2; // количество ожидаемых вводимых параметров
        if (list == null || list.isEmpty()) return;
        System.out.println("Выберите товар:");
        shop.printProductList(list);
        List<Integer> input = userAnswer("Введите номер товара и количество через пробел (0 - отмена)");
        if (!input.isEmpty() && input.get(0) == 0) return;
        if (input.size() < INPUT_CNT) {
            System.out.println("Недостаточно введенных данных");
            return;
        }
        int prodNum = input.get(0);
        int prodCnt = input.get(1);
        if (prodNum > 0 && prodNum <= list.size()) {
            if (prodCnt <= list.get(prodNum-1).getCount()) {
                basket.addProduct(list.get(prodNum-1), prodCnt);
                System.out.println("Товар добавлен в корзину");
            }
            else {
                System.out.println("Недостаточно товара в магазине");
            }
        }
        else {
            System.out.println("Нет товара с таким номером");
        }
    }


    private static List<Product> filterProductsInShop(Shop shop) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Введите строку поиска:");
        String input = sc.nextLine();
        if (!input.isEmpty()) {
//            shop.printProductList(input);
            List<Product> list = shop.getFilterProducts(input);
//            shop.printProductList(list);
            return list;
        }
        else {
            return null;
        }
    }


    private static void setDeliveryDistance(Basket basket) {
        basket.setDistance(userAnswer("Введите расстояние доставки, км: ").get(0));
        System.out.println("Стоимость корзины: " + basket.getFullPrice());
    }


    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Магазин по шаблонам!");

        Shop shop = new Shop();
        shopFilling(shop);

        Basket basket = new Basket(shop);

        String[] commandNames = {
                "Список доступных товаров", //1
                "Очистить корзину и вернуть товары", //2
                "Добавить товары в корзину...", //3
                "Печать корзины", //4
                "Поиск в магазине...", //5
                "Изменить расстояние доставки...",//6
                "Посчитать сумму корзины",//7
                "Оплатить и доставить заказ",//8
//                "",//9
//                "Выход из программы"//10
        };


        Command[] commands = {
                shop::printProductList, //1
                basket::returnAll, //2
                () -> addProductToBasket(basket, shop, shop.getProducts()), //3
                basket::printProductList, //4
                () -> addProductToBasket(basket, shop, filterProductsInShop(shop)), //5
                () -> setDeliveryDistance(basket), //6
                () -> System.out.println("Стоимость корзины: " + basket.getFullPrice()), //7
                basket::pay, //8
//                () -> {}, //9
//                () -> { if (userAnswer("exit? 1/0").get(0) == 1) System.exit(10); } //10
        };



        while (true) {
            showMenu(commandNames);
            List<Integer> inputs = userAnswer("Введите номер команды или 'end' для выхода: \n");
            if (inputs.isEmpty()) break; // end
            int commNum = inputs.get(0);
            if (commands.length >= commNum && commNum > 0) {
                System.out.println(commandNames[commNum-1]);
                commands[commNum-1].execute();
            }
        }

    }
}