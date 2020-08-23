import java.util.HashMap;

/**
 * Created by yuping on 2020/8/23.
 */
public class UniqChar {

    public int firstUniqChar(String s) {
        HashMap<Character,Integer> map = new HashMap(26);
        for(Character c : s.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }

        int index = 0;
        for(Character c : s.toCharArray()){
            if(map.get(c) == 1) {
                return index;
            }
            index++;
        }
        return -1;


    }

}
