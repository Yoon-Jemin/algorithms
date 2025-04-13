package programmers.level_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 오픈채팅방 {

    public static void main(String[] args) {
        String[] record = {
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"
        };
        String[] result = solution(record);
        for (String r : result) {
            System.out.println(r);
        }
    }

    public static String[] solution(String[] record) {
        List<String> list = new ArrayList<>();

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] words = record[i].split(" ");
            String command = words[0];
            if (command.equals("Leave")) continue;

            String uid = words[1];
            String nickName = words[2];

            if (command.equals("Enter") || command.equals("Change")) {
                map.put(uid, nickName);
            }
        }

        for (int i = 0; i < record.length; i++) {
            String[] words = record[i].split(" ");
            String command = words[0];
            if (command.equals("Change")) continue;
            String uid = words[1];

            StringBuilder sb = new StringBuilder();
            sb.append(map.get(uid));
            if (command.equals("Enter")) {
                sb.append("님이 들어왔습니다.");
            } else if (command.equals("Leave")) {
                sb.append("님이 나갔습니다.");
            }
            list.add(sb.toString());
        }

        return list.toArray(new String[0]);
    }
}
