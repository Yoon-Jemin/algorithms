package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 나무재테크 {

    public static class Soil {
        int nutrition;
        List<Tree> aliveTrees;
        List<Tree> deadTrees;

        public Soil(int nutrition) {
            this.nutrition = nutrition;
            aliveTrees = new ArrayList<>();
            deadTrees = new ArrayList<>();
        }
    }

    public static class Tree {
        int year;

        public Tree(int year) {
            this.year = year;
        }
    }

    static int N, M, K;
    static Soil[][] land;
    static int[][] A;
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 땅의 크기
        M = Integer.parseInt(st.nextToken());   // 나무의 개수
        K = Integer.parseInt(st.nextToken());   // 지나는 해의 수

        land = new Soil[N + 1][N + 1];

        // 처음에는 모든 땅에 5의 양분이 있음
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                land[i][j] = new Soil(5);
            }
        }

        // 각 칸에 추가되는 양분의 양
        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 처음에 심은 나무의 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            land[x][y].aliveTrees.add(new Tree(age));
        }


        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        // 살아있는 나무의 개수
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                answer += land[i][j].aliveTrees.size();
            }
        }

        System.out.println(answer);
    }

    /**
     * 봄
     * 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
     * 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다
     * 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽음
     */
    private static void spring() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (land[i][j].aliveTrees.isEmpty()) continue;

                List<Tree> treesToRemove = new ArrayList<>();
                for (Tree aliveTree : land[i][j].aliveTrees) {
                    if (land[i][j].nutrition >= aliveTree.year) {
                        land[i][j].nutrition -= aliveTree.year;
                        aliveTree.year++;
                    } else {
                        land[i][j].deadTrees.add(aliveTree);
                        treesToRemove.add(aliveTree);
                    }
                }

                for (Tree removeTree : treesToRemove) {
                    land[i][j].aliveTrees.remove(removeTree);
                }
            }
        }
    }

    /**
     * 여름
     * 봄에 죽은 나무가 양분으로 변하게 됨
     * 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
     */
    private static void summer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (land[i][j].deadTrees.isEmpty()) continue;

                for (Tree deadTree : land[i][j].deadTrees) {
                    land[i][j].nutrition += deadTree.year / 2;
                }

                land[i][j].deadTrees.clear();
            }
        }
    }

    /**
     * 가을
     * 가을에는 나무가 번식
     * 번식하는 나무는 나이가 5의 배수이어야 하며 -> 인접한 8개의 칸에 나이가 1인 나무가 생김
     */
    public static void fall() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Tree tree: land[i][j].aliveTrees) {
                    if (tree.year % 5 != 0) continue;

                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx <= 0 || nx > N || ny <= 0 || ny > N) continue;

                        land[nx][ny].aliveTrees.add(0, new Tree(1));
                    }
                }
            }
        }
    }

    /**
     * 겨울
     * S2D2가 땅을 돌아다니면서 땅에 양분을 추가
     * 각 칸에 추가되는 양분의 양은 A[r][c]
     */
    public static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                land[i][j].nutrition += A[i][j];
            }
        }
    }
}
