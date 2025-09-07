package baekjoon.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 계보복원 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();


        int n = Integer.parseInt(st.nextToken());   // 마을에 살고 있는 사람의 수
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();
            map.put(name, i);
            indegree.put(name, 0);
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());   // 계보의 수
        ArrayList<String>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String person = st.nextToken();
            String ancestor = st.nextToken();
            graph[map.get(ancestor)].add(person);
            indegree.put(person, indegree.get(person) + 1);
        }

        List<String> roots = new ArrayList<>();
        for (String key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                roots.add(key);
            }
        }

        sb.append(roots.size() + "\n");
        Collections.sort(roots);
        for (String root : roots) sb.append(root + " ");
        sb.append("\n");

        Map<String, List<String>> ans = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.addAll(roots);
        while (!queue.isEmpty()) {
            String nowPerson = queue.poll();
            List<String> children = new ArrayList<>();

            for (String child : graph[map.get(nowPerson)]) {
                indegree.put(child, indegree.get(child) - 1);
                if (indegree.get(child) == 0) {
                    queue.add(child);
                    children.add(child);
                }
            }

            ans.put(nowPerson, children);
        }

        List<String> res = new ArrayList<>();
        for (String key : ans.keySet()) {
            res.add(key);
        }

        Collections.sort(res);
        for (String key : res) {
            sb.append(key + " ");
            sb.append(ans.get(key).size() + " ");
            Collections.sort(ans.get(key));
            for (String child : ans.get(key)) {
                sb.append(child + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
