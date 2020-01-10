package array;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.*;

public class Pascal_Trangle {
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> listList = new LinkedList<List<Integer>>();
            for(int i=0 ; i < numRows; i++){
                List<Integer> list = new LinkedList<Integer>();
                for(int j=0; j<= i; j++){
                    if(j == 0 || j == i) {
                        list.add(1);
                    }else{
                        list.add(listList.get(i-1).get(j-1) + listList.get(i-1).get(j));
                    }
                }
                listList.add(list);
            }
            return listList;
        }
    }

    public static  void  main(String[] args){
        List<List<Integer>> list = new Pascal_Trangle().new Solution().generate(4);
        for(List<Integer> l1 : list){
            for(Integer a:l1){
                System.out.print(a);
            }
            System.out.println();
        }
    }
}
