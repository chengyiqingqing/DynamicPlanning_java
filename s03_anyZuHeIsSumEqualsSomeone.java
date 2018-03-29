package com.wenwen.swordtooffer.sword_dynamicplanning;

/**
 * Created by Administrator on 2018/3/29.
 */

public class s03_anyZuHeIsSumEqualsSomeone {

    public static void main(String[] args){
        int[] arr=new int[]{3,34,4,12,5,2};
//        System.out.println(f(arr,arr.length-1,9));
//        System.out.println(f(arr,arr.length-1,10));
//        System.out.println(f(arr,arr.length-1,11));
//        System.out.println(f(arr,arr.length-1,12));
//        System.out.println(f(arr,arr.length-1,13));
        System.out.println(noRecursion(arr,arr.length-1,9));
        System.out.println(noRecursion(arr,arr.length-1,10));
        System.out.println(noRecursion(arr,arr.length-1,11));
        System.out.println(noRecursion(arr,arr.length-1,12));
        System.out.println(noRecursion(arr,arr.length-1,13));

    }

    /**
     * 使用递归的形式，解题思路。
     *      出口1：target==0的时候，就retrue true;
     *      出口2：当下标为0的时候，就比较当前target是否和arr[0]相等；不等那就是出口了；
     *      循环调用条件：(1)arr[arrIndex]>target，都是正整数，减了就负了，所以该数不选择。
     *                   (2)arr[arrIndex]
     * @param arr
     * @param arrIndex
     * @param target
     * @return
     */
    public static boolean f(int[] arr,int arrIndex,int target){
        if (target==0)
            return true;
        else  if (arrIndex==0){
            return target==arr[arrIndex];//这个地方应该是终止条件；
        }else if (arr[arrIndex]>target){
            return f(arr,arrIndex-1,target);
        }else{
            return f(arr,arrIndex-1,target-arr[arrIndex])
                    ||f(arr,arrIndex-1,target);
        }
    }

    /**
     * 很明显现在使用一维数组记录最优解，是完成不了了；
     * 因为没法定义“非递归下的出口条件”：opt[0],opt[1]的情况啊； ==》 这个是关键；
     * 所以这次派上二维数组；
     * @param arr
     * @param arrIndex
     * @param target
     */
    public static boolean noRecursion(int[] arr,int arrIndex,int target){
        boolean result=false;
        //1.先定义二维数组
        boolean[][] resultArr=new boolean[arr.length][target+1];
        int rows=resultArr.length;
        int columns=resultArr[0].length;//第一行有多少个元素就是多少列呗；
        //2.再定义一个一维数组target的值的数组；
        int[] targetArr=new int[target+1];
        for (int i=0;i<targetArr.length;i++){
            targetArr[i]=i;
        }
        //3.初始化二维数组的首行和首列；
        for (int i=0;i<columns;i++){//把第一行的数据初始化；
            if (arr[0]==targetArr[i]){
                resultArr[0][i]=true;
            }else {
                resultArr[0][i]=false;
            }
        }
        for (int i=0;i<rows;i++){
            resultArr[i][0]=true;
        }
        //4.相当于递归函数出口已经设置；接下来循环；
        for (int i=1;i<rows;i++){ //行数 从第二个元素开始；
            for (int curTarget=1;curTarget<columns;curTarget++){ //列数从第二个元素开始；j就是当前target
                if (arr[i]>curTarget){
                    resultArr[i][curTarget]=resultArr[i-1][curTarget];//两个boolean值的相互取；
                }else {
                    resultArr[i][curTarget]=resultArr[i-1][curTarget]
                            ||resultArr[i-1][curTarget-arr[i]];
                }
            }
        }
        return resultArr[rows-1][columns-1];
    }

}
