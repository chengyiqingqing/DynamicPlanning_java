package com.wenwen.swordtooffer.sword_dynamicplanning;

/**
 * Created by sww on 2018/3/31.
 * A:动态规划的基本思想和解题思路：
 *      （1）描述：将待求问题分解为若干个子问题，按顺序求解子阶段，前一问题的解，为后一问题的求解提供了有用信息。
 *           我们依次解决子问题，最后一个子问题的解就是--初始问题的解。
 *      （2）但是它和分治法是不一样的：适合于用动态规划法求解的问题，经分解后得到的子问题往往不是相互独立的。
 *      一句话总结：前一步的解对后一步的解有影响。
 *
 * B:动态规划的求解问题适用场景所具有的三个特点：（每一个子问题都是紧紧相连的）
 *      （1）最优化原理-----------如果问题的最优解包含子问题的解也是最优的，那么我们说它具有最优子结构，
 *                               也就满足最优化原理；
 *      （2）无后效性 -----------也就是说某个阶段的状态一旦确定，就不受这个状态以后决策的影响。
 *                              不会影响以前的状态，只与当前的状态有关；
 *      （3）有重叠子问题--------
 *
 * C:动态规划求解的基本步骤：
 *      （1）划分阶段-------------按照问题的时间或空间特征进行划分若干个阶段
 *      （2）确定状态和状态变量-----------将问题所发展个各个阶段，用不同的状态表示出来；
 *      （3）确定决策并写出状态转移方程--------确定决策并写出状态转移方程；这个问题非常的重要，
 *                                              在动态规划求解问题的过程中也非常的难处理
 *      （4）寻找边界条件-------------就是为我们的状态转移方程找到一个递推式；
 *      一句话终结：动态规划求解的核心，就是找状态转移方程。
 *
 * D:本次所要解决的问题：
 *      字符串解码，路径数目&最小路径和，最大子数组乘积。
 *
 * 问题：字符串解码；
 * 题目描述：
 *      一个包含字母的消息被加密之后变成了一个只包含数字的字符串，但是我们现在知道加密的规则：
 *      ‘A’ -> 1;
 *       'B'  -> 2;
 *       ...
 *       'Z' -> 26;
 *      现在给定一个已经被加密的只包含数字的字符串，求出该字符串有多少种被解密的方法。
 *      例如：'12'->AB  或者  '12'->L (求被解密出多少种结果)；
 */

public class s04_stringDecode {

    public static void main(String[] args){
        String str="1231725";
//        String str3="12317";
        /*int n=str.length();
        int[] opt=new int[n];
        for (int i=0;i<str.length();i++){
            opt[i]=0;
        }*/
//        System.out.println(decodeNum(str,opt,str.length()-1));//decodeNumNoRecursion
        System.out.println(decodeNumNoRecursion(str));
    }

    /**
     * 这是一个典型的dp问题，假设定义一个数组，dp[i]为到第i个字符所组成的所有编码方式的个数。
     * 那么对于dp[i+1]来说，肯定至少和dp[i]一样多，如果第i和i+1个字符可以合成一个字符，那么
     * dp[i+1]=dp[i]+dp[i-1];
     *     --> 计算的是个数，那我直接定义一个数组，直接arr[i]存储这个个数。
     *     --> 接下来分析i和i-1的关系，或者i和i-1，i-2的关系。
     *     --> 有两种选择的时候：opt[i]=opt[i-1]+opt[i-2];    构不成两种选择：opt[i]=opt[i-1];
     *     -->
     * @param str
     * @return
     */
    public static int decodeNum(String str,int[] opt,int n){
        char[] charArray = str.toCharArray();
        if (n==0){
            return 1;
        }else if (n==1){
            int temp=Integer.valueOf(str.substring(n-1,n+1));
            if (temp>=10&&temp<=26){
                return 2;
            }
            return 1;
        }else{
            int temp=Integer.valueOf(str.substring(n-1,n+1));
            if (temp>=10&&temp<=26){//两者都满足；
                return decodeNum(str,opt,n-1)+decodeNum(str,opt,n-2);
            }else {
                return decodeNum(str,opt,n-1);
            }
        }
    }

    public static int decodeNumNoRecursion(String str){
        int result=0;
        char[] charArray = str.toCharArray();
        int n=charArray.length;
        int[] opt=new int[n];
        if (str.length()>=1){
            opt[0]=1;
        }
        if (str.length()>=2){
            int temp=Integer.valueOf(str.substring(0,2));
            if (temp>=10&&temp<=26) {
                opt[1] = 2;
            }else {
                opt[1]=opt[0];
            }
        }
        for (int i=2;i<n;i++){
            int temp=Integer.valueOf(str.substring(i-1,i+1));
            if (temp>=10&&temp<=26) {
                opt[i] = opt[i-1]+opt[i-2];
            }else {
                opt[i]=opt[i-1];
            }
        }
        return opt[n-1];
    }


}
