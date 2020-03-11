<center><h1>LeetCode 解题记录</h1></center>
----



[TOC]

### 1. Two Sum【Easy】

Given an array of integers, return **indices** of the two numbers such that they add up to a specific target.

You may assume that each input would have ***exactly*** one solution, and you may not use the *same* element twice.

**Example:**

```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

**My answer:**

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int[] ans =new int[2];
        for(int i=0 ;i < nums.length ; i++){
            int another = target - nums[i];
            if(map.containsKey(another)){
               ans[0]= i;
               ans[1]= map.get(another);
                return ans;
            }
            map.put(nums[i],i);
        }   
        return null;
    }
}
```



### 2. Add Two Numbers【Medium】

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order** and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Example:**

```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

**My answer:**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        int flag = 0;
        while(p!=null && q!= null){
            p = p.next;
            q = q.next;
        }
        //保证p总是最长的那个
        if(q!= null){
            flag = 1;
            p = l2;
            q = l1;
        }else{
            p = l1;
            q = l2;
        }
        
        ListNode pre = new ListNode(0);
        pre.next = p;
        int c = 0,sum=0;
        while(q!=null ){
            sum = p.val + q.val + c;
            c = sum/10;
            p.val = sum%10;
            p = p.next;
            q = q.next;
            pre = pre.next;
        }
        while(p!= null){
            sum = p.val + c;
            c = sum/10;
            p.val = sum%10;
            p = p.next;
            pre = pre.next;
        }
        if(c != 0 ){
            ListNode listNode = new ListNode(c);
            listNode.next = null;
            pre.next = listNode;
        }
        return flag==1? l2:l1;
    }
}

/**
 * 贴上网上的答案，好简洁啊NB,但是花费了o(N)的空间
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
 }
 */
```



### [9. Palindrome Number](https://leetcode-cn.com/problems/palindrome-number/)【简单】

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

**Example 1:**

```
Input: 121
Output: true
```

**Example 2:**

```
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```

**Example 3:**

```
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

**Follow up:**

Coud you solve it without converting the integer to a string?

**My answer:**

```java
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int quo = x;
        int y = 0;
        while(quo != 0){
            int c = quo % 10;
            y = y*10 + c;
            quo /= 10;
        }
        return x == y;
    }
}
```





### [15. 3Sum](https://leetcode-cn.com/problems/3sum/)【中等】

Given an array `nums` of *n* integers, are there elements *a*, *b*, *c* in `nums` such that *a* + *b* + *c* = 0? Find all unique triplets in the array which gives the sum of zero.

**Note:**

The solution set must not contain duplicate triplets.

**Example:**

```
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

**my answer:**

```java
class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int len = nums.length;
        if(nums == null ||  len < 3){
            return ans;
        }
        Arrays.sort(nums);
        for(int i=0; i< len; i++){
            if(nums[i] >0) break;
            if( i>0 && nums[i] == nums[i-1]) continue;
            int L = i+1;
            int R = nums.length -1;
            while(L<R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i], nums[L],nums[R]));
                    while(L<R && nums[L] == nums[L+1]) L++;
                    while(L<R && nums[R] == nums[R-1]) R--;
                    L++;R--;
                }else if(sum <0)   L++;
                else if(sum > 0) R--;
            }
        }
        return ans;
    }
}

```



### [16. 3Sum Closest](https://leetcode-cn.com/problems/3sum-closest/)【中等】

Given an array `nums` of *n* integers and an integer `target`, find three integers in `nums` such that the sum is closest to `target`. Return the sum of the three integers. You may assume that each input would have exactly one solution.

**Example:**

```
Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

**my answer:**

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int temp_ans = 0;
        for(int i=0; i< nums.length; i++){
            int L = i+1;
            int R = nums.length -1;
            while(L < R){
                sum = nums[i] + nums[L] + nums[R];
                if(sum - target > 0){
                    if(sum - target < min){
                        temp_ans = sum;
                        min = sum - target;
                    }
                    R --;
                }else if(sum - target < 0){
                    if(target - sum < min){
                        temp_ans = sum;
                        min = target - sum;
                    }
                    L ++;
                }else{
                    //注意当距离为0的时候可以直接返回
                    return target;
                }
            }
        }
        return temp_ans;
    }
}
```



### [18. 4Sum](https://leetcode-cn.com/problems/4sum/)【中等】

