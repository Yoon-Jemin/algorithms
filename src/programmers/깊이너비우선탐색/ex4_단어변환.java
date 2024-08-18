package programmers.깊이너비우선탐색;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ex4_단어변환 {

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(begin, target, words));
    }

    static boolean[] visited;
    static Map<String, Integer> numMoved;
    static int size;
    public static int solution(String begin, String target, String[] words) {
        size = words.length;
        visited = new boolean[words.length];
        numMoved = new HashMap<>();

        numMoved.put(begin, 0);
        for(int i = 0; i < size; i++){
            numMoved.put(words[i], 0);
        }

        if(!numMoved.containsKey(target)) return 0;

        BFS(begin, target, words);

        return numMoved.get(target);
    }

    private static void BFS(String begin, String target, String[] words) {
        String word = begin;
        Queue<String> queue = new LinkedList<>();
        queue.offer(word);
        while(!queue.isEmpty()){
            String now = queue.poll();
            for(int i = 0; i < size; i++){
                String nextWord = words[i];
                if(!visited[i] && isNextWord(now, words[i])){
                    visited[i] = true;
                    numMoved.put(nextWord, numMoved.get(now) + 1);
                    queue.offer(nextWord);
                }
            }
        }

    }

    private static boolean isNextWord(String now, String word) {
        int different = 0;
        for(int i = 0; i < now.length(); i++){
            if(now.charAt(i) != word.charAt(i)) different++;
        }

        if(different == 1) return true;
        return false;
    }
}
