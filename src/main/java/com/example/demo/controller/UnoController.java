package com.example.demo.controller;
import com.example.demo.models.linkedlists.CircleQueue;
import lombok.Getter;

import com.example.demo.util.Playfield;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.demo.util.Actions.ACTIONS;

@Controller
public class UnoController {

    Playfield playf;

    @GetMapping("/unoGame")
    public String unoGame(Model model)
    {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("unoGame");

        model.addAttribute("playfield", playf);

        return "unoGame";

    }

    @GetMapping("unoInit")
    public String test(@RequestParam(name="numPlayers", required = false) String value,
                       @RequestParam(name = "names", required = false) String value2,
                       @RequestParam(name = "numBots", required = false) String value3,
                       Model model) throws IOException, LineUnavailableException{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("unoInit");
        if (value != null) {
            List<String> allMatches = new ArrayList<String>();
            int InputVal;

            try {
                InputVal = (int) Long.parseLong(value);
            } catch (Exception Ex) {
                model.addAttribute("error1", "Please input a valid integer.");
                return "unoInit";
            }

            String InputVal2;

            try {
                InputVal2 = value2;
            } catch (Exception Ex) {
                model.addAttribute("error2", "Please input a valid string and separate names with commas");
                return "unoInit";
            }
            Matcher m = Pattern.compile("[^,]+")
                    .matcher(InputVal2);
            while (m.find()) {
                allMatches.add(m.group());
            }

            int InputVal3;

            try {
                InputVal3 = (int) Long.parseLong(value3);
            } catch (Exception Ex) {
                model.addAttribute("error3", "Please input a valid integer");
                return "unoInit";
            }

            playf = new Playfield(InputVal, allMatches, InputVal3);
            System.out.println("playf object created");
            return "unoGame";
        }

        return "unoInit";
    }


    @PostMapping("/unoPlace")
    public String unoPlace(@RequestParam(name = "selectedCard") int cardIndex,
            Model model)
    {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("unoPlace");

        ACTIONS action = ACTIONS.PLACE;
        try {
            playf = playf.execute(action, cardIndex);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        model.addAttribute("playfield", playf);


        return "unoPlace";

    }

    @PostMapping("/unoDraw")
    public String unoDraw(Model model)
    {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("unoDraw");

        ACTIONS action = ACTIONS.DRAW;
        try {
            playf = playf.execute(action, 0);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        model.addAttribute("playfield", playf);

        return "unoDraw";

    }

}
