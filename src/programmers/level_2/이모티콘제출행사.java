package programmers.level_2;

import java.util.ArrayList;

public class 이모티콘제출행사 {

    public static void main(String[] args) {

        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};

        int[] result = solution(users, emoticons);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    private static Emoticon[] arrays;

    private static int resultCount = 0;
    private static int resultPrice = 0;

    public static int[] solution(int[][] users, int[] emoticons) {

        arrays = new Emoticon[emoticons.length];

        for(int i = 0; i < emoticons.length; i++) {
            arrays[i] = new Emoticon(emoticons[i], 0);
        }

        search(0, users);

        return new int[]{resultCount, resultPrice};
    }

    private static void search(int index, int[][] users) {

        if(index == arrays.length) {
            int totalCount = 0;
            int totalPrice = 0;

            for(int i = 0; i < users.length; i++) {
                int minPercent = users[i][0];
                int minPrice = users[i][1];

                int tempPrice = 0;
                for(int j = 0; j < arrays.length; j++) {
                    if(minPercent <= arrays[j].discount){
                        tempPrice += arrays[j].price * (100 - arrays[j].discount) / 100;
                    }
                }

                if(tempPrice >= minPrice){
                    totalCount++;
                } else {
                    totalPrice += tempPrice;
                }
            }

            if(resultCount < totalCount) {
                resultCount = totalCount;
                resultPrice = totalPrice;
            } else if (resultCount == totalCount) {
                resultPrice = Math.max(totalPrice, resultPrice);
            }

        } else {
            for(int i = 10; i <= 40; i += 10){
                arrays[index].setDiscount(i);
                search(index + 1, users);
            }
        }

    }

    public static class Emoticon{
        public int price;
        public int discount;

        public Emoticon(int price, int discount) {
            this.price = price;
            this.discount = discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }
    }
}
