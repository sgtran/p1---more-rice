package minilabz;

import java.util.stream.LongStream;

public class Factorial {
    public static long Recursion(long Value) {
        assert Value >= 0 : "'Value' must be a nonzero value.";
        return Value == 0 ? 1 : Value * Recursion(Value - 1);
    }

    public static long ForLoop(long Value) {
        assert Value >= 0 : "'Value' must be a nonzero value.";
        if (Value == 0) {
            return 1;
        } else {
            long Result = 1;
            for (long Counter = 2; Counter <= Value; ++Counter) {
                Result *= Counter;
            }
            return Result;
        }
    }

    public static long Stream(long Value) {
        assert Value >= 0 : "'Value' must be a nonzero value.";
        if (Value == 0) {
            return 1;
        } else {
            return LongStream.rangeClosed(1, Value).reduce(1, (long a, long b) -> a * b);
        }
    }

    public static void main(String[] args) {
        long Start = System.nanoTime();
        long Result = Recursion(20);
        System.out.println(Result);
        long End = System.nanoTime();

        System.out.println((End - Start) / 1000000.0);


    }
}
