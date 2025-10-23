package programmers.level_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 방금그곡 {

    public static void main(String[] args) {
//        String m = "ABCDEFG";
//        String[] musicinfos = {
//                "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"
//        };

        String m = "ABC";
        String[] musicinfos = {
                "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"
        };
        System.out.println(solution(m, musicinfos));
    }

    public static class Music {
        int idx;
        int start;
        int end;
        String title;
        String fullMelody;

        public Music(int idx, int start, int end, String title, String fullMelody) {
            this.idx = idx;
            this.start = start;
            this.end = end;
            this.title = title;
            this.fullMelody = fullMelody;
        }
    }

    public static String solution(String m, String[] musicinfos) {

        // musicinfos: 음악 제목, 재생이 시작되고 끝난 시각, 악보를 제공
        // 네오가 기억한 멜로디와 악보에 사용되는 음: C, C#, D, D#, E, F, F#, G, G#, A, A#, B
        // 각 음은 1분에 1개씩 재생
        // 음악은 반드시 처음부터 재생되며 음악 길이보다 재생된 시간이 길 때는 음악이 끊김 없이 처음부터 반복해서 재생
        // 음악 길이보다 재생된 시간이 짧을 때는 처음부터 재생 시간만큼만 재생

        // 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환
        // 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환
        m = normalize(m);

        int idx = 0;
        List<Music> musics = new ArrayList<>();
        for (String musicInfo : musicinfos) {
            String[] s = musicInfo.split(",");

            int start = getIntegerTime(s[0]);
            int end = getIntegerTime(s[1]);
            String title = s[2];
            String melody = normalize(s[3]);

            String fullMelody = "";
            for (int i = 0; i < end - start; i++) {
                fullMelody += melody.charAt(i % melody.length());
            }

            Music music = new Music(idx, start, end, title, fullMelody);
            musics.add(music);
            idx++;
        }

        Collections.sort(musics, (a, b) -> {
            if ((b.end - b.start) == (a.end - a.start)) return a.idx - b.idx;
            else return (b.end - b.start) - (a.end - a.start);
        });

        String answer = "(None)";
        for (Music music : musics) {
            String fullMelody = music.fullMelody;

            if (fullMelody.contains(m)) {
                answer = music.title;
                break;
            }
        }

        return answer;
    }

    private static String normalize(String s) {
        return s.replace("C#", "c")
                .replace("B#", "b")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a");
    }

    private static int getIntegerTime(String strStart) {
        String[] time = strStart.split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        return hour * 60 + minute;
    }
}
