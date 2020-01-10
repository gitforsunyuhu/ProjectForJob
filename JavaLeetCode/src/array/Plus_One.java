package array;

public class Plus_One {
    class Solution {
        public int[] plusOne(int[] digits) {
            int length = digits.length;
            int num1 = 0;
            int c = 1;
            int temp = 0;
            for(int i=length-1; i>=0; i--){
                temp = c + digits[i];
                digits[i] = temp % 10;
                c = temp / 10;
            }
            if(c > 0){
                int[] answer = new int[length+1];
                answer[0] = c;
                for(int i=0; i< length ; i++){
                    answer[i+1] = digits[i];
                }
                return answer;
            }
            return digits;
        }
    }
}
