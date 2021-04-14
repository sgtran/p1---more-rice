package com.example.demo.controller;

import consoleUI.ConsoleMethods;
import minilabz.*;

import com.example.demo.models.linkedlists.CircleQueue;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.User;
import com.example.demo.service.CustomUserDetailsService;
import minilabz.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Getter
@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class DataOPsController {
    private CircleQueue queue;	// circle queue object
    private int count; // number of objects in circle queue
    //control variables for UI checkboxes and radios
    private boolean  ice, water, student, teacher, pet, cake;

    private IceCream.KeyType iceKey;
    private Water.KeyType waterKey;

    private Student.KeyType studentKey;
    private Teacher.KeyType teacherKey;
    private Pet.KeyType petKey;
    private CakeShop.KeyType cakeKey;

    /*
     * Circle queue constructor
     */
    public DataOPsController()
    {
        //circle queue inits
        count = 0;
        queue = new CircleQueue();
    }

    /*
     * Add any array of objects to the queue
     */
    public void addCQueue(Object[] objects)
    {
        ConsoleMethods.println("Add " + objects.length);
        for (Object o : objects)
        {
            queue.add(o);
            ConsoleMethods.println("Add: " + queue.getObject() + " " + queue);
            this.count++;
        }
        ConsoleMethods.println();
    }

    /*
     * Delete/Clear all object in circle queue
     */
    public void deleteCQueue()
    {
        int length = this.count;
        ConsoleMethods.println("Delete " + length);

        for (int i = 0; i<length; i++)
        {
            ConsoleMethods.println("Delete: " + queue.delete() + " " + queue);
            this.count--;
        }
    }

    /*
     * String buffer for each row is created to support Thymeleaf (Interable could be alternative)
     */
    public List<String> getCQList()
    {
        List<String> log = new ArrayList<>();
        //travers each row, halting when first is re-encountered (circle queue halt)
        Object first = queue.getFirstObject();
        do {
            log.add(queue.getObject().toString());
        } while (queue.setNext() != first);
        return log;
    }

    /*
     GET request,, parameters are passed within the URI
     */

    @GetMapping("/kevinSort")

    public String kevinSort(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("kevinSort");

        this.count = 0;
        this.queue = new CircleQueue();

        this.waterKey = Water.KeyType.title;
        Water.key = this.waterKey;

        this.water = true;

        this.addCQueue(Water.waterData());

        model.addAttribute("ctl", this);
        return "kevinSort"; //HTML render default condition
    }

    @PostMapping("/kevinSort")
    public String kevinSortFilter(
            @RequestParam(value = "water", required = false) String water,
            @RequestParam(value = "waterKey") Water.KeyType waterKey,

            Model model)
    {

        if (water != null) {
            this.addCQueue(Water.waterData());
            this.water = true;
            this.waterKey = waterKey;
            Water.key = this.waterKey;
        } else {
            this.water = false;
        }

        //sort data according to selected options
        this.queue.insertionSort();
        //render with options
        model.addAttribute("ctl", this);
        return "kevinSort";
    }

    /*
     * Show key objects/properties of circle queue
     */

    @GetMapping("/andrewSort")

    public String andrewSort(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("andrewSort");

        this.count = 0;
        this.queue = new CircleQueue();

        this.petKey = Pet.KeyType.title;
        Pet.key = this.petKey;
        this.studentKey = Student.KeyType.title;
        Student.key = this.studentKey;
        this.teacherKey = Teacher.KeyType.title;
        Teacher.key = this.teacherKey;

        this.pet = true;
        this.student = true;
        this.teacher = true;

        this.addCQueue(Pet.pets());
        this.addCQueue(Student.students());
        this.addCQueue(Teacher.teachers());

        model.addAttribute("ctl", this);
        return "andrewSort"; //HTML render default condition
    }

    @PostMapping("/andrewSort")
    public String andrewSortFilter(
            @RequestParam(value = "pet", required = false) String pet,
            @RequestParam(value = "petKey") Pet.KeyType petKey,
            @RequestParam(value = "student", required = false) String student,
            @RequestParam(value = "studentKey") Student.KeyType studentKey,
            @RequestParam(value = "teacher", required = false) String teacher,
            @RequestParam(value = "teacherKey") Teacher.KeyType teacherKey,

            Model model)
    {

        if (pet != null) {
            this.addCQueue(Pet.pets());
            this.pet = true;
            this.petKey = petKey;
            Pet.key = this.petKey;
        } else {
            this.pet = false;
        }

        if (student != null) {
            this.addCQueue(Student.students());
            this.student = true;
            this.studentKey = studentKey;
            Student.key = this.studentKey;
        } else {
            this.student = false;
        }

        if (teacher != null) {
            this.addCQueue(Teacher.teachers());
            this.teacher = true;
            this.teacherKey = teacherKey;
            Teacher.key = this.teacherKey;
        } else {
            this.teacher = false;
        }

        //sort data according to selected options
        this.queue.insertionSort();
        //render with options
        model.addAttribute("ctl", this);
        return "andrewSort";
    }


    @GetMapping("/data")

    public String data(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("data");
        //initialize data
        this.count = 0;
        this.queue = new CircleQueue();
        //application specific inits
        //title defaults


        this.iceKey = IceCream.KeyType.title;
        IceCream.key = this.iceKey;

        this.waterKey = Water.KeyType.title;
        Water.key = this.waterKey;

        this.cakeKey = CakeShop.KeyType.title;
        CakeShop.key = this.cakeKey;


        //control options
        this.ice = true;
        this.water = true;
        this.student = true;
        this.teacher = true;
        this.pet = true;
        this.cake = true;

        //load data
        this.addCQueue(Animal.animalData());
        this.addCQueue(IceCream.iceCreamData());

        //data is not sorted, queue order (FIFO) is default
        model.addAttribute("ctl", this);
        return "data"; //HTML render default condition
    }

    /*
     GET request,, parameters are passed within the URI
     */
    @PostMapping("/data")
    public String dataFilter(
            @RequestParam(value = "ice", required = false) String ice,
            @RequestParam(value = "iceKey") IceCream.KeyType iceKey,
            @RequestParam(value = "water", required = false) String water,
            @RequestParam(value = "waterKey") Water.KeyType waterKey,
            @RequestParam(value = "student", required = false) String student,
            @RequestParam(value = "studentKey") Student.KeyType studentKey,
            @RequestParam(value = "teacher", required = false) String teacher,
            @RequestParam(value = "teacherKey") Teacher.KeyType teacherKey,
            @RequestParam(value = "pet", required = false) String pet,
            @RequestParam(value = "petKey") Pet.KeyType petKey,
            @RequestParam(value = "cake", required = false) String cake,
            @RequestParam(value = "cakeKey") CakeShop.KeyType cakeKey,

            Model model)
    {

        //re-init data according to check boxes selected
        count = 0;
        queue = new CircleQueue();
        //for each category rebuild data, set presentation and data defaults


        if (ice != null) {
            this.addCQueue(IceCream.iceCreamData());  //adding ice data to queue
            this.ice = true;             //persistent selection from check box selection
            this.iceKey = iceKey;     //persistent enum update from radio button selection
            IceCream.key = this.iceKey;    //toString configure for sort order
        } else {
            this.ice = false;
        }

        if (water != null) {
            this.addCQueue(Water.waterData());
            this.water = true;
            this.waterKey = waterKey;
            Water.key = this.waterKey;
        } else {
            this.water = false;
        }


        if (student != null) {
            this.addCQueue(Student.students());
            this.student = true;
            this.studentKey = studentKey;
            Student.key = this.studentKey;
        } else {
            this.student = false;
        }

        if (teacher != null) {
            this.addCQueue(Teacher.teachers());
            this.teacher = true;
            this.teacherKey = teacherKey;
            Teacher.key = this.teacherKey;
        } else {
            this.teacher = false;
        }

        if (pet != null) {
            this.addCQueue(Pet.pets());
            this.pet = true;
            this.petKey = petKey;
            Pet.key = this.petKey;
        } else {
            this.pet = false;
        }

        if (cake != null) {
            this.addCQueue(CakeShop.cakes());
            this.cake = true;
            this.cakeKey = cakeKey;
            CakeShop.key = this.cakeKey;
        } else {
            this.cake = false;
        }

        //sort data according to selected options
        this.queue.insertionSort();
        //render with options
        model.addAttribute("ctl", this);
        return "data";
    }

    /*
     * Show key objects/properties of circle queue
     */
    public void printCQueue()
    {
        //queue and object of queue all print via toString()
        ConsoleMethods.println("Size: " + count);
        ConsoleMethods.println("First Element: " + queue.getFirstObject());
        ConsoleMethods.println("Last Element: " + queue.getLastObject());
        ConsoleMethods.println("Full cqueue: " + queue);
        for (String line : this.getCQList()) {
            ConsoleMethods.println(line);
        }
        ConsoleMethods.println();
    }

    /*
     * Illustrate different Objects that can be placed on same Queue
     */
    public static void main(String[] args)
    {
        //queue
        DataOPsController trial = new DataOPsController();

        //add different types of objects to the same opaque queue
        trial.addCQueue(IceCream.iceCreamData());
        trial.addCQueue(Water.waterData());

        trial.addCQueue(Student.students());
        trial.addCQueue(Teacher.teachers());
        trial.addCQueue(Pet.pets());

        //display queue objects in queue order
        ConsoleMethods.println("Add order (all data)");
        trial.printCQueue();

        //sort queue objects by specific element within the object and display in sort order
        IceCream.key = IceCream.KeyType.flavor;
        Water.key = Water.KeyType.brand;

        Student.key = Student.KeyType.lastName;
        Teacher.key = Teacher.KeyType.lastName;
        Pet.key = Pet.KeyType.nickname;

        CakeShop.key = CakeShop.KeyType.caketype;

        trial.queue.insertionSort();
        ConsoleMethods.println("Sorted order (key only)");
        trial.printCQueue();

        //display queue objects, changing output but not sort
        IceCream.key = IceCream.KeyType.title;
        Water.key = Water.KeyType.title;

        Student.key = Student.KeyType.title;
        Teacher.key = Teacher.KeyType.title;
        Pet.key = Pet.KeyType.title;

        CakeShop.key = CakeShop.KeyType.title;

        ConsoleMethods.println("Retain sorted order (all data)");
        trial.printCQueue();
        trial.queue.insertionSort();
        //display queue objects, changing sort order
        ConsoleMethods.println("Order by data type (all data)");
        trial.printCQueue();

        //delete queue objects
        ConsoleMethods.println("Delete from front (all data)");
        trial.deleteCQueue();
    }

}