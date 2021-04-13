package minilabz;

import consoleUI.ConsoleMethods;

public class Pokemon extends Generic {
    public enum KeyType {title, name, level,species}
    public static Pokemon.KeyType key = KeyType.title;
    public String name;
    public String species;

    public int level;

    Pokemon(String name, int level, String species) {
        this.setType("Pokemon");
        this.name = name;
        this.species = species;

        this.level = level;
    }

    public String toString() {
        String output="";
        switch (key) {
            case name:
                output += this.name;
                break;
            case species:
                output += this.species;
                break;
            case level:
                output += this.level;
                break;
            case title:
            default:
                output = super.getType() + ": " + this.name + ", " + this.level+ ", " + this.species;
        }

        return output;
    }

    public static Generic[] pokemonData(){
        return new Generic[]{
                new Pokemon("Charizard", 3,"Fire"),
                new Pokemon("Pikachu", 2,"Electric"),
                new Pokemon("Squirtle", 7,"Water"),
                new Pokemon("Bulbasaur", 1,"Plant"),
        };
    }

    public static void main(String[] args)
    {
        Generic[] pokemon = pokemonData();
        for(Generic c:  pokemon)
            ConsoleMethods.println(c);
    }
}
