package vip.persevere.ai;

import java.util.Scanner;

public class DataTypesDome {
    public static void main(String[] args) {
        //录入贷款本金
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入贷款本金：");
        double loanPrincipal = scanner.nextDouble();

        //录入年利率(列如：5 表示 %5)
        System.out.print("请输入年利率(列如：5 表示 %5)：");
        double annualInterestRate = scanner.nextDouble();
        annualInterestRate /= 100;

        //录入还款期数
        System.out.print("请输入还款期数（年）：");
        int numberOfPayments = scanner.nextInt();

        //计算月利率
        double monthlyInterestRate = annualInterestRate / 12;

        //计算每月应还金额
        double monthlyPayment = loanPrincipal * monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments*12) / (Math.pow(1 + monthlyInterestRate, numberOfPayments*12) - 1));

        //输出结果
        System.out.println("每月应还金额：" + String.format("%.2f",monthlyPayment));
    }
}
/*
double weight;
        while (true) {
                System.out.print("请输入您的体重(kg):");
weight = scanner.nextDouble();
            if (weight < 20 || weight > 300) {
        System.out.println("体重输入有误，请重新输入20-300之间的值！");
                continue;
                        }
                        break;
                        }*/
