package minilabz;

public class Employee extends Generic{ //for sort minilab
    public int experience, salary;
    public String profession;

    public Employee(int e, int s, String p) {
        this.experience = e;
        this.salary = s;
        this.profession = p;
    }

    @Override
    public String toString() {
        return this.profession + ", $" + this.salary + ", " + this.experience + " years;    ";
    }
}
