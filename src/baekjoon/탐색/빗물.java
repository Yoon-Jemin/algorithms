package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물 {
    /**
     * 4 8
     * 3 1 2 3 4 1 1 2
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int water = 0;
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        int[] map = new int[width];
        int maxIndex = 0;
        int maxHeight = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < width; i++) {
            map[i] = Integer.parseInt(st.nextToken());
            if (map[i] > maxHeight) {
                maxHeight = map[i];
                maxIndex = i;
            }
        }

        int leftPointer = 0;
        int currentMaxHeight = map[leftPointer];
        while (leftPointer < maxIndex) {
            int nextHeight = map[leftPointer + 1];
            if (currentMaxHeight > nextHeight) {
                water += currentMaxHeight - nextHeight;
            } else if (nextHeight > currentMaxHeight) {
                currentMaxHeight = nextHeight;
            }
            leftPointer++;
        }

        int rightPointer = width - 1;
        currentMaxHeight = map[rightPointer];
        while (rightPointer > maxIndex) {
            int nextHeight = map[rightPointer - 1];
            if (currentMaxHeight > nextHeight) {
                water += currentMaxHeight - nextHeight;
            } else if (nextHeight > currentMaxHeight) {
                currentMaxHeight = nextHeight;
            }
            rightPointer--;
        }

        System.out.println(water);
    }
}
