package minilabz;

public class Student extends Generic {

    public enum KeyType {title, firstName, lastName, score, grade}
    public static KeyType key = KeyType.title;
    private final String firstName;
    private final String lastName;
    private final double score;
    private final int grade;

    public Student(String f, String l, double s, int g) {
        this.setType("Student");
        this.firstName = f;
        this.lastName = l;
        this.score = s;
        this.grade = g;
    }

    @Override
    public String toString() {
        String output="";
        switch(key) {
            case firstName:
                output += this.firstName + " " + this.lastName;
                break;
            case lastName:
                output += this.lastName + ", " + this.firstName;
                break;
            case score:
                output += "00" + this.score;
                output = output.substring(output.length()-4);
                break;
            case grade:
                output += "0" + this.grade;
                output = output.substring(output.length() - 2);
            case title:
            default:
                output = super.getType() + ": " + this.grade + " Grader " + this.firstName + " " + this.lastName + ", " + this.score;
        }
        return output;
    }

    public static Generic[] students (){
        return new Generic[]{
                new Student("Pete", "Davidson", 12.3, 12),
                new Student("David", "Peterson", 32.1, 12),
                new Student("Alexander", "Hamilton", 99.9, 12),
                new Student("Barack", "Obama", 10.0, 12),
                new Student("Spongebob", "Squarepants", 94.2, 12),
        };
    }

    public static void main(String[] args) {
        Generic[] studentList = students();
    }

}
