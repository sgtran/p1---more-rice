package com.example.demo.controller;

import consoleUI.ConsoleMethods;
import minilabz.Animal;

import com.example.demo.models.linkedlists.CircleQueue;
import lombok.Getter;
import minilabz.IceCream;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Circle Queue Driver takes a list of Objects and puts them into a Queue
 * @author     John Mortensen
 *
 */
@Getter
@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class DataOPsController {
    private CircleQueue queue;	// circle queue object
    private int count; // number of objects in circle queue
    //control variables for UI checkboxes and radios
    private boolean animal, ice;
    private Animal.KeyType animalKey;
    private IceCream.KeyType iceKey;


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
    @GetMapping(value = {"/data", "/data2"})
    public String data(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("data");
        //initialize data
        this.count = 0;
        this.queue = new CircleQueue();
        //application specific inits
        //title defaults
        this.animalKey = Animal.KeyType.title;
        Animal.key = this.animalKey;
        this.iceKey = IceCream.KeyType.title;
        IceCream.key = this.iceKey;

        //control options
        this.animal = true;
        this.ice = true;

        //load data
        this.addCQueue(Animal.animalData());
        this.addCQueue(IceCream.iceCreamData());

        //data is not sorted, queue order (FIFO) is default
        model.addAttribute("ctl", this);
        if (ServletUriComponentsBuilder.fromCurrentRequest().toUriString().charAt(ServletUriComponentsBuilder.fromCurrentRequest().toUriString().length() - 1) == '2') { //if current url ends with a "2" indicating data2.html, show data2.html instead of data.html
            return "data2"; //HTML render default condition
        } else {
            return "data";
        }
    }

    /*
     GET request,, parameters are passed within the URI
     */
    @PostMapping(value = {"/data", "/data2"})
    public String dataFilter(
            @RequestParam(value = "animal", required = false) String animal,
            @RequestParam(value = "animalKey") Animal.KeyType animalKey,
            @RequestParam(value = "ice", required = false) String ice,
            @RequestParam(value = "iceKey") IceCream.KeyType iceKey,

            Model model)
    {

        //re-init data according to check boxes selected
        count = 0;
        queue = new CircleQueue();
        //for each category rebuild data, set presentation and data defaults
        if (animal != null) {
            this.addCQueue(Animal.animalData());  //adding Animal data to queue
            this.animal = true;             //persistent selection from check box selection
            this.animalKey = animalKey;     //persistent enum update from radio button selection
            Animal.key = this.animalKey;    //toString configure for sort order
        } else {
            this.animal = false;
        }

        if (ice != null) {
            this.addCQueue(IceCream.iceCreamData());  //adding ice data to queue
            this.ice = true;             //persistent selection from check box selection
            this.iceKey = iceKey;     //persistent enum update from radio button selection
            IceCream.key = this.iceKey;    //toString configure for sort order
        } else {
            this.ice = false;
        }

        //sort data according to selected options
        this.queue.insertionSort();
        //render with options
        model.addAttribute("ctl", this);
        if (!this.ice) {
            return "data";
        } else {
            return "data2";
        }
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
        trial.addCQueue(Animal.animalData());
        trial.addCQueue(IceCream.iceCreamData());

        //display queue objects in queue order
        ConsoleMethods.println("Add order (all data)");
        trial.printCQueue();

        //sort queue objects by specific element within the object and display in sort order
        Animal.key = Animal.KeyType.name;
        IceCream.key = IceCream.KeyType.flavor;

        trial.queue.insertionSort();
        ConsoleMethods.println("Sorted order (key only)");
        trial.printCQueue();

        //display queue objects, changing output but not sort
        Animal.key = Animal.KeyType.title;
        IceCream.key = IceCream.KeyType.title;

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