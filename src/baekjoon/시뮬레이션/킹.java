package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 킹 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String strKing = st.nextToken();
        String strStone = st.nextToken();

        int n = Integer.parseInt(st.nextToken());

        List<String> moves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            moves.add(st.nextToken());
        }

        Map<String, Integer> mapY = new HashMap<>();
        mapY.put("A", 1);
        mapY.put("B", 2);
        mapY.put("C", 3);
        mapY.put("D", 4);
        mapY.put("E", 5);
        mapY.put("F", 6);
        mapY.put("G", 7);
        mapY.put("H", 8);

        Map<String, int[]> direction = new HashMap<>();
        direction.put("R", new int[]{0, 1});
        direction.put("L", new int[]{0, -1});
        direction.put("T", new int[]{-1, 0});
        direction.put("B", new int[]{1, 0});
        direction.put("RT", new int[]{-1, 1});
        direction.put("LT", new int[]{-1, -1});
        direction.put("RB", new int[]{1, 1});
        direction.put("LB", new int[]{1, -1});

        int[] king = new int[] {9 - Integer.parseInt(strKing.substring(1, 2)), mapY.get(strKing.substring(0, 1))};
        int[] stone = new int[] {9 - Integer.parseInt(strStone.substring(1, 2)), mapY.get(strStone.substring(0, 1))};

        for (String move : moves) {
            int[] moveDirection = direction.get(move);

            int nextKingX = king[0] + moveDirection[0];
            int nextKingY = king[1] + moveDirection[1];

            if (nextKingX > 8 || nextKingX < 1 || nextKingY > 8 || nextKingY < 1) continue;

            if (nextKingX == stone[0] && nextKingY == stone[1]) {   // 겹침
                int nextStoneX = stone[0] + moveDirection[0];
                int nextStoneY = stone[1] + moveDirection[1];
                if (nextStoneX > 8 || nextStoneX < 1 || nextStoneY > 8 || nextStoneY < 1) continue;
                stone[0] = nextStoneX;
                stone[1] = nextStoneY;
            }

            king[0] = nextKingX;
            king[1] = nextKingY;
        }

        printLocation(king);
        printLocation(stone);
    }

    private static void printLocation(int[] location) {
        StringBuilder sb = new StringBuilder();

        Map<Integer, String> mapY = new HashMap<>();
        mapY.put(1, "A");
        mapY.put(2, "B");
        mapY.put(3, "C");
        mapY.put(4, "D");
        mapY.put(5, "E");
        mapY.put(6, "F");
        mapY.put(7, "G");
        mapY.put(8, "H");

        sb.append(mapY.get(location[1]));
        sb.append(9- location[0]);

        System.out.println(sb.toString());
    }
}