Given an array `nums` of *n* integers and an integer `target`, are there elements *a*, *b*, *c*, and *d* in `nums` such that *a* + *b* + *c* + *d* = `target`? Find all unique quadruplets in the array which gives the sum of `target`.

**Note:**

The solution set must not contain duplicate quadruplets.

**Example:**

```
Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```

**my answer:**

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 4){
            return ans;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for(int k=0; k< len-3; k++){
            if(k > 0 && nums[k] == nums[k-1]) continue;
            if(nums[k] + nums[k+1] + nums[k+2] + nums[k+3] > target) break;
            if(nums[k] + nums[len-1] + nums[len-2] + nums[len-3] < target) continue;
            for(int i= k +1; i< len -2; i++ ){
                if(i > k +1 && nums[i] == nums[i-1]) continue;
                int L = i+1;
                int R = len -1;
                while(L < R){
                    int sum = nums[k] + nums[i] + nums[L] + nums[R];
                    if(sum == target){
                        ans.add(Arrays.asList(nums[k], nums[i], nums[L], nums[R]));
                        L ++;
                        R --;
                        while(L < R && nums[L] == nums[L-1]) L++;
                        while(L < R && nums[R] == nums[R+1]) R--;
                    }else if(sum < target){
                        L++;
                    }else{
                        R--;
                    }
                }
            }
        }
        return ans;
    }
}
```





### *21. Merge Two Sorted Lists【Easy17】*

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

**Example:**

```
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

**My answer:**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null){
            return l1== null?l2:l1;
        }
        ListNode head = new ListNode(0);
        ListNode p= head;
        while(l1!=null && l2!= null){
            if(l1.val <= l2.val ){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            //这里可以统一加，缩短代码长度
             p = p.next;
        }
        if(l1 != null){
            //这里只需要一次就可以了
            p.next = l1;
        }
        if(l2 != null){
            p.next = l2;
        }
        return head.next;
    }
}
```



### [35. Search insert position](https://leetcode-cn.com/problems/search-insert-position/)【简单】

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

**示例 1:**

```
输入: [1,3,5,6], 5
输出: 2
```

**示例 2:**

```
输入: [1,3,5,6], 2
输出: 1
```

**示例 3:**

```
输入: [1,3,5,6], 7
输出: 4
```

**示例 4:**

```
输入: [1,3,5,6], 0
输出: 0
```

**My answer:**

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length -1;
        int mid = 0;
        while(left <= right){
            mid = left + (right -left)/2;
            if(nums[mid] < target) left = mid +1;
            else if(nums[mid] > target) right = mid -1;
            else return mid;
        }
        //注意插入的是当前的位置，一开始我错以为是 mid -1 这个位置
        //这就像你超过了第二名，你还是第二名一个道理
        return nums[mid] > target ? mid : mid + 1;
    }
}
```





### 46. Permutations【Medium】

Given a collection of **distinct** integers, return all possible permutations.

**Example:**

```
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
```

**My answer:**

```java
import java.util.*;
public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        if(num.length <= 0){
            return null;
        }
        ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        per(alist, num, list);
        return alist;
    }
    public void per(ArrayList<ArrayList<Integer>> alist , int[] nums, ArrayList list{
        //如果是最后一位了，就返回了
        if(list.size() == nums.length){
            alist.add(new ArrayList(list));
            return;
        }
        for(int j=0; j< nums.length; j++){
            if(!list.contains(nums[j])){
                list.add(nums[j]);
                per(alist,nums,list);
                //这句相当关键啊
                list.remove(list.size()-1);
            }
        }
    }
}
```





### 62. Unique Paths【Medium】

A robot is located at the top-left corner of a *m* x *n* grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

![img](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)
Above is a 7 x 3 grid. How many possible unique paths are there?

**Note:** *m* and *n* will be at most 100.

**Example 1:**

```
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
```

**Example 2:**

```
Input: m = 7, n = 3
Output: 28
```

**My answer:**

```java
public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++){
            dp[i][0] = 1;
        }
        for(int i=0; i<n; i++){
            dp[0][i] = 1;
        }
        for(int i= 1; i< m; i++){
            for(int j=1; j< n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}

//如果用递归也可以但是递归的话复杂度太高了
	public int uniquePaths(int m, int n) {
		if(m == 1|| n==1){
            return 1;
        }
        return uniquePaths(m-1,n) + uniquePaths(m,n-1);
    }
```





### 70. Climbing Stairs【Easy】

