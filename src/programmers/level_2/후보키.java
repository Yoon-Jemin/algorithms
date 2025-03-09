package programmers.level_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {

    public static void main(String[] args) {
        String[][] relation = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };

        System.out.println(solution(relation));
    }

    public static int solution(String[][] relation) {
        int colSize = relation[0].length;   // 4
        List<Integer> candidateKeys= new ArrayList<>();

        for (int subset = 1; subset < (1 << colSize); subset++) {
            if (!isUnique(relation, subset)) continue;
            if (!isMinimal(candidateKeys, subset)) continue;

            candidateKeys.add(subset);
        }

        return candidateKeys.size();
    }

    private static boolean isUnique(String[][] relation, int subset) {
        Set<String> uniqueRows = new HashSet<>();

        for (String[] row : relation) {
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                if ((subset & (1 << i)) != 0) key.append(row[i]).append(",");
            }

            if (uniqueRows.contains(key.toString())) return false;
            uniqueRows.add(key.toString());
        }

        return true;
    }

    public static boolean isMinimal(List<Integer> candidateKeys, int subset) {
        for (int key : candidateKeys) {
            if ((subset & key) == key) return false;
        }

        return true;
    }
}
