package me.jy.lang.generic;

/**
 * @author jy
 */
public class Plate<T> {

    private T item;

    public static void main(String[] args) {
        Plate<? extends Fruit> plate = new Plate<>();
//        plate.setItem(new Fruit());
        Object item = plate.getItem();
        System.out.println(item);

        Plate<? super Fruit> superPlate = new Plate<>();
        superPlate.setItem(new Apple());
        Object superPlateItem = superPlate.getItem();
        System.out.println(superPlateItem.getClass());
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
