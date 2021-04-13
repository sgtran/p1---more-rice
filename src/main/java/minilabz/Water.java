package minilabz;

import consoleUI.ConsoleMethods;

public class Water extends Generic {
    public enum KeyType {title, brand, price}
    public static Water.KeyType key = KeyType.title;
    public String brand;
    public int price;

    Water(String brand, int price) {
        this.setType("Water");
        this.brand = brand;
        this.price = price;
    }

    public String toString() {
        String output="";
        switch (key) {
            case brand:
                output += this.brand;
                break;
            case price:
                output += this.price;
                break;
            case title:
            default:
                output = super.getType() + ": " + this.brand + ", " + this.price;
        }

        return output;
    }

    public static Generic[] waterData(){
        return new Generic[]{
                new Water("Kirkland", 3),
                new Water("Aquafina", 2),
                new Water("Fiji", 7),
                new Water("Dasani", 1),
                new Water("SmartWater", 2),
        };
    }

    public static void main(String[] args)
    {
        Generic[] water = waterData();
        for(Generic c:  water)
            ConsoleMethods.println(c);
    }
}