You are climbing a stair case. It takes *n* steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

**Note:** Given *n* will be a positive integer.

**Example 1:**

```
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```

**Example 2:**

```
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

**My answer:**

```java
class Solution {
    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i=2; i< n; i++){
            dp[i]= dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }
}
/*
 *这个题目其实就是斐波那契数列的题目，只不过样子变了，使用动态规划的话也是可以的，使用递归也是可以的
 */
```



### [74. Search a 2D Matrix](https://leetcode-cn.com/problems/search-a-2d-matrix/)【中等】

Write an efficient algorithm that searches for a value in an *m* x *n* matrix. This matrix has the following properties:

- Integers in each row are sorted from left to right.
- The first integer of each row is greater than the last integer of the previous row.

**Example 1:**

```
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
```

**Example 2:**

```
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
```

**My answer:**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        //一种方法是从左下角到右上角
        /*
        int x = matrix.length -1;
        int y = 0;

        while(x >= 0 && y <= matrix[0].length-1){
            if(matrix[x][y] > target) x--;
            else if(matrix[x][y] < target) y++;
            else return true;
        }
        return false;
        */
        
        //一种方法是二分查找
        int left = 0 ;
        int right = (matrix.length)* matrix[0].length -1;
        int mid = left + (right - left)/2;
        //这里表示区间内没有元素了
        while(left <= right){
            mid = left + (right - left)/2;
            if(matrix[mid / matrix[0].length][mid % matrix[0].length] < target) left = mid +1;
            else if(matrix[mid / matrix[0].length][mid % matrix[0].length] > target) right = mid -1;
            else return true;
        }
        return false;
        
        
        //剩下的就是我的垃圾算法
        /*
        for(int i=0; i< matrix.length; i++){
           if(matrix[i][0]<= target && target <= matrix[i][matrix[0].length-1] ){
               for(int j =0; j< matrix[0].length; j++){
                   if(matrix[i][j] == target){
                       return true;
                   }
               }
               return false;
           }
        }
        return false;
        */
    }
}
```





### ***[84. Largest Rectangle in Histogram](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/)【困难】***

Given *n* non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 

