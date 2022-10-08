package sort;

/**
 * @author shenzhuojun
 * @version 1.0 2022/9/25 9:38 下午
 */
public class MergeSort {

    public static int[] tmp;

    // 归并排序 分而治之
    public static void sort(int[] arr) {
        tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    // 递归表达式：sort(arr[0...n]) = sort(sort(0 ... mid),sort(mid+1 ... n))
    private static void sort(int[] arr, int low, int hight) {
        if (low >= hight) {
            return;
        }
        int mid = low + (hight - low) / 2;
        // 这一对sort，递归到最后的结果都是为同级的
        // sort(arr, low, mid) return 的时候 sort(arr, mid + 1, hight) 也一定 return
        // 之后两个单一的元素 merge，并且这两个元素的 index 在 arr 中一定是相邻的，这是这个算法实现的前提。
        // 回溯的时候，上一轮的 arr merge 过的下标的元素，已经是有序的了
        sort(arr, low, mid);
        sort(arr, mid + 1, hight);
        // 先分到单个元素，进行 merge，第二次回溯后，两个双元素的进行 merge ...
        merge(arr, low, mid, hight);
    }

    private static void merge(int[] arr, int low, int mid, int hight) {
        int tmpIndex = low;
        int leftIndex = low;
        int rightIndex = mid + 1;
        // 左右指针的数组找到一个小的，把这个小的数放到 tmp
        // 左右指针往下移一个，tmp 的指针也往下移一个
        while (leftIndex <= mid && rightIndex <= hight) {
            // 左边小
            if (arr[leftIndex] < arr[rightIndex]) {
                tmp[tmpIndex++] = arr[leftIndex++];
            } else {
                tmp[tmpIndex++] = arr[rightIndex++];
            }
        }
        // 剩下的数组直接追加，因为已经排序过了。
        while (leftIndex <= mid) {
            tmp[tmpIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= hight) {
            tmp[tmpIndex++] = arr[rightIndex++];
        }
        for (int index = low; index <= hight; index++) {
            arr[index] = tmp[index];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 1};
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
