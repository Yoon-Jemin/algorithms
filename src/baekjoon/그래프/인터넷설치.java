package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인터넷설치 {

    static int numPeople;
    static int numCable;
    static int numFree;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numPeople = Integer.parseInt(st.nextToken());
        numCable = Integer.parseInt(st.nextToken());
        numFree = Integer.parseInt(st.nextToken());

        graph = new ArrayList[numPeople + 1];
        for (int i = 1; i <= numPeople; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;
        for (int i = 0; i < numCable; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
            maxCost = Math.max(maxCost, c);
        }

        System.out.println(binarySearch(maxCost));
    }

    private static int binarySearch(int maxCost) {
        int left = 0;
        int right = maxCost;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canConnect(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private static boolean canConnect(int mid) {
        Queue<Integer> queue = new LinkedList<>();
        int[] freeEdges = new int[numPeople + 1];
        Arrays.fill(freeEdges, Integer.MAX_VALUE);
        freeEdges[1] = 0;
        queue.add(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int[] next : graph[cur]) {
                int nextNode = next[0];
                int cost = next[1];
                int newFree = freeEdges[cur] + (cost > mid ? 1 : 0);

                if (newFree <= numFree && newFree < freeEdges[nextNode]) {
                    freeEdges[nextNode] = newFree;
                    queue.add(nextNode);
                }
            }
        }

        return freeEdges[numPeople] <= numFree;
    }
}