![img](https://assets.leetcode.com/uploads/2018/10/12/histogram.png)
Above is a histogram where width of each bar is 1, given height = `[2,1,5,6,2,3]`.

 

![img](https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png)
The largest rectangle is shown in the shaded area, which has area = `10` unit.

 

**Example:**

```
Input: [2,1,5,6,2,3]
Output: 10
```

**my answer:**

```java
class Solution {
	//每次找到最矮的那个，然后通过这个将问题分解为求解左右两边最大面积的子问题
    //第五行中判断语句包含等号，是因为在计算面积的时候即使是两个相等，后面有+1也是可以的
    public int fenzhifa(int[] heights, int start , int end){
        if(start > end){
            return 0;
        }
        int minindex = start;
        for(int i=start; i<= end; i++){
            if(heights[minindex] > heights[i]){
                minindex = i;
            }
        }
        return Math.max(heights[minindex] * (end - start + 1), Math.max(fenzhifa(heights,start, minindex-1), fenzhifa(heights, minindex +1 ,end)));
    }

    public int largestRectangleArea(int[] heights) {
        return fenzhifa(heights, 0, heights.length-1);
    }
}
```



### *[85. Maximal Rectangle](https://leetcode-cn.com/problems/maximal-rectangle/)【困难】*

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

**Example:**

```
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
```

**my answer:**

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
         if(matrix == null || matrix.length == 0|| matrix[0].length ==0){
            return 0;
        }
        int maxarea = 0;
        int[][] ma = new int[matrix.length][matrix[0].length];
        for(int i=0;i< matrix.length; i++){
            for(int j=0; j< matrix[i].length; j++){
                ma[i][j] = matrix[i][j]-'0';
            }
        }
        //首先对于每一行构造一个直方图
        for(int i = 0; i< ma.length; i++){
            if(i == 0) {
                maxarea = Math.max(maxarea, getarea(ma[0]));
                continue;
            }
            for(int j=0; j< ma[i].length; j++){
                if(ma[i][j] == 1){
                    ma[i][j] = ma[i-1][j] + ma[i][j];
                }
            }
            //每一行都调用直方图最大面积的函数，然后取最大值
            maxarea = Math.max(maxarea, getarea(ma[i]));
        }
        return maxarea;
    }

    public int getarea(int[] matrix){
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int start =0;
        int end = matrix.length -1;
        return func(matrix, start, end);
    }

    public int func(int[] matrix, int start, int end){
        if(start > end){
            return 0;
        }
        int minh = start;
        for(int i=start; i<= end; i++){
            if(matrix[i] < matrix[minh]){
                minh = i;
            }
        }
        return Math.max(matrix[minh]* (end -start +1), Math.max(func(matrix, start, minh-1),func(matrix,minh + 1,end)));
    }
}
```





### 92. Reverse Linked List II【Medium9】

Reverse a linked list from position *m* to *n*. Do it in one-pass.

**Note:** 1 ≤ *m* ≤ *n* ≤ length of list.

**Example:**

```
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
```

**My answer:**

```java
/**
 *不得不说这个代码写的是真的丑
 *主要是要考虑到 m == n和当m == 1时这样的情况，除此之外和206题还是很相似的
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || head.next == null || m == n){
            return head;
        }
        ListNode h = head;
         //得到尾巴的位置
        ListNode pre = head;
        for(int i=0;i<n;i++){
            pre = pre.next;
        }
        if(m ==1 ){
            n = n-m +1;
            ListNode q= head;
            while(n != 0){
                ListNode temp = q.next;
                q.next = pre;
                pre = q;
                q = temp;
                n--;
            }
            return pre;
        }
        
        //遍历到第m-1的位置
        for(int i=0;i<m-2;i++){
            head = head.next;
        }
        m = n-m+1;
        ListNode q = head.next,temp;
        while(m!=0){
            head.next = q;
            temp = q.next;
            q.next = pre;
            q = temp;
            pre = head.next;
            m--;
        }
        return h;
    }
}
```





### 104. Maximum Depth of Binary Tree【Easy】

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

return its depth = 3.

**My answer:**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return getMaxDepth(root);
    }
    
    public int getMaxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1+ Math.max(getMaxDepth(root.left),getMaxDepth(root.right));   
    }
}
```



### *111. Minimum Depth of Binary Tree【Easy】*

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

return its minimum depth = 2.

**My answer:**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 *这个题目的思路是：我们得找到二叉树的根节点到叶子节点的最短距离。
 *就是根节点的子节点到叶子节点的距离＋1
 *如果两个子节点都不为空，那么就找两个中较小的那个；如果左子树不为空，那么就需要向下找到对应的叶子节点
 *如果右子树不为空，那么就需要从右子树中找到对应的叶子节点
 * PS:如果只有一个节点，那么最小的深度 为 1
 */
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else if(root.left !=null && root.right !=null){
            return Math.min(minDepth(root.left),minDepth(root.right)) +1;
        }else if(root.left !=null){
            return 1+ minDepth(root.left);
        }else{
            return 1+ minDepth(root.right);
        }
    }
}
```



### 118. Pascal's Triangle【Easy14】

Given a non-negative integer *numRows*, generate the first *numRows* of Pascal's triangle.

![img](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)
In Pascal's triangle, each number is the sum of the two numbers directly above it.

**Example:**

```
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

**My answer:**

```java
import java.util.*;
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        if(numRows < 0){
            return null;
        }
        ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i< numRows; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(1);
            //这里需要注意的是： 第二层循环中，j是可以和i相等的，里层循环和外层循环的次数才是一样的
            for(int j=1; j<= i; j++){
                if(j == i) {
                    temp.add(1);
                    continue;
                }
                temp.add(alist.get(i-1).get(j-1) + alist.get(i-1).get(j));
            }
            alist.add(temp);
        }
        return alist;
    }
}
```



### [121. Best Time to Buy and Sell Stock](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)【简单】

Say you have an array for which the *i*th element is the price of a given stock on day *i*.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

**Example 1:**

```
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
```

**Example 2:**

```
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```

**my answer:**

```java
class Solution {
    //记录历史最低价，然后用当前价格减去历史最低价即可
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <2){
            return 0;
        }
        int len = prices.length;
        int[] historyPrice = new int[len];
        int min = prices[0];
        int max = prices[0]-min;
        historyPrice[0] = prices[0];
        for(int i=1;i< prices.length; i++){
            if(prices[i] < min){
                min = prices[i];
            }
            if(max < prices[i] - min){
                max = prices[i] - min;
            }
        }
        return max;
    }
}
```



### 136. Single Number【Easy6】

