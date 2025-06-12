# Принципы Solid

## Single-responsibility principle
Класс Milk описывает только молоко, а не любые продукты 
<https://github.com/andxk/SolidShop/blob/main/src/products/Milk.java#L3>

## Open-closed principle
Имплементируя интерфейсы мы добавляем классу новые свойства, такие как "Гарантия" и "Диагональ экрана" не изменяя его код 
<https://github.com/andxk/SolidShop/blob/main/src/products/TV.java#L3>

## Liskov substitution principle
Наследники класса Product полностью играют роль предка
<https://github.com/andxk/SolidShop/blob/main/src/Main.java#L17>

## Interface segregation principle
Имплементируем несколько простых интерфейсов вместо одного сложного
<https://github.com/andxk/SolidShop/blob/main/src/products/SmartPhone.java#L3>

## Dependency inversion principle
Разделив интерфейсы мы не зависим от их общей функциональности
<https://github.com/andxk/SolidShop/blob/main/src/products/SmartPhone.java#L3>

#
# Шаблоны проектирования
## Magic Numbers Principle
Используем константы вместо чисел
<https://github.com/andxk/SolidShop/blob/main/src/storages/Basket.java#L8>

## Принцип DRY
Повторяющийся запрос ввода из консоли переносим в отдельный метод
<https://github.com/andxk/SolidShop/blob/main/src/Main.java#L29>

## Шабон Commander
Обращаемся с действиями как c объектами
<https://github.com/andxk/SolidShop/blob/main/src/Main.java#L137>
