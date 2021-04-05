
public class FactModel{

    public static int fact(int n) {
        if (n >= 0) {
            if (n == 0) {return 1;}
            else {
                return n * fact(n - 1);
            }
        } else {
            return -1; //to indicate that n is negative
        }
    }
}
