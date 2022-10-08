package sort;

/**
 * @author shenzhuojun
 * @version 1.0 2022/9/25 10:29 下午
 */
public class QuickSort {

    // 快速排序，分而治之，优化了归并排序需要额外的 tmp 数组空间的不足。
    // 并且与归并排序不同过的是，归并排序是由下至上，先处理子任务，再合并。它是先分区，再计算子任务。
    // 快排不稳定，是因为如果右边有个和 pivot 相同的数，也会交换。
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        // 先分区在计算,最后一次的 sort 会是双数据，并且排完序
        int partition = partition(arr, low, high);
        sort(arr, low, partition - 1);
        sort(arr, partition + 1, high);
    }

    public static int partition(int[] arr, int low, int high) {
        int leftIndex = low;
        int rightIndex = high + 1;
        int val = arr[low];
        // 找到左右节点
        while (true) {
            // 右边找到比 val 小的数
            while (arr[--rightIndex] > val) {
                // 已经到最左边了
                if (leftIndex >= rightIndex) {
                    break;
                }
            }
            // 左边找比 val 大的数
            while (arr[++leftIndex] < val) {
                // 已经到最右边了
                if (leftIndex >= rightIndex) {
                    break;
                }
            }
            // 走完了
            if (leftIndex >= leftIndex) {
                break;
            } else {
                // 左右互换
                int tmp = arr[leftIndex];
                arr[leftIndex] = arr[rightIndex];
                arr[rightIndex] = tmp;
            }

        }
        // 右节点是准确的
        arr[low] = arr[rightIndex];
        arr[rightIndex] = val;
        return rightIndex;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 1};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
