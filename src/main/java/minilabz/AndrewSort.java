package minilabz;

import java.util.ArrayList;

public class AndrewSort {

    public static ArrayList<Integer> Integers = new ArrayList<>();
    public static ArrayList<String> Strings = new ArrayList<>();
    public static ArrayList<Pet> Pets = new ArrayList<>();

    public static void reset() {
        Integers.clear();
        Strings.clear();
        Pets.clear();
        Integers.add(36);
        Integers.add(1);
        Integers.add(116);
        Integers.add(0);
        Integers.add(24);
        Strings.add("donkey");
        Strings.add("dog");
        Strings.add("chicken");
        Strings.add("aaa");
        Strings.add("xyz");
        Pets.add(new Pet("Clifford", "Big Red Dog"));
        Pets.add(new Pet("Garfield", "Cat"));
        Pets.add(new Pet("Marty", "Giraffe"));
        Pets.add(new Pet("Alexander", "Great"));
        Pets.add(new Pet("Bing Bong", "Imaginary Friend"));

    }

    public static String StringBubbleSort(ArrayList<String> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - i - 1; j++) {
                if (array.get(j).compareTo(array.get(j+1)) > 0) {
                    String temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        String result = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i);
        }
        System.out.println(result);
        return result;

    }

    public static String intBubbleSort(ArrayList<Integer> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - i - 1; j++) {
                if (array.get(j) > array.get(j+1)) {
                    Integer temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        String result = array.get(0).toString();
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i);
        }
        System.out.println(result);
        return result;

    }

    public static String PetBubbleSort(ArrayList<Pet> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - i - 1; j++) {
                if (array.get(j).toString().compareTo(array.get(j+1).toString()) > 0) {
                    Pet temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        String result = array.get(0).toString();
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i).toString();
        }
        System.out.println(result);
        return result;

    }

    public static String StringSelectionSort(ArrayList<String> array) {
        String first = array.get(0);
        int firstIndex = 0;
        for (int i = 0; i < array.size(); i++) {
            first = array.get(i);
            firstIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (first.compareTo(array.get(j)) > 0) {
                    first = array.get(j);
                    firstIndex = j;
                }
            }
            String temp = array.get(i);
            array.set(i, array.get(firstIndex));
            array.set(firstIndex, temp);
        }
        String result = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i);
        }
        System.out.println(result);
        return result;
    }

    public static String intSelectionSort(ArrayList<Integer> array) {
        int first = array.get(0);
        int firstIndex = 0;
        for (int i = 0; i < array.size(); i++) {
            first = array.get(i);
            firstIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (first > array.get(j)) {
                    first = array.get(j);
                    firstIndex = j;
                }
            }
            int temp = array.get(i);
            array.set(i, array.get(firstIndex));
            array.set(firstIndex, temp);
        }
        String result = array.get(0).toString();
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i);
        }
        System.out.println(result);
        return result;
    }

    public static String PetSelectionSort(ArrayList<Pet> array) {
        Pet first = array.get(0);
        int firstIndex = 0;
        for (int i = 0; i < array.size(); i++) {
            first = array.get(i);
            firstIndex = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (first.toString().compareTo(array.get(j).toString()) > 0) {
                    first = array.get(j);
                    firstIndex = j;
                }
            }
            Pet temp = array.get(i);
            array.set(i, array.get(firstIndex));
            array.set(firstIndex, temp);
        }
        String result = array.get(0).toString();
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i).toString();
        }
        System.out.println(result);
        return result;
    }

    public static String StringInsertionSort(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (array.get(j).compareTo(array.get(j-1)) > 0) {
                    break;
                }
                String temp = array.get(j);
                array.set(j, array.get(j - 1));
                array.set(j - 1, temp);
            }
        }
        String result = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i);
        }
        System.out.println(result);
        return result;
    }

    public static String intInsertionSort(ArrayList<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (array.get(j) > array.get(j-1)) {
                    break;
                }
                int temp = array.get(j);
                array.set(j, array.get(j - 1));
                array.set(j - 1, temp);
            }
        }
        String result = array.get(0).toString();
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i).toString();
        }
        System.out.println(result);
        return result;
    }

    public static String PetInsertionSort(ArrayList<Pet> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (array.get(j).toString().compareTo(array.get(j-1).toString()) > 0) {
                    break;
                }
                Pet temp = array.get(j);
                array.set(j, array.get(j - 1));
                array.set(j - 1, temp);
            }
        }
        String result = array.get(0).toString();
        for (int i = 1; i < array.size(); i++) {
            result += ", " + array.get(i);
        }
        System.out.println(result);
        return result;
    }
}
