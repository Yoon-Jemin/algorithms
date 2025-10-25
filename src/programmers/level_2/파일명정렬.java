package programmers.level_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 파일명정렬 {

    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] result = solution(files);

        for (String r : result) System.out.println(r);
    }

    public static class File {
        int idx;
        String head;
        int number;
        String tail;

        String origin;

        public File(int idx, String origin) {
            this.idx = idx;
            this.origin = origin;
        }

        // HEAD: 숫자가 아닌 문자 (최소 한 글자 이상)
        // NUMBER: 한 글자에서 최대 다섯 글자 사이의 연속된 숫자 (앞쪽에 0 가능)
        // TAIL: 나머지 부분으로, 여기에는 숫자가 다시 나타날 수도 있으며, 아무 글자도 없을 수 있음
        public void preProcess() {

            // HEAD
            int headIdx = 0;
            for (int i = 0; i < origin.length(); i++) {
                if (Character.isDigit(origin.charAt(i))) {
                    headIdx = i;
                    break;
                }
            }
            this.head = origin.substring(0, headIdx).toLowerCase();

            // NUMBER
            int numberIdx= headIdx;
            while (numberIdx < origin.length() && Character.isDigit(origin.charAt(numberIdx))) {
                numberIdx++;
            }
            this.number = Integer.parseInt(origin.substring(headIdx, numberIdx));

            // TAIL
            this.tail = origin.substring(numberIdx);
        }
    }

    public static String[] solution(String[] files) {
        int n = files.length;

        List<File> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            File file = new File(i, files[i]);
            file.preProcess();
            list.add(file);
        }

        // 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬 (문자열 비교 시 대소문자 구분을 하지 않음)
        // NUMBER의 숫자 순으로 정렬
        // 두 파일의 HEAD 부분과, NUMBER의 숫자도 같을 경우, 원래 입력에 주어진 순서를 유지
        Collections.sort(list, (a, b) -> {
            if (a.head.equals(b.head)) {
                if (a.number != b.number) {
                    return a.number - b.number;
                } else {
                    return a.idx - b.idx;
                }
            } else {
                return a.head.compareTo(b.head);
            }
        });

        String[] answer = new String[n];
        for (int i = 0; i < n; i++) answer[i] = list.get(i).origin;
        return answer;
    }
}
