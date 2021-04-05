
public class BinarySearchModel {
    public BinarySearchModel(int arr[], int l, int r, int x){
        int result = search(arr, l, r, x);
        System.out.println("Element found at: " + Integer.toString(result));
    }

    int search(int arr[], int l, int r, int x)
    {
        if (r >= l) {
            int middle = l + (r - l) / 2;

            if (arr[middle] == x)
                return middle;

            if (arr[middle] > x)
                return search(arr, l, middle - 1, x);

            return search(arr, middle + 1, r, x);
        }

        return -1;
    }

}
