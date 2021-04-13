package minilabz;

public class Pet extends Generic {

    public enum KeyType {title, nickname, species}
    public static KeyType key = KeyType.title;
    private final String nickname;
    private final String species;

    public Pet(String n, String s) {
        this.setType("Student");
        this.nickname = n;
        this.species = s;
    }

    @Override
    public String toString() {
        String output="";
        switch(key) {
            case nickname:
                output += this.nickname + " " + this.nickname;
                break;
            case species:
                output += this.species + ", " + this.species;
                break;
            default:
                output = super.getType() + ": " + this.nickname + " the " + this.species;
        }
        return output;
    }

    public static Generic[] pets(){
        Generic[] pets = {};
        pets[0] = new Pet("Clifford", "Big Red Dog");
        pets[0] = new Pet("Nemo", "Clownfish");
        pets[0] = new Pet("Camel", "Camel");
        pets[0] = new Pet("Alexander", "Great");
        pets[0] = new Pet("Bozo", "Clown");
        pets[0] = new Pet("Tyler", "Creator");
        pets[0] = new Pet("Harambe", "Gorilla");
        pets[0] = new Pet("Garfield", "Dog");
        return pets;
    }

    public static void main(String[] args) {
        Generic[] petList = pets();
    }

}
