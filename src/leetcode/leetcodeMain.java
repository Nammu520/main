package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * leetcodeMain
 *
 * @author dengyu
 * @date 2020/2/18
 * @since 1.0.0
 */
public class leetcodeMain {


    public static void main(String[] args) {
        Integer a = 300;
        Integer b = 300;
        System.out.println( a ==  b);


        leetcodeMain main = new leetcodeMain();
        int[] nums = new int[3];
        nums[0] = 1;
        nums[1] = 1;
        nums[2] = 3;
        List<List<Integer>> temp = main.permuteUnique(nums);
        for(int i = 0; i < temp.size() ; i++){
            List<Integer> te = temp.get(i);
            for(int j = 0; j < nums.length; j++){
                System.out.print(te.get(j) + " ");
            }
            System.out.println();
        }
    }

    List<List<Integer>> leetcode = new ArrayList<>();
    int size;
    int[] nums;

    // leetcode46
    private List<List<Integer>> permute(int[] nums) {
        size = nums.length;
        this.nums = nums;
        for(int i = 0; i < nums.length; i++){
            boolean[] flag = new boolean[size];
            flag[i] = true;
            int step = 0;
            int[] re = new int[size];
            re[step++] = nums[i];
            dfs(step, flag, re);
        }
        return leetcode;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        size = nums.length;
        this.nums = nums;
        for(int i = 0; i < nums.length; i++){
            boolean[] flag = new boolean[size];
            flag[i] = true;
            int step = 0;
            int[] re = new int[size];
            re[step++] = nums[i];
            dfs(step, flag, re);
        }
        Set<String> keys = new HashSet<>();
        List<List<Integer>> removes = new ArrayList<>();
        Iterator<List<Integer>> it = leetcode.iterator();
        int i = 0;
        while (it.hasNext()){
            List<Integer> temp = it.next();
            String key = outString(temp);
            if(!keys.add(key)){
                it.remove();
            }
        }
        leetcode.removeAll(removes);
        return leetcode;
    }

    String outString(List<Integer> list){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            sb.append(list.get(i)).append(":");
        }
        return sb.toString();
    }

    void dfs(int step, boolean[] flag, int[] re){
        if(step == size){
            List<Integer> res = new ArrayList<>();
            for(int i = 0; i < size; i++){
                res.add(re[i]);
            }
            leetcode.add(res);
            return;
        }
        for(int i = 0; i < size; i++){
            if(flag[i]){
                continue;
            }
            re[step++] = nums[i];
            flag[i] = true;
            dfs(step, flag, re);
            step--;
            flag[i] = false;
        }
    }


    // leetcode24
    public ListNode swapPairs(ListNode head) {
        if(head.next == null){
            return head;
        }
        int index = 2;
        int modeify = head.val;
        head.val = head.next.val;
        ListNode next = head.next;
        while(next != null){
            if(index % 2 == 0){
                next.val = modeify;
            }else{
                if(next.next != null){
                    modeify = next.val;
                    next.val = next.next.val;
                }
            }
            next = next.next;
            index++;
        }
        return head;
    }
}

