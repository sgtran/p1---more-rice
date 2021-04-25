package minilabz;

import consoleUI.ConsoleMethods;

public class Yogurt extends Generic{
    public enum KeyType {title, flavor, frozen}
    public static Yogurt.KeyType key = Yogurt.KeyType.title;
    public String flavor;
    public boolean frozen;

    Yogurt(String flavor, boolean temp)
    {
        this.setType("Yogurt");
        this.flavor = flavor;
        this.frozen = temp;
    }

    public String toString() {
        String output="";
        switch (key) {
            case flavor:
                output += this.flavor;
                break;
            case frozen:
                output += this.frozen;
                break;
            case title:
            default:
                output = super.getType() + ": " + this.flavor + ", " + this.frozen;
        }

        return output;
    }

    public static Generic[] yogurtData () {
        return new Generic[]{
                new Yogurt("Vanilla", false),
                new Yogurt("Vanilla", true),
                new Yogurt("Peach", false),
                new Yogurt("Peach", true),
                new Yogurt("Strawberry", false),
                new Yogurt("Strawberry", true),
        };
    }

    public static void main(String[] args)
    {
        Generic[] yogurt = yogurtData();
        for(Generic c:  yogurt)
            ConsoleMethods.println(c);
    }
}
