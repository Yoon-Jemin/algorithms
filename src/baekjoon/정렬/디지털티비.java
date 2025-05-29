package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 디지털티비 {

    static int pointer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] channels = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String channel = st.nextToken();
            if (channel.equals("KBS1")) {
                channels[i] = 1;
            } else if (channel.equals("KBS2")) {
                channels[i] = 2;
            }
        }

        List<Integer> list = new ArrayList<>();
        while (channels[0] != 1 || channels[1] != 2) {
            if (channels[0] != 1) {
                while (channels[pointer] != 1) {
                    pointer++;
                    list.add(1);
                }
                while (channels[0] != 1) {
                    int temp = channels[pointer];
                    channels[pointer] = channels[pointer - 1];
                    channels[pointer - 1] = temp;
                    pointer--;
                    list.add(4);
                }
            } else if (channels[1] != 2) {
                while (channels[pointer] != 2) {
                    pointer++;
                    list.add(1);
                }
                while (channels[1] != 2) {
                    int temp = channels[pointer];
                    channels[pointer] = channels[pointer - 1];
                    channels[pointer - 1] = temp;
                    pointer--;
                    list.add(4);
                }
            }
        }


        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
    }
}
