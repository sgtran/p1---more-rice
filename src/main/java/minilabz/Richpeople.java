package minilabz;

public class Richpeople extends Generic{ //for sort
    public int age, salary;
    public String company;

    public Richpeople(int e, int s, String p) {
        this.age = e;
        this.salary = s;
        this.company = p;
    }

    @Override
    public String toString() {
        return this.company + ", $" + this.salary + ", " + this.age + " age;    ";
    }
}