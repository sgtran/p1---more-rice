package minilabz;

import consoleUI.ConsoleMethods;

public class IceCream extends Generic{
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

    public static Generic[] iceCreamData () {
        return new Generic[]{
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
        Generic[] iceCream = iceCreamData();
        for(Generic c:  iceCream)
            ConsoleMethods.println(c);
    }
}