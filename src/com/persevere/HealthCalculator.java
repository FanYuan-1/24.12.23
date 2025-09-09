package com.persevere;

import java.util.Scanner;

public class HealthCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入您的体重（千克）: ");
        double weight = scanner.nextDouble();

        System.out.print("请输入您的身高（厘米）: ");
        double height = scanner.nextDouble();

        System.out.print("请输入您的年龄（岁）: ");
        int age = scanner.nextInt();

        System.out.print("请输入您的性别（男/女）: ");
        String gender = scanner.next().toLowerCase();

        System.out.println("请选择您的活动水平:");
        System.out.println("1. 不活跃");
        System.out.println("2. 轻度活动");
        System.out.println("3. 中等活动");
        System.out.println("4. 高强度活动");
        System.out.println("5. 极端高强度活动");
        int activityLevel = scanner.nextInt();

        double bmi = calculateBMI(weight, height);
        String bodyType = determineBodyType(bmi);
        double bmr = calculateBMR(weight, height, age, gender);
        double totalCalories = calculateTotalCalories(bmr, activityLevel);
        double proteinIntake = calculateProteinIntake(weight);
        double carbsIntake = calculateCarbsIntake(totalCalories);
        double fatIntake = calculateFatIntake(totalCalories);

        System.out.printf("您的BMI是: %.2f\n", bmi);
        System.out.println("体型: " + bodyType);
        System.out.printf("您的每日基础代谢率(BMR)是: %.2f 千卡\n", bmr);
        System.out.printf("您的每日总热量消耗是: %.2f 千卡\n", totalCalories);
        System.out.printf("每日推荐摄入的蛋白质是: %.2f 克\n", proteinIntake);
        System.out.printf("每日推荐摄入的碳水化合物是: %.2f 克\n", carbsIntake);
        System.out.printf("每日推荐摄入的脂肪是: %.2f 克\n", fatIntake);

        scanner.close();
    }

    private static double calculateBMI(double weight, double height) {
        return weight / ((height / 100) * (height / 100));
    }

    private static String determineBodyType(double bmi) {
        if (bmi < 18.5) {
            return "过轻";
        } else if (bmi < 24.9) {
            return "正常";
        } else if (bmi < 29.9) {
            return "超重";
        } else {
            return "肥胖";
        }
    }

    private static double calculateBMR(double weight, double height, int age, String gender) {
        if ("男".equals(gender)) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

    private static double calculateTotalCalories(double bmr, int activityLevel) {
        switch (activityLevel) {
            case 1:
                return bmr * 1.2;
            case 2:
                return bmr * 1.375;
            case 3:
                return bmr * 1.55;
            case 4:
                return bmr * 1.725;
            case 5:
                return bmr * 1.9;
            default:
                throw new IllegalArgumentException("无效的活动水平选择！");
        }
    }

    private static double calculateProteinIntake(double weight) {
        return weight * 0.8; // 成人每公斤体重摄入0.8克蛋白质
    }

    private static double calculateCarbsIntake(double totalCalories) {
        double carbPercentage = 0.55; // 假设碳水化合物占比55%
        return (totalCalories * carbPercentage) / 4; // 每克碳水化合物提供4千卡热量
    }

    private static double calculateFatIntake(double totalCalories) {
        double fatPercentage = 0.25; // 假设脂肪占比25%
        return (totalCalories * fatPercentage) / 9; // 每克脂肪提供9千卡热量
    }
}



