package base;

/**
 * TestSort
 *
 * @author dengyu
 * @date 2020/3/11
 * @since 1.0.0
 */
public class TestSort {

    public static void main(String[] args) {
        int a[] = {2,6,8,9,7,3,1,5,4};
        SortUitl.mergeSort(a);
        SortUitl.print(a);
    }
}
