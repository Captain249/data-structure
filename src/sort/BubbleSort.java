package sort;

/**
 * @author shenzhuojun
 * @version 1.0 2022/9/25 1:25 上午
 */
public class BubbleSort {

    /**
     * @param arr 数组
     * @param n   数组长度
     */
    public static void sort(int[] arr, int n) {
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 是否需要退出排序（如果无换位，则说明数据顺序）
            boolean noExchange = true;
            // j < n-i 确定排序的次数
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    noExchange = false;
                }
            }
            if (noExchange) {
                break;
            }
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
