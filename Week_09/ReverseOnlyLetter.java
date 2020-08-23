import java.util.Stack;

/**
 * Created by yuping on 2020/8/23.
 */
public class ReverseOnlyLetter {

    public String reverseOnlyLetters(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length()-1;
        for(int i = 0; i < S.length(); i++) {
            if(Character.isLetter(S.charAt(i))) {
                while(!Character.isLetter(S.charAt(j))) {
                    j--;
                }
                ans.append(S.charAt(j--));
            }else {
                ans.append(S.charAt(i));
            }
        }
        return ans.toString();
    }

    public String reverseOnlyLetters2(String S) {
        Stack<Character> letters = new Stack();
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }
        return ans.toString();


    }

}
