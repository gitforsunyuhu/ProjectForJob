package array;

public class Merge_Sorted_Array {
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int num = n+m -1;
            int i=m-1;
            int j=n-1;
            while(num>=0){
                if(i<0){
                    while(j >= 0){
                        nums1[num] = nums2[j];
                        num--;
                        j--;
                    }
                    break;
                }else if(j<0){
                    while(i >= 0){
                        nums1[num] = nums1[i];
                        num--;
                        i--;
                    }
                    break;
                }
                if(nums1[i] >= nums2[j] ){
                    nums1[num] = nums1[i];
                    i--;
                    num--;
                }else if(nums1[i] <= nums2[j]){
                    nums1[num] = nums2[j];
                    j--;
                    num--;
                }
            }
        }
    }
    public static void main(String[] args){
        int[] a = new int[] {1,2,3,0,0,0};
        int[] b = new int[] {2,4,5};
        new Merge_Sorted_Array().new Solution().merge(a,3,b,3);
        for(int i=0;i < a.length + b.length ; i++){
            System.out.println(a[i]);
        }
    }
}
