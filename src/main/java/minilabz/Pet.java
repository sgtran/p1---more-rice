package minilabz;

public class Pet extends Generic {

    public enum KeyType {title, nickname, species}
    public static KeyType key = KeyType.title;
    private final String nickname;
    private final String species;

    public Pet(String n, String s) {
        this.setType("Pet");
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
            case title:
            default:
                output = super.getType() + ": " + this.nickname + " the " + this.species;
        }
        return output;
    }

    public static Generic[] pets(){
        return new Generic[]{
                new Pet("Clifford", "Big Red Dog"),
                new Pet("Camel", "Camel"),
                new Pet("Alexander", "Great"),
                new Pet("Harambe", "Gorilla"),
                new Pet("Garfield", "Dog")
        };
    }

    public static void main(String[] args) {
        Generic[] petList = pets();
    }

}