Given a **non-empty** array of integers, every element appears *twice* except for one. Find that single one.

**Note:**

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

**Example 1:**

```
Input: [2,2,1]
Output: 1
```

**Example 2:** 	

```
Input: [4,1,2,1,2]
Output: 4
```

**My answer:**

```java
class Solution {
    public int singleNumber(int[] nums) {
        for(int i=1;i<nums.length; i++){
            nums[0] = nums[0]^nums[i];
        }
        return nums[0];
   }
}
```





### 141. Linked List Cycle【Easy10】

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

 

**Follow up:**

Can you solve it using *O(1)* (i.e. constant) memory?

**My answer:**

```java
/**
 * Definition for singly-linked list.
 * 这个算法有叫做     'Floyd's Tortoise and Hare' ： 弗洛伊德的乌龟和野兔
 * 一开始要注意到 fast.next == null 的问题
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        
        while(slow!= null && fast !=null){
            slow = slow.next;
            if(fast.next == null){
                fast = fast.next;
            }else{
                fast = fast.next.next;
            }
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}

/**   另外一种解法是通过hashTable来实现的
```



### [153. Find Minimum in Rotated Sorted Array](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)【中等】

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  `[0,1,2,4,5,6,7]` might become  `[4,5,6,7,0,1,2]`).

Find the minimum element.

You may assume no duplicate exists in the array.

**Example 1:**

```
Input: [3,4,5,1,2] 
Output: 1
```

**Example 2:**

```
Input: [4,5,6,7,0,1,2]
Output: 0
```

**my answer:**

```java

```

### [154. Find Minimum in Rotated Sorted Array II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)【困难】

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 `[0,1,2,4,5,6,7]` 可能变为 `[4,5,6,7,0,1,2]` )。

请找出其中最小的元素。

注意数组中可能存在重复的元素。

**示例 1：**

```
输入: [1,3,5]
输出: 1
```

**示例 2：**

```
输入: [2,2,2,0,1]
输出: 0
```

**说明：**

- 这道题是 [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/) 的延伸题目。
- 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？

**答案：**

```java
class Solution {
    public int findMin(int[] nums) {      
        if(nums == null) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.min(nums[0], nums[1]);
        
        int L= 0;
        int R = nums.length-1;
        while(L < R -1){
            //这句话比较重要
            if(nums[L] < nums[R]){
                return nums[L];
            }
            int mid = L +(R -L)/2;
            if(nums[L] < nums[mid]){
                L= mid;
            }else if(nums[L] > nums[mid]){
                R = mid;
            }else{
                //和153题的差别就在这里，这一步去重
                L ++;
            }
        }
        return Math.min(nums[R],nums[L]);
    }
}
```





### 203. Remove Linked List Elements【Easy】

Remove all elements from a linked list of integers that have value  ***val***

**Example:**

```
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
```

**My answer:**

```java
/**
 * 这个方法中要考虑的情况是：得记录当前节点的前一个节点，这样才可以处理最后一个节点的情况
 * 本人使用的方法是再新建一个节点指向head节点，这样pre所指向的节点总是在p所指向的节点的后面
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }
        ListNode p = head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        while(p.next != null){
            if(p.val == val){
                p.val = p.next.val;
                p.next =  p.next.next;
            }else{
                pre =pre.next;
                p = p.next;
            }
        }
        if(p.val == val){
            //如果要删除的节点就是头节点的话
            if(pre.next == head){
                head = p.next;
            }else{
                pre.next = p.next;
            }
        }
        return head;
    }
}
```



### 206. Reverse Linked List【Easy8】

Reverse a singly linked list.

**Example:**

```
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
```

**Follow up:**

A linked list can be reversed either iteratively or recursively. Could you implement both?

**My answer：**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null ||head.next == null){
            return head;
        }
        ListNode p,q,temp,pre;
        p = head;
        pre = p;
        q = head.next;
        while(q!=null){
            pre.next = q.next;
            q.next = p;
            p = q;
            q = pre.next;
        }
        return p;
    }
}
/**
这个解答也是相当好的呀！
 public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
}
*/
```



### 237. Delete Node in a Linked List【Easy11】

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:

![img](https://assets.leetcode.com/uploads/2018/12/28/237_example.png)

**Example 1:**

```
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
```

**Example 2:**

```
Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
```

**Note:**

- The linked list will have at least two elements.
- All of the nodes' values will be unique.
- The given node will not be the tail and it will always be a valid node of the linked list.
- Do not return anything from your function.

**My answer:**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode ln;
        while(node.next.next!= null){
            ln = node.next;
            node.val = ln.val;
            node = node.next;
        }
        ln = node.next;
        node.val = ln.val;
        node.nxt = null;
    }
}
```



