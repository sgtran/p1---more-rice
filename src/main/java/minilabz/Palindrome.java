package minilabz;
import com.example.demo.models.sorts.Bubble;
import java.util.Scanner;
import com.example.demo.models.sorts.Bubble;
import java.lang.System;

public class Palindrome {
    public String inp;
    public boolean palinverify;
    public long bint;
    public String result;

    public Palindrome() {
        this.palinverify = false;
        this.inp = "";
    }

    public boolean getPalinverify() {
        return palinverify;
    }

    public String getInp() {
        return inp;
    }

    public void setPalinverify() {
        this.palinverify = false;
    }

    public void setInp(String inp) {
        this.inp = inp;
        if (this.inp.length() == 0) { //default input
            this.inp = "b123457898754321b";
        }
        this.bint = System.nanoTime();
        this.palinverify = isPal(inp);
        this.bint = System.nanoTime() - bint;
    }

    public static boolean isPal(String s) {   // if length is 0 or 1 then String is palindrome
        if (s.length() == 0 || s.length() == 1)
            return true;
        if (s.charAt(0) == s.charAt(s.length() - 1))
            return isPal(s.substring(1, s.length() - 1));
        return false;
    }
}