package com.wenwen.swordtooffer.sword_dynamicplanning;

/**
 * Created by Administrator on 2018/3/29.
 * https://www.bilibili.com/video/av18512769 正月点灯笼；
 * 1.问题描述：
 *      一个数组中不相邻的数字和的最大值；
 *      题目分解：
 *          --》不相邻的数字；
 *          --》求和；
 *          --》求最大值；
 *
 * 2.问题归类：（通过归类，让解题走向套路）；
 *      动态规划；
 *
 * 3.解题思路：
 *      选和不选：先通过递归的方式；找出解题方式；然后，再根据递归关系，写出非递归形式；
 *
 *      递归：写出OPT(n)=OPT(pre(next)) + f(n) || OPT(n-1) ;
 *      非递归：
 *          可以利用额外一维数组OPT[]的方式，来记录每一步的最优解。
 *          OPT[0],OPT[1]  ==》 OPT[2],OPT[3],....OPT[n];
 *          写出OPT(n)=OPT(pre(next)) + f(n) || OPT(n-1) ;
 */

public class s02_noBorderSumQuestion {
    static int i;
    static String strs;
    public static void main(String[] args){
//        int[] arr=new int[]{1,2,4,1,7,8,3};
        int[] arr=new int[]{4,1,1,9,1};
        int result = noRecursion(arr,arr.length-1);
        System.out.println(result);
    }

    /**
     * 不相邻数字求和求最大值；
     * @param arr 目标数组；
     */
    public static int recursion(int[] arr,int arrIndex){
        //思路1：采用递归的形式；
        if (arrIndex==0){
            return arr[0];
        }else if (arrIndex==1){
            return Math.max(
                    arr[0],
                    arr[1]) ;
        }else {
            return Math.max(
                    recursion(arr,arrIndex-2)+arr[arrIndex],
                    recursion(arr,arrIndex-1));
        }
    }

    /**
     * 非递归的形式求解；他妈的都是套路；
     * @param arr
     * @param arrIndex
     * @return
     */
    public static int noRecursion(int[] arr,int arrIndex){
        //进行终止条件或者说是递归出口在哪里？
        int[] opt=new int[arr.length]; //跟踪原数组，用于记录最优解；
        opt[0]=arr[0];
        opt[1]=Math.max(arr[0],arr[1]);
        for (int i=2;i<arr.length;i++){
            int A=arr[i]+opt[i-2];//选它自己f(i)=arr[i]，然后加上pre(next)的最优解；
            int B=opt[i-1];//不选它自己，然后看它子数组的最优解；
            opt[i]=Math.max(A,B);
        }
        return opt[arrIndex];
    }

}
