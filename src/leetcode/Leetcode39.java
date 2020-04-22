package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode39
 *
 * @author dengyu
 * @date 2020/2/20
 * @since 1.0.0
 */
public class Leetcode39 {

    public static void main(String[] args) {
        Leetcode39 main = new Leetcode39();
        int target = 8;
        int[] candidates = new int[3];
        candidates[0] = 2;
        candidates[1] = 3;
        candidates[2] = 5;
        List<List<Integer>> temp = main.combinationSum(candidates, target);
        for(int i = 0; i < temp.size() ; i++){
            List<Integer> te = temp.get(i);
            for(int j = 0; j < te.size(); j++){
                System.out.print(te.get(j) + " ");
            }
            System.out.println();
        }
    }

    List<List<Integer>> answer = new ArrayList<>();
    int[] candidates;
    int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        boolean[] flag = new boolean[candidates.length];
        this.candidates = candidates;
        this.target = target;
        int size = candidates.length;
        for(int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - 1 - i; j++){
                if(candidates[j] > candidates[j+1]){
                    int temp = candidates[j];
                    candidates[j] = candidates[j+1];
                    candidates[j+1] = temp;
                }
            }
        }
        if(candidates[0] > target){
            return answer;
        }
        for(int i = 0; i < size; i++){
            if(candidates[i] > target)
                break;
            int[] re = new int[1000];
            int now = candidates[i];
            int count = 0;
            re[count++] = candidates[i];
            dfs(now, re, count, i);
        }
        return answer;
    }

    void dfs(int now, int[] re,int count, int index){
        if(now == this.target){
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < count; i++){
                temp.add(re[i]);
            }
            answer.add(temp);
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(now + candidates[i] > target){
                break;
            }
            for(int j = 1; candidates[i] * j + now <= target; j++){
                re[count++] = candidates[i];
                now += candidates[i];
                dfs(now, re, count, i);
            }
        }
    }
}
