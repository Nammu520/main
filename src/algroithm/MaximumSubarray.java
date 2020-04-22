package algroithm;

public class MaximumSubarray {



    public int maxSubArray(int[] nums) {
        int a0 = nums[0];
        int maxReturn = a0;
        for(int i = 1;i < nums.length ;i++){
            int a1 = Math.max(nums[i], a0 + nums[i]);
            a0 = a1;
            maxReturn = Math.max(a0, maxReturn);
        }
        return maxReturn;
    }

}
