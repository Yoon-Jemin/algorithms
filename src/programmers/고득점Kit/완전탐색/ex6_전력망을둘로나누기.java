package programmers.고득점Kit.완전탐색;

import java.util.HashSet;
import java.util.Set;

public class ex6_전력망을둘로나누기 {
    public static void main(String[] args) {
        int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
        int n = 7;
        System.out.println(solution1(n, wires));
    }

    public static int solution1(int n, int[][] wires) {
        int answer = n;

        for (int i = 0; i < wires.length; i++) {
            // 각 전선을 끊어보며 시도
            Set<Integer> network = new HashSet<>();
            int start = wires[i][0];  // 끊어진 전선의 한쪽 송전탑부터 시작
            network.add(start);

            boolean changed;
            do {
                changed = false;
                for (int j = 0; j < wires.length; j++) {
                    if (i == j) continue;  // 현재 끊은 전선은 무시

                    int v1 = wires[j][0];
                    int v2 = wires[j][1];

                    if (network.contains(v1) && !network.contains(v2)) {
                        network.add(v2);
                        changed = true;
                    } else if (network.contains(v2) && !network.contains(v1)) {
                        network.add(v1);
                        changed = true;
                    }
                }
            } while (changed);  // 더 이상 변화가 없을 때까지 반복

            int size1 = network.size();
            int size2 = n - size1;
            answer = Math.min(answer, Math.abs(size1 - size2));
        }

        return answer;
    }

    static int N, min;
    static int[][] map;
    static int[] vst;

    public static int solution2(int n, int[][] wires) {
        N = n;
        min = n;

        map = new int[n+1][n+1];
        vst = new int[n+1];
        for(int[] wire : wires){
            int a = wire[0];
            int b = wire[1];
            map[a][b] = map[b][a] = 1;
        }
        dfs(1);
        return min;
    }

    private static int dfs(int n) {
        vst[n] = 1;
        int child = 1;
        for(int i = 1; i <= N; i++){
            if(vst[i] == 0 && map[n][i] == 1){
                vst[i] = 1;
                child += dfs(i);
            }
        }
        min = Math.min(min, Math.abs(child - (N - child)));
        return child;
    }
}
