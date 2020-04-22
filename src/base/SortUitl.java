package base;

/**
 * SortUitl
 *
 * @author dengyu
 * @date 2020/3/11
 * @since 1.0.0
 */
public class SortUitl {

    // 由小到大排序

    /**
     * 插入排序,时间复杂度n^2 稳定排序
     *
     * @param array
     */
    public static void insertSort(int[] array){
        int size = array.length;
        for(int i = 1; i < size; i++){
            int temp = array[i];
            for(int j = i - 1; j >= 0; j--){
                if(array[j] < temp){  //如果位置j的元素比i位置的元素小,证明a[i]应该插入到a[j]的下一个位置a[j+1]
                    array[j+1] = temp;
                    break;
                }
                array[j+1] = array[j]; // 将a[j]后移
                if(j == 0 && array[0] > temp){ //处理a[i] 应该处于开头的请求
                    array[0] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序,时间复杂度n^2 稳定排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array){
        int size = array.length;
        for(int i = 0; i < size - 1; i++){
            for(int j = 0; j < size - 1 - i; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序,时间复杂度n^2 不稳定排序
     * 序列5 8 5 2 9，我们知道第一遍选择第1个元素5会和2交换，那么原序列中2个5的相对前后顺序就被破坏
     *
     * @param array
     */
    public static void selectSort(int[] array){
       for(int i = 0; i < array.length; i++){
           int min = array[i];
           int index = i;
           for(int j = i + 1; j < array.length; j++){
               if(array[j] < min){
                   min = array[j];
                   index = j;
               }
           }
           array[index] = array[i];
           array[i] = min;
       }
    }

    /**
     * 快速排序小,时间复杂度n*logn 不稳定排序
     *
     * @param array
     */
    public static void quickSort(int[] array){
        qSort(array, 0, array.length - 1);
    }

    private static void qSort(int[] a, int left, int right){
        if(left >= right){
            return;
        }
        int mid = partition(a, left, right);
        qSort(a, left, mid - 1);
        qSort(a, mid + 1, right);
    }

    private static int partition(int[] a, int left, int right){
        int key = a[left]; // 执行完成一次后,需要保证数组左边的都比key小,数组右边的都比key大,从而确认key的位置
        while (left < right){
            while(left < right && a[right] > key){ //right指针左移,直到a[right] < 选择的中间值(key)
                right--;
            }
            a[left] = a[right]; //直接将右边值赋值给左边
            while (left < right && a[left] < key){ //right指针右移,直到a[left] > 选择的中间值(key)
                left++;
            }
            a[right] = a[left];
        }
        a[left] = key;
        return left;
    }

    /**
     * 归并排序, 时间复杂度n*logn 稳定排序
     *
     * @param array
     */
    public static void mergeSort(int[] array){
        split(array, new int[array.length], 0, array.length - 1);
    }

    private static void split(int[] array, int[] temp, int left, int right){
        if(left < right){
            int mid = (right + left) / 2;
            split(array, temp, left, mid);
            split(array, temp, mid + 1, right);
            merge(array, temp, left, right, mid + 1);
        }
    }

    private static void merge(int[] array, int[] temp, int left, int right, int mid){
        int index = 0;
        int i = left, j = mid;
        while(i <= mid - 1 && j <= right){
            if(array[i] <= array[j]){
                temp[index++] = array[i++];
            }else {
                temp[index++] = array[j++];
            }
        }
        while(i <= mid - 1){
            temp[index++] = array[i++];
        }
        while (j <= right){
            temp[index++] = array[j++];
        }
        index = 0;
        for(int k = left; k <= right; k++){
            array[k] = temp[index++];
        }
    }

    /**
     * 堆排序 时间复杂度n*logn 不稳定排序
     *
     * @param array
     */
    public static void heapSort(int[] array){

    }

    public static void print(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");
    }
}
