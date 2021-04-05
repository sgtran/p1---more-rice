package minilabs;

public class BinarySearchModel {
    int BinarySearchModel(int arr[], int l, int r, int x)
    {
        if (r >= l) {
            int middle = l + (r - l) / 2;

            if (arr[middle] == x)
                return middle;

            if (arr[middle] > x)
                return
                        BinarySearchModel(arr, l, middle - 1, x);

            return BinarySearchModel(arr, middle + 1, r, x);
        }

        return -1;
    }

}
