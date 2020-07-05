import java.util.*;

/**
 * Created by yuping on 2020/7/5.
 */
public class TopKFrequent {


    public int[] topKFrequent(int[] nums, int k) {

        //hashmap存储频率
        HashMap<Integer,Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        //最小堆 O(nlogK)
        PriorityQueue<Integer> queue = new PriorityQueue((o1, o2)->map.get(o1)-map.get(o2));

        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(key);
            }else if( map.get(queue.peek()) < map.get(key)) {
                queue.poll();
                queue.offer(key);
            }
        }

        int res[] = new int[k];
        int idx = 0;
        while (!queue.isEmpty()) {
            res[idx++] = queue.poll();
        }
        return res;



    }

    public List<Integer> topKFrequentWithBucketSort(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }
}
