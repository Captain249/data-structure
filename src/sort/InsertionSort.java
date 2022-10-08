package sort;

/**
 * @author shenzhuojun
 * @version 1.0 2022/9/25 1:40 上午
 */
public class InsertionSort {

    public static void sort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        // arr[0] 默认已排序
        for (int i = 1; i < n; i++) {
            int val = arr[i];
            int j = i - 1;
            // 找插入位置
            for (; j >= 0; j--) {
                if (arr[j] > val) {
                    // 数据移动
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            // 插入数据
            arr[j + 1] = val;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 1};
        sort(arr, arr.length);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
