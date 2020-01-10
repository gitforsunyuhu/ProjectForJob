package array;

import java.util.LinkedList;
import java.util.List;

public class Pascal_Trangle2 {
    class Solution {
        public List<Integer> generate(int rowIndex) {
            List<Integer> listList = new LinkedList<Integer>();
            List<Integer> listList1 = new LinkedList<Integer>();
            int temp = 0;
            for(int i=0 ; i < rowIndex+1; i++){
                listList.add(1);
                listList1.add(1);
                for(int j =0; j<=i ; j++){
                    if(j == 0 || j== i){
                        listList.set(j,1);
                    }else{
                        temp = listList.get(j-1) + listList.get(j);
                        listList1.set(j,temp);
                    }
                }
                for(int j=0;j<=i;j++){
                    listList.set(j,listList1.get(j));
                }
            }
            return listList;
        }
    }

    public static  void  main(String[] args){
        List<Integer> list = new Pascal_Trangle2().new Solution().generate(3);
        for(Integer l1 : list){
            System.out.print( l1);
        }
    }
}
