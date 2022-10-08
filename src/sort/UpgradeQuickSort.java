package sort;

/**
 * @author shenzhuojun
 * @version 1.0 2022/9/28 7:26 上午
 */
public class UpgradeQuickSort {

    // 快排优化，三数取中法获取枢纽值
    // 原因是当一组数本身就有序时，快排选取固定的某一位置元素（开头或者结尾）会是时间复杂度为O(n^2)
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = dealPivot(arr, low, high);
        int val = arr[pivot];
        int left = low;
        int right = high - 1;
        while (true) {
            // 左边找比枢纽值大的
            while (left < high && arr[++left] < val) {
                if (left >= right) {
                    break;
                }
            }
            // 右边找比枢纽值小的
            while (right > low && arr[--right] > val) {
                if (left >= right) {
                    break;
                }
            }
            if (left >= right) {
                break;
            } else {
                swap(arr, left, right);
            }
        }
        if (left < high) {
            swap(arr, pivot, left);
        }
        sort(arr, low, pivot - 1);
        sort(arr, pivot + 1, high);
    }

    // 三数取中，处理枢纽值
    public static int dealPivot(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        // 先保证左边是是最小的
        if (arr[low] > arr[mid]) {
            swap(arr, low, mid);
        }
        if (arr[low] > arr[high]) {
            swap(arr, low, high);
        }
        // 在保证中间是中等的
        if (arr[mid] > arr[high]) {
            swap(arr, mid, high);
        }
        // 把枢纽值放到right前面
        swap(arr, mid, high - 1);
        return high - 1;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 1};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
