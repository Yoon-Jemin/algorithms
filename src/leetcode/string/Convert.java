package leetcode.string;

public class Convert {

    public static void main(String[] args) {
//        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("AB", 1));
    }

    public static String convert(String s, int numRows) {
        String[][] table = new String[numRows][s.length()];

        if (numRows == 1) return s;

        int x_index = 0;
        int y_index = 0;
        boolean moveDown = true;
        for (int i = 0; i < s.length(); i++) {
            String now = s.substring(i, i + 1);
            table[y_index][x_index] = now;

            if (moveDown) {
                y_index++;
            } else {
                y_index--;
                x_index++;
            }

            if (y_index == numRows - 1 || y_index == 0) {
                moveDown = !moveDown;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] != null) sb.append(table[i][j]);
            }
        }

        return sb.toString();
    }
}
