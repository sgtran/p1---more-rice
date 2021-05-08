package minilabz;

public class BinarySearch {

    private static Pet[] arr;

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        arr = new Pet[14];
        arr[0] = new Pet("Alex", "Lion");
        arr[1] = new Pet("Melman", "Giraffe");
        arr[2] = new Pet("Marty", "Zebra");
        arr[3] = new Pet("Gloria", "Hippo");
        arr[4] = new Pet("Julian", "King");
        arr[5] = new Pet("Mort", "Lemur");
        arr[6] = new Pet("Maurice", "Lemur");
        arr[7] = new Pet("Mason", "Chimp");
        arr[8] = new Pet("Phil", "Chimp");
        arr[9] = new Pet("Moto Moto", "Hippo");
        arr[10] = new Pet("Kowalski", "Penguin");
        arr[11] = new Pet("Skipper", "Penguin");
        arr[12] = new Pet("Rico", "Penguin");
        arr[13] = new Pet("Private", "Penguin");
    }

    public static int search(String name) {
        return binSearch(name, arr, 0, arr.length);
    }

    public static void sort() {
        Pet first = arr[0];
        int firstIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            first = arr[i];
            firstIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (first.toString().compareTo(arr[j].toString()) > 0) {
                    first = arr[j];
                    firstIndex = j;
                }
            }
            Pet temp = arr[i];
            arr[i] = arr[firstIndex];
            arr[firstIndex] = temp;
        }
    }

    public static int binSearch(String name, Pet[] sortedArray, int startIndex, int endIndex) {
        int mid = (startIndex + endIndex) / 2;

        if (sortedArray[mid].getNickname().equals(name)) {
            return mid;
        } else if (startIndex == endIndex) {
            return -1;
        } else if (sortedArray[mid].getNickname().compareTo(name) < 0) {
            int result = binSearch(name, sortedArray, mid + 1, endIndex);
            return result;
        } else {
            int result = binSearch(name, sortedArray, startIndex, mid - 1);
            return result;
        }


    }

    public static String printArray() {
        String result = arr[0].getNickname();
        for (int i = 1; i < arr.length; i++) {
            result += ", " + arr[i].getNickname();
        }
        return result;
    }

}
