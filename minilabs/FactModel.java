
public class FactModel{
    public static void main (String[] args) {
        //only algo has been implemented for now
    }

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
