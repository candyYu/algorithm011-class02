import java.util.ArrayList;

/**
 * Created by yuping on 2020/8/23.
 */
public class ReverseWordsInString2 {

    public String reverseWords(String s) {
        String[] words = split(s);
        StringBuilder sb = new StringBuilder();
        for(String word : words) {
            sb.append(reverse(word)+" ");
        }
        return sb.toString().trim();

    }

    public String[] split(String s) {
        ArrayList< String > words = new ArrayList< >();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words.add(word.toString());
                word.setLength(0);
            }else {
                word.append(s.charAt(i));
            }
        }
        words.add(word.toString());
        return words.toArray(new String[words.size()]);

    }

    public String reverse(String s) {
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            res.insert(0,s.charAt(i));
        return res.toString();
    }

}
