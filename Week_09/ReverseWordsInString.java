import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yuping on 2020/8/23.
 */
public class ReverseWordsInString {

    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') --right;

        Deque<String> d = new ArrayDeque();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);

    }

    public String reverseWords2(String s) {
        StringBuilder sb = timeSpaces(s);

        reverse(sb, 0, sb.length()-1);

        reverseEachWord(sb);

        return sb.toString();

    }

    private StringBuilder timeSpaces(String s) {
        int left = 0 , right = s.length()-1;
        while(left <= right && s.charAt(left) == ' ') left++;
        while(left <= right && s.charAt(right) == ' ') right--;

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c);

            ++left;
        }
        return sb;
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while(left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while(start < n) {
            while(end < n && sb.charAt(end) != ' ') end++;

            reverse(sb, start, end-1);

            start = end+1;
            end++;
        }
    }
}
