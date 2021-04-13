package com.nighthawk.csa.algorithm.genericDataModel;

import com.nighthawk.csa.consoleUI.ConsoleMethods;

public class IceCream extends Generics{
    public enum KeyType {title, flavor, sprinkles}
    public static IceCream.KeyType key = IceCream.KeyType.title;
    public String flavor;
    public boolean sprinkles;

    IceCream(String flavor, boolean sprinkles)
    {
        this.setType("Ice Cream");
        this.flavor = flavor;
        this.sprinkles = sprinkles;
    }

    public String toString() {
        String output="";
        switch (key) {
            case flavor:
                output += this.flavor;
                break;
            case sprinkles:
                output += this.sprinkles;
                break;
            case title:
            default:
                output = super.getType() + ": " + this.flavor + ", " + this.sprinkles;
        }

        return output;
    }

    public static Generics[] iceCreamData () {
        return new Generics[]{
                new IceCream("Chocolate", true),
                new IceCream("Chocolate", false),
                new IceCream("Vanilla", true),
                new IceCream("Vanilla", false),
                new IceCream("Strawberry", true),
                new IceCream("Strawberry", false),
        };
    }

    public static void main(String[] args)
    {
        Generics[] iceCream = iceCreamData();
        for(Generics c:  iceCream)
            ConsoleMethods.println(c);
    }
}
