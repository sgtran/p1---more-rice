package minilabz;

public class Teacher extends Generic {

    public enum KeyType {title, formOfAddress, lastName, married, age}
    public static KeyType key = KeyType.title;
    private final String formOfAddress;
    private final String lastName;
    private final boolean married;
    private final int age;

    public Teacher(String f, String l, boolean m, int a) {
        this.setType("Teacher");
        this.formOfAddress = f;
        this.lastName = l;
        this.married = m;
        this.age = a;
    }

    @Override
    public String toString() {
        String output="";
        switch(key) {
            case formOfAddress:
                output += this.formOfAddress + " " + this.lastName;
                break;
            case lastName:
                output += this.lastName;
                break;
            case married:
                if (this.married) {
                    output += "married";
                } else {
                    output += "unmarried";
                }
                break;
            case age:
                output += "0" + this.age;
                output = output.substring(output.length() - 2);
            case title:
            default:
                output = super.getType() + ": " + this.formOfAddress + " " + this.lastName + " is " + age + " years old and ";
                if(!married) {output += "not ";}
                output += "married";
        }
        return output;
    }

    public static Generic[] teachers(){
        return new Generic[]{
            new Teacher("Ms.", "Smith", false, 30),
            new Teacher("Ya Boi", "Rogers", false, 20),
            new Teacher("Dr.", "Dre", true, 56),
            new Teacher("Mr.", "Mortensen", true, 20),
            new Teacher("Madame", "Ping", false, 99),
        };
    }

    public static void main(String[] args) {
        Generic[] teacherList = teachers();
    }

}
