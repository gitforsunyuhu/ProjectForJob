package math;

public class ReverseNum {
    class Solution {
        public int reverse(int x) {
            String str = x + "";
            StringBuffer sb = new StringBuffer();
            int flag = 0;
            int flag1 = 0;
            if(str.charAt(0) == '-'){
                flag = 1;
            }

            for(int i= str.length() -1; i>0 ; i--){
                if(flag1 == 0){
                    if(str.charAt(i) == '0'){
                        continue;
                    }else{
                        flag1 =1;
                        i++;
                    }
                }else{
                    sb.append(str.charAt(i));
                }
            }
            if(flag == 1){
                Long ll = Long.parseLong(sb.toString());
                if(-ll < -Integer.MAX_VALUE){
                    return 0;
                }
                return -ll.intValue();
            }else{
                sb.append(str.charAt(0));
                Long ll = Long.parseLong(sb.toString());
                if(ll > Integer.MAX_VALUE){
                    return 0;
                }
                return Integer.parseInt(sb.toString());
            }

        }
    }
    public static void main(String[] args){
        ReverseNum rn = new ReverseNum();
        System.out.println(rn.new Solution().reverse(1000923279));
    }
}
