package serch;

/**
 * @author shenzhuojun
 * @version 1.0 2022/9/29 12:36 上午
 */
public class BinarySearch {

    // 二分查找注意三点
    // 1.while 内是 low <= high 想一下3个元素的情况，low == high 情况存在，如果用 < 跳过，就找不到结果
    // 2.mid 取值可以优化 low + (high-low)>>1
    // low high 更新 low = mid -1 ; high = mid + 1
    public static int search(int[] arr, int val) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) >> 1;
            if (val == arr[mid]) {
                return mid;
            } else if (val < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 递归实现
    public static int search(int[] arr, int low, int high, int val) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) >> 1;
        if (val == arr[mid]) {
            return mid;
        } else if (val < arr[mid]) {
            return search(arr, low, mid - 1, val);
        } else {
            return search(arr, mid + 1, high, val);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 1};
        int search1 = search(arr, 6);
        int search2 = search(arr, 0, arr.length - 1, 6);
        System.out.println(search2);
    }

}
