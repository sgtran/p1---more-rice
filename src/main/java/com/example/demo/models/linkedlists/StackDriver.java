package com.example.demo.models.linkedlists;



import consoleUI.ConsoleMethods;
import minilabz.Animal;
import minilabz.IceCream;
import minilabz.Pet;
import minilabz.Student;
import minilabz.Teacher;

/**
 * Stack Driver takes a list of Objects and puts them onto the Stack
 * @author johnmortensen
 *
 */
public class StackDriver {

    private Stack stack;
    private String dataType="";
    private int count;

    public StackDriver()
    {
        count = 0;
        stack = new Stack();
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void pushStack(Object[] objects)
    {
        ConsoleMethods.println("Add " + dataType);
        for (Object o : objects)
        {
            stack.push(o);
            ConsoleMethods.println("Push: " + stack.getObject() + " " + stack);
            this.count++;
        }
        ConsoleMethods.println();

    }

    public void showStack()
    {
        ConsoleMethods.println("Size: " + count);
        ConsoleMethods.println("Top Element: " + stack.getObject());
        ConsoleMethods.println("Full Stack: " + stack);
        ConsoleMethods.println();
    }

    public void popStack()
    {
        ConsoleMethods.println("Delete " + dataType);
        for (int x = 0; x<count; x++)
        {
            ConsoleMethods.println("Pop: " + stack.pop() + " " + stack);
        }
    }

    /*
     * Illustrate different Objects that can be placed on same Stack
     */
    public static void main(String[] args)
    {
        StackDriver trial = new StackDriver();

        // setup for Animals
        trial.setDataType("Animals");
        trial.pushStack(Animal.animalData());

        trial.setDataType("Ice Cream");
        trial.pushStack(IceCream.iceCreamData());

        trial.setDataType("Pet");
        trial.pushStack(Pet.pets());

        trial.setDataType("Student");
        trial.pushStack(Student.students());

        trial.setDataType("Teacher");
        trial.pushStack(Teacher.teachers());


        // mixed stack
        trial.setDataType("Animals");
        trial.setDataType("Ice Cream");
        trial.setDataType("Pet");
        trial.setDataType("Student");
        trial.setDataType("Teacher");
        trial.showStack();
        trial.popStack();
    }

}