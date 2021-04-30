package minilabz;

public class Athlete extends Generic{ //for sort minilab
    public int age, salary;
    public String sport;

    public Athlete(int e, int s, String p) {
        this.age = e;
        this.salary = s;
        this.sport = p;
    }

    @Override
    public String toString() {
        return this.sport + ", $" + this.salary + ", " + this.age + " age;    ";
    }
}
