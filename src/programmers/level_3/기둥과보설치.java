package programmers.level_3;

import java.util.ArrayList;
import java.util.List;

public class 기둥과보설치 {

    public static void main(String[] args) {
        int n = 5;
        int[][] build_frame = {
                {1,0,0,1},
                {1,1,1,1},
                {2,1,0,1},
                {2,2,1,1},
                {5,0,0,1},
                {5,1,0,1},
                {4,2,1,1},
                {3,2,1,1}
        };

        int[][] result = solution(n, build_frame);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static class Node {
        int x;
        int y;
        boolean pillar;
        boolean beam;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.pillar = false;
            this.beam = false;
        }
    }

    static Node[][] nodes;
    static int N;
    public static int[][] solution(int n, int[][] build_frame) {

        // 구조물은 교차점 좌표를 기준으로 보는 오른쪽, 기둥은 위쪽
        N = n;
        nodes = new Node[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }

        // 삭제 기능: 기둥과 보를 삭제한 후 남은 기둥과 보들 또한 위 규칙을 만족해야 함
        for (int[] build : build_frame) {
            int x = build[1];   // x 축
            int y = build[0];   // y 축
            int a = build[2];   // 0: 기둥, 1: 보
            int b = build[3];   // 0: 삭제: 1:설치

            if (a == 0) {
                pillar(x, y, b);    // 기둥
            } else {
                beam(x, y, b);  // 보
            }
        }

        // return하는 배열의 원소 : [x, y, a] 형식
        // x, y는 기둥, 보의 교차점 좌표
        // a는 구조물의 종류를 나타내며, 0은 기둥, 1은 보
        // 배열은 x좌표 기준으로 오름차순 정렬하며, x좌표가 같을 경우 y좌표 기준으로 오름차순 정렬
        // x, y좌표가 모두 같은 경우 기둥이 보보다 앞에
        List<int[]> answer = new ArrayList<>();

        for (int j = 0; j <= n; j++) {
            for (int i = 0; i <= n; i++) {
                if (nodes[i][j].pillar) {
                    answer.add(new int[] {j, i, 0});
                }

                if (nodes[i][j].beam) {
                    answer.add(new int[] {j, i, 1});
                }
            }
        }

        return answer.toArray(new int[0][]);
    }

    private static void pillar(int x, int y, int b) {
        if (b == 1) {   // 설치
            if(isValidPillar(x, y)) nodes[x][y].pillar = true;
        } else {    // 삭제
            nodes[x][y].pillar = false;
            if (!isValidAll()) {
                nodes[x][y].pillar = true;
            }
        }
    }

    // 기둥: 바닥 위 or 보의 한쪽 끝 부분 위 or 다른 기둥 위
    private static boolean isValidPillar(int x, int y) {
        return (x == 0)
                || (nodes[x - 1][y].pillar)
                || (nodes[x][y].beam)
                || (y - 1 >= 0 && nodes[x][y - 1].beam);
    }


    // 보: 한쪽 끝 부분이 기둥 위 or 양쪽 끝 부분이 다른 보와 동시에 연결
    private static void beam(int x, int y, int b) {
        if (b == 1) {   // 설치
            if (isValidBeam(x, y)) nodes[x][y].beam = true;
        } else {    // 삭제
            nodes[x][y].beam = false;
            if (!isValidAll()) {
                nodes[x][y].beam = true;
            }
        }
    }

    private static boolean isValidBeam(int x, int y) {
        return (nodes[x - 1][y].pillar)
                || (nodes[x - 1][y + 1].pillar)
                || (y - 1 >= 0 && nodes[x][y - 1].beam && nodes[x][y + 1].beam);
    }

    private static boolean isValidAll() {
        for (int i = 0; i <= N; i++) {
            for (int j= 0; j <= N; j++) {
                Node cur = nodes[i][j];

                if (cur.pillar && !isValidPillar(i, j)) return false;
                if (cur.beam && !isValidBeam(i, j)) return false;
            }
        }

        return true;
    }

}
