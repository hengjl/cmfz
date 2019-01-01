import org.junit.Test;

import java.util.Random;

public class Test1 {
    @Test
    public void test() {
        //定义一个冒泡排序相邻间的元素进行比较
        int[] arr = {2, 11, 33, 12, 32, 5, 77, 9};
        //外层控制排序次数
        for (int i = 0; i < arr.length; i++) {
            //内层控制比较次数
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print("-" + arr[i]);
        }
        System.out.println();
        //选择排序从起始位置开始进行比较  自己定比较规则
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    //System.out.println();
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print("-" + arr[i]);
        }

    }

    @Test
    public void testCode() {
        int x;//定义两变量
        Random ne = new Random();//实例化一个random的对象ne
        x = ne.nextInt(9999 - 1000 + 1) + 1000;//为变量赋随机值1000-9999
        System.out.println("产生的随机数是:" + x);//输出
    }

}
