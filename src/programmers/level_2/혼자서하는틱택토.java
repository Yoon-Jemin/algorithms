package programmers.level_2;

public class 혼자서하는틱택토 {

    public static void main(String[] args) {

        String[] board = {"O.X", ".O.", "..X"};
//        String[] board = {"OOO", ".O.", "XXX"};
//        String[] board = {"...", ".X.", "..."};
//        String[] board = {"...", "...", "..."};

        System.out.println(solution(board));
    }

    public static int solution(String[] board) {
        int answer = 1;

        int count_O = 0;
        int count_X = 0;

        boolean isWinner_O = false;
        boolean isWinner_X = false;

        // 세로로 승리자가 있는지 확인
        for(int i = 0; i < 3; i++){
            if(board[i].charAt(0) != '.'){
                if(board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)){
                    if(board[i].charAt(0) == 'O') isWinner_O = true;
                    if(board[i].charAt(0) == 'X') isWinner_X = true;
                }
            }
        }

        // 가로로 승리자가 있는지 확인
        for(int i = 0; i < 3; i++){
            if(board[0].charAt(i) != '.'){
                if(board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)){
                    if(board[0].charAt(i) == 'O') isWinner_O = true;
                    if(board[0].charAt(i) == 'X') isWinner_X = true;
                }
            }
        }

        // 대각선으로 승리자가 있는지 확인
        if(board[0].charAt(0) != '.'){
            if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
                if(board[0].charAt(0) == 'O') isWinner_O = true;
                if(board[0].charAt(0) == 'X') isWinner_X = true;
            }
        }
        if(board[0].charAt(2) != '.'){
            if(board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
                if(board[0].charAt(2) == 'O') isWinner_O = true;
                if(board[0].charAt(2) == 'X') isWinner_X = true;
            }
        }

        for(int i = 0; i < board.length; i++) {
            String s = board[i];
            for(int j = 0; j < s.length(); j++) {
                if(s.charAt(j) == 'O') count_O++;
                if(s.charAt(j) == 'X') count_X++;
            }
        }

        if(isWinner_O && isWinner_X) {
            answer = 0;
        }
        if(isWinner_O && !isWinner_X) {
            if(count_O != count_X + 1) answer = 0;
        }
        if(!isWinner_O && isWinner_X) {
            if(count_O != count_X) answer = 0;
        }
        if(!isWinner_O && !isWinner_X) {
            if(count_O != count_X && count_O != count_X + 1 ) answer = 0;
        }

        return answer;
    }
}