### 344. Reverse String 【Easy】

Write a function that reverses a string. The input string is given as an array of characters `char[]`.

Do not allocate extra space for another array, you must do this by **modifying the input array [in-place](https://en.wikipedia.org/wiki/In-place_algorithm)** with O(1) extra memory.

You may assume all the characters consist of [printable ascii characters](https://en.wikipedia.org/wiki/ASCII#Printable_characters).

 

**Example 1:**

```
Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

**Example 2:**

```
Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

**My answer:**

```java
class Solution {
    public void reverseString(char[] s) {
        int length = s.length;
        for(int i=0;i<length/2; i++){
            if(s[i] != s[length-1-i]){
                char ch = s[i];
                s[i] = s[length-1-i];
                s[length-1-i] = ch;
            }
        }
    }
}
```



### 412. Fizz Buzz【Easy】

Write a program that outputs the string representation of numbers from 1 to *n*.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

**Example:**

```
n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
```

**My answer:**

```java
/*
* 还有一种解法是通过HashTable来实现的，但是时间和空间复杂度都一样
*/
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<String>();
        for(int i=1;i<=n;i++){
            if(i%3 == 0&& i%5 !=0){
                list.add("Fizz");
            }else if(i%5 == 0&& i%3 !=0){
                list.add("Buzz");
            }else if(i%5 == 0 && i%3 ==0){
                list.add("FizzBuzz");
            }else{
                list.add(i+"");
            }
        }
        return list;
    }
}
```



### *[543. Diameter of Binary Tree](https://leetcode-cn.com/problems/diameter-of-binary-tree/)【简单】*

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the **longest** path between any two nodes in a tree. This path may or may not pass through the root.

**Example:**
Given a binary tree

```
          1
         / \
        2   3
       / \     
      4   5    
```



Return **3**, which is the length of the path [4,2,1,3] or [5,2,1,3].

**Note:** The length of path between two nodes is represented by the number of edges between them.

**my answer:**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null || root.left == null && root.right == null){
            return 0;
        }
        int ans = 0;
        if(root.left != null){
            ans += 1;
        }
        if(root.right !=null){
            ans += 1;
        }
        int temp = ans + maxLen(root.left) + maxLen(root.right);
        int temp1 = diameterOfBinaryTree(root.left);
        int temp2 = diameterOfBinaryTree(root.right);
        return Math.max(temp1,Math.max(temp,temp2));
    }
    //用到了之前最长深度的函数，但是这个笑脸非常低
    public int maxLen(TreeNode root){
        if(root == null || root.left == null && root.right == null){
            return 0;
        }
        return Math.max(maxLen(root.left), maxLen(root.right)) + 1;
    }
}
```





### 1108. Defanging an IP Address【Easy2】

Given a valid (IPv4) IP `address`, return a defanged version of that IP address.

A *defanged IP address* replaces every period `"."` with `"[.]"`.

**Example 1:**

```
Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
```

**Example 2:**

```
Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"
```

 

**Constraints:**

- The given `address` is a valid IPv4 address.

**answer:**

```java
class Solution {
    public String defangIPaddr(String address) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i< address.length(); i++){
            if(address.charAt(i) == '.'){
                sb.append("[.]");
            }else{
                sb.append(address.charAt(i));
            }
        }
        return sb.toString();
    }
}
```





### 1342. Number of Steps to Reduce a Number to Zero 【Easy1】

Given a non-negative integer `num`, return the number of steps to reduce it to zero. If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.

**Example 1:**

```
Input: num = 14
Output: 6
Explanation: 
Step 1) 14 is even; divide by 2 and obtain 7. 
Step 2) 7 is odd; subtract 1 and obtain 6.
Step 3) 6 is even; divide by 2 and obtain 3. 
Step 4) 3 is odd; subtract 1 and obtain 2. 
Step 5) 2 is even; divide by 2 and obtain 1. 
Step 6) 1 is odd; subtract 1 and obtain 0.
```

**Example 2:**

```
Input: num = 8
Output: 4
Explanation: 
Step 1) 8 is even; divide by 2 and obtain 4. 
Step 2) 4 is even; divide by 2 and obtain 2. 
Step 3) 2 is even; divide by 2 and obtain 1. 
Step 4) 1 is odd; subtract 1 and obtain 0.
```

**Example 3:**

```
Input: num = 123
Output: 12
```

**Constraints:**

- `0 <= num <= 10^6`

**Answer:**

```jAVA
class Solution {
    public int numberOfSteps (int num) {
        int ans = 0;
        if(num == 0){
            return ans;
        }else{
            while(num !=0){
                if(num %2 ==0){
                    num = num/2;
                    ans ++;
                }else{
                    num = num-1;
                    ans ++;
                }
            }   
        }
        return ans;
    }
}
```





### ==从此步入中文时代==



### 面试题09. 用两个栈实现队列【简单】

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

**示例 1：**

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

**示例 2：**

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

**提示：**

- `1 <= values <= 10000`
- `最多会对 appendTail、deleteHead 进行 10000 次调用`

```java
class CQueue {

