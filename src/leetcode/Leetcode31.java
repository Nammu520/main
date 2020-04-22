package leetcode;


/**
 * Leetcode31
 *
 * @author dengyu
 * @date 2020/2/20
 * @since 1.0.0
 */
public class Leetcode31 {
    public static void main(String[] args) {
        Leetcode31 main = new Leetcode31();
        int[] nums = new int[3];
        nums[0] = 3;
        nums[1] = 2;
        nums[2] = 1;
        main.nextPermutation(nums);
        for(int i = 0 ; i < nums.length; i ++){
            System.out.println(nums[i]);
        }
    }

    public void nextPermutation(int[] nums) {
        int size = nums.length;
        if(size <= 1){
            return;
        }
        // 从后往前寻找第一个升序对(nums[i-1] < nums[i],然后从第i位,获取最小的比nums[i-1]大的数,交换位置,然后吧第i位到size-1为升序排列)
        for(int i = size - 1 ; i >= 1 ; i--){
            if(nums[i] > nums[i-1]){
                int max = nums[i];
                int index = i;
                for(int m = i + 1 ; m < size; m++){
                    if(max > nums[m] && nums[m] > nums[i - 1]){
                        max = nums[m];
                        index = m;
                    }
                }
                int temp1 = nums[i - 1];
                nums[i - 1] = nums[index];
                nums[index] = temp1;
                int count = 0;
                for(int n = i; n < size - 1; n++){
                    for(int m = i ; m < size - 1 - count; m++){
                        if(nums[m] > nums[m + 1]){
                            int temp = nums[m + 1];
                            nums[m + 1] = nums[m];
                            nums[m] = temp;
                        }
                    }
                    count++;
                }
                return;
            }
        }
        for(int i = 0 ; i < size - 1; i++){
            for(int j = 0 ; j < size - i - 1; j++){
                if(nums[j] > nums[j + 1]){
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
}
