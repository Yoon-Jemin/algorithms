package programmers.고득점Kit.해시;

import java.util.Arrays;

public class ex3_전화번호목록 {

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for(int i = 0; i < phone_book.length - 1; i++){
            String offset = phone_book[i];
            String compareNum = phone_book[i+1];

            if(compareNum.startsWith(offset)) return false;
        }

        return true;
    }
}
