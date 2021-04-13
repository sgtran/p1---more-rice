package minilabz;

import consoleUI.ConsoleMethods;

public class Minecraft extends Generic {
    public enum KeyType {title, name, level,element}
    public static Minecraft.KeyType key = KeyType.title;
    public String name;
    public String element;

    public int level;

    Minecraft(String name, int level, String element) {
        this.setType("Minecraft");
        this.name = name;
        this.element = element;

        this.level = level;
    }

    public String toString() {
        String output="";
        switch (key) {
            case name:
                output += this.name;
                break;
            case element:
                output += this.element;
                break;
            case level:
                output += this.level;
                break;
            case title:
            default:
                output = super.getType() + ": " + this.name + ", " + this.level+ ", " + this.element;
        }

        return output;
    }

    public static Generic[] minecraftData(){
        return new Generic[]{
                new Minecraft("Pickaxe", 3,"Gold"),
                new Minecraft("Shovel", 2,"Stone"),
                new Minecraft("Axe", 7,"Diamond"),
                new Minecraft("Sword", 1,"Wood"),
        };
    }

    public static void main(String[] args)
    {
        Generic[] minecraft = minecraftData();
        for(Generic c:  minecraft)
            ConsoleMethods.println(c);
    }
}
