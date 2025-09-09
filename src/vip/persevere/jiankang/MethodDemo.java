package vip.persevere.jiankang;

public class MethodDemo {
    public static void main(String[] args) {
        int max = getMax(10,20);
        System.out.println("最大值是："+max);
    }
    //定义一个求两个int数之间求最大数的方法
    public static int getMax(int a, int b){
        return  a > b ? a : b;
    }
    //定义一个方法，接收一个人名，打印hello+人名
    public static void sayHello(String name){
        System.out.println("hello"+name);
    }
}