    Stack<Integer> stack1= null;
    Stack<Integer> stack2= null;

    public CQueue() {
          stack1 = new Stack<Integer>();
          stack2 = new Stack<Integer>();
    }
    
    public void appendTail(int value) {
        stack1.push(value);
    }
    
    public int deleteHead() {
         while(!stack1.empty()){
            stack2.push(stack1.pop());
        }
        int ans;
        if(!stack2.empty()){
            ans = stack2.pop();
        }else{
            ans= -1;
        }
        while(!stack2.empty()){
            stack1.push(stack2.pop());
        }
        return ans;
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```



### 面试题10- I. 斐波那契数列【简单】

写一个函数，输入 `n` ，求斐波那契（Fibonacci）数列的第 `n` 项。斐波那契数列的定义如下：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入：n = 2
输出：1
```

**示例 2：**

```
输入：n = 5
输出：5
```

**提示：**

- `0 <= n <= 100`

**我的答案**

```java
class Solution {
    public int fib(int n) {
        if(n == 0 || n == 1){
            return n;
        }
        int f1 = 0,f2 = 1;
        for(int i=0; i<n-1 ; i++){
            int f3 = (f1 + f2)%1000000007;
            f1 = f2;
            f2 = f3;
        }
        return f2;
    }
}
/**
 *执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
 *内存消耗 : 35.8MB, 在所有 Java 提交中击败了100.00%的用户
 */
```





### 面试题10- II. 青蛙跳台阶问题【简单】

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

输入：n = 2
输出：2
示例 2：

输入：n = 7
输出：21
提示：

0 <= n <= 100

难度简单9收藏分享切换为英文关注反馈

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 `n` 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入：n = 2
输出：2
```

**示例 2：**

```
输入：n = 7
输出：21
```

**提示：**

- `0 <= n <= 100`

**我的答案**

```java
class Solution {
    public int numWays(int n) {
        if(n == 0){
            return 1;
        }
         if(n == 1|| n ==2){
            return n;
        }
        int f1 = 1; 
        int f2 = 2;
        for(int i=0; i< n-2; i++){
            int f3 = (f1 + f2)%1000000007;
            f1 = f2;
            f2 = f3;
        }
        return f2;
    }
}
```





**我的答案**

```java
class Solution {
    public int minArray(int[] numbers) {
        //处理不符合的情况
        if(numbers.length <= 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i< numbers.length-1; i++){
            if(numbers[i] > numbers[i+1]){
                return numbers[i+1];
            }else{
                if(min > numbers[i]){
                    min = numbers[i];
                }
            }
        }
        if(min>numbers[numbers.length-1]){
            min = numbers[numbers.length-1];
        }
        return min;
    }
}
/**
执行用时 :1 ms, 在所有 Java 提交中击败了50.89%的用户
内存消耗 :39.3 MB, 在所有 Java 提交中击败了100.00%的用户
*/
```





### 面试题11. 旋转数组的最小数面试题【简单】

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

示例 1：

输入：[3,4,5,1,2]
输出：1
示例 2：

输入：[2,2,2,0,1]
输出：0
注意：本题与主站[154](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)相同

**示例 1：**

```
输入：[3,4,5,1,2]
输出：1
```

**示例 2：**

```
输入：[2,2,2,0,1]
输出：0
```

**my answer:**

```java
class Solution {
    public int minArray(int[] numbers) {
        //处理不符合的情况
        if(numbers.length <= 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i< numbers.length-1; i++){
            if(numbers[i] > numbers[i+1]){
                return numbers[i+1];
            }else{
                if(min > numbers[i]){
                    min = numbers[i];
                }
            }
        }
        if(min>numbers[numbers.length-1]){
            min = numbers[numbers.length-1];
        }
        return min;
    }
}
```



### [面试题15. 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)【简单】

请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

**示例 1：**

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

**示例 2：**

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

**示例 3：**

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

**我的答案：**

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {  
        //这题在题解中有很巧妙的解法
       	/*
       	按位判断
        这是最直观的方式，依次判断整数的每一位是否为 1 。

        判断第 1 位：n & 1

        判断第 2 位：n & (1 << 1)

        ......

        判断第32位：n & (1 << 31)

        代码
        class Solution {
        public:
            int hammingWeight(uint32_t n) {
                int ret = 0;
                for (int i = 0; i < 32; i ++) {
                    if (n & (1 << i)) {
                        ret ++;
                    }
                }
                return ret;
            }
        };
        N & (N - 1)​
        使用这个方法，基于这样一个事实：

        一个数 nn 与一个比它小 11 的数（n - 1n−1）进行与运算（\&&）之后，得到的结果会消除 nn 中最低位的 11.
        我们看两个例子：

        示例1: 7 & 6
        00111
              &   =》 00110
        00110

        示例2: 8 & 7
        01000
              &   =》 00000
        00111
        可以看到，n \& (n - 1)n&(n−1) 得到的结果，就是将 nn 最低位 11，换成 00 之后的值。

        根据这个，编码思路就是，每次都使用该运算法则消去 11 ，每运算一次计数器 + 1+1，直至 nn 为 00 .

        代码
        class Solution {
        public:
            int hammingWeight(uint32_t n) {
                int ret = 0;
                while (n != 0) {
                    n &= n-1;
                    ret ++;
                }
                return ret;
            }
        };
        作者：huwt
        链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/er-jin-zhi-zhong-1de-ge-shu-an-wei-pan-duan-n-n-1-/
       	*/
        
        return Integer.bitCount(n);
    }
}
```





### [面试题59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)【中等】

请定义一个队列并实现函数 `max_value` 得到队列里的最大值，要求函数`max_value`、`push_back` 和 `pop_front` 的**均摊**时间复杂度都是O(1)。

若队列为空，`pop_front` 和 `max_value` 需要返回 -1

**示例 1：**

```
输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
```

**示例 2：**

```
输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
```

**限制：**

- `1 <= push_back,pop_front,max_value的总操作数 <= 10000`
- `1 <= value <= 10^5`

**我的答案：**

```java
class MaxQueue {

    Queue<Integer> queue = null;
    int maxValue = -1;
    public MaxQueue() {
        queue = new LinkedList<Integer>();
    }
    
    public int max_value() {
        if(queue.isEmpty()){
            return -1;
        }
        maxValue = -1;
        for(Integer va : queue){
            if(va > maxValue){
                maxValue = va;
            }
        }
        return maxValue;
    }
    
    public void push_back(int value) {
        queue.offer(value);
    }
    
    public int pop_front() {
        if(queue.isEmpty()){
            return -1;
        }
        return queue.poll();
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
```



### [面试题64. 求1+2+…+n](https://leetcode-cn.com/problems/qiu-12n-lcof/)【中等】

求 `1+2+...+n` ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

**示例 1：**

```
输入: n = 3
输出: 6
```

**示例 2：**

```
输入: n = 9
输出: 45
```

**限制：**

- `1 <= n <= 10000`

**我的答案：**

```java
/*
这道题2个点：
    不能用for循环
    不能用if运算
如何解决：
    for用递归实现，这很好理解
    if用逻辑运算符的计算特性来解决。即and的短路特性。
	A and function() 如果A是True， 返回的是function 如果A是false，function都不会被执行到就下一句了。因此我们把递归入口放在function处，那么A表达式就可以起到if的作用，function递归起到for的作用
为了让n能及时停止（数量够的时候，要输出false），我们只能把终点设置成0，因此我们递归中要倒数。

    return (int) (Math.pow(n, 2) + n) >> 1;
*/
class Solution {
    public int sumNums(int n) {
        int sum = n;
        boolean flag = (n > 0) && (sum += sumNums(n-1));
        return sum;
    }
}
```





