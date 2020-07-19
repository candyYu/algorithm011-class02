import java.util.*;

/**
 * Created by yuping on 2020/7/17.
 */
public class WordLadder {

    // 注意 这里原来没有直接return count 选择了个标志位，导致时间超时，需要仔细看看为啥
    public int ladderLength(String beginWord, String endWord, List<String> wordList){

        //bfs 广度优先遍历 使用queue
        //init
        HashSet<String> wordsSet = new HashSet();
        for(String word : wordList) {
            wordsSet.add(word);
        }

        //bfs visited
        int count = 1;
        Queue<String> queue = new LinkedList();
        HashSet<String> visited = new HashSet();
        queue.add(beginWord);

        while (!queue.isEmpty()) {
            int size  = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                visited.add(currWord);
                //思路一  当前档次currWord 可以做的变幻， 变为26个字母中的一个，且在字典中
                //思路二  当前单词 与 字典中的内容对比 字母相差不超过1的内容记录下来  对比下来选择
                //terminator
                if (currWord.equals(endWord)) {
                    return count;
                }
                // char[] charArray = currWord.toCharArray();

                // // 修改每一个字符
                // for (int j = 0; j < currWord.length(); j++) {
                //     // 一轮以后应该重置，否则结果不正确
                //     char originChar = charArray[j];

                //     for (char k = 'a'; k <= 'z'; k++) {
                //         if (k == originChar) {
                //             continue;
                //         }

                //         charArray[j] = k;
                //         String nextWord = String.valueOf(charArray);

                //         if (wordsSet.contains(nextWord)) {
                //

                //             if (!visited.contains(nextWord)) {
                //                 queue.add(nextWord);
                //                 // 注意：添加到队列以后，必须马上标记为已经访问
                //                 visited.add(nextWord);
                //             }
                //         }
                //     }
                //     // 恢复
                //     charArray[j] = originChar;
                // }

                List<String> changeWords = getOneChangeWords(currWord,wordsSet,visited);
                for (String changeWord : changeWords) {
                    visited.add(changeWord);
                    queue.add(changeWord);
                    visited.remove(changeWord);
                }

            }
            count++;

        }
        return  0;
    }

    private List<String> getOneChangeWords(String currentWord, HashSet<String> words, HashSet<String> visited)    {    List<String> oneChangeWords = new ArrayList();
        for (String word : words) {
            int diff = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != currentWord.charAt(i)) {
                    diff++;
                }
                if (diff > 1) {
                    break;
                }
            }
            if (diff == 1 && !visited.contains(word)) {
                oneChangeWords.add(word);
            }

        }
        return oneChangeWords;

    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        String[] wordListArray = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        Collections.addAll(wordList, wordListArray);
        WordLadder solution = new WordLadder();
        int res = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println(res);
    }


    //双端bfs
    public int ladderLengthMethod2(String beginWord, String endWord, List<String> wordList) {
        Set<String> allWordSet = new HashSet<>(wordList);
        if (!allWordSet.contains(endWord)) {
            return 0;
        }

        // 从两端BFS遍历要用的队列
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        // 两端已经遍历过的节点
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        queue1.offer(beginWord);
        queue2.offer(endWord);
        visited1.add(beginWord);
        visited2.add(endWord);

        int count = 0;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
            if (queue1.size() > queue2.size()) {
                Queue<String> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                Set<String> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }
            int size1 = queue1.size();
            while (size1-- > 0) {
                String s = queue1.poll();
                char[] chars = s.toCharArray();
                for (int j = 0; j < s.length(); ++j) {
                    // 保存第j位的原始字符
                    char c0 = chars[j];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chars[j] = c;
                        String newString = new String(chars);
                        // 已经访问过了，跳过
                        if (visited1.contains(newString)) {
                            continue;
                        }
                        // 两端遍历相遇，结束遍历，返回count
                        if (visited2.contains(newString)) {
                            return count + 1;
                        }
                        // 如果单词在列表中存在，将其添加到队列，并标记为已访问
                        if (allWordSet.contains(newString)) {
                            queue1.offer(newString);
                            visited1.add(newString);
                        }
                    }
                    // 恢复第j位的原始字符
                    chars[j] = c0;
                }
            }
        }
        return 0;
    }




}
