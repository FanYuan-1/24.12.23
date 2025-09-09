package vip.persevere.jiankang;

import java.util.Scanner;

public class Jhua {
    public static void main(String[] args) {
        //1.录入用户信息
        short height = (short) readValue("身高(cm)", 130, 250);
        double weight = readValue("体重(kg", 20, 300);
        byte age = (byte) readValue("年龄", 18, 70);
        byte sex = readSex();
        byte intensity = (byte) readValue("运动强度(0.久坐/1.轻度/2.中度/3.高度):", 0, 3);
        //2.计算BMI，根据BMI判断体型，设定运动目标
        double bmi = calculateBmi(height, weight);
        String bodyType = calculateString(bmi);
        String goal = setGoal(bodyType);
        //3.计算每日摄入热量建议，包括BMR、TDEE、每日热量
        double bmr = calculateBmr(sex, weight, height, age);
        double tdee = calculateTdee(intensity, bmr);
        double dailyCalorie = calculateDailyCalorie(goal, tdee);
        //4.计算每日摄入营养素的量
        double protein = calculateProtein(dailyCalorie);
        double fat = calculateFat(dailyCalorie);
        double carbohydrate = calculateCarb(dailyCalorie);
        //5.输出结果
        printResult(bmi,bodyType,goal,dailyCalorie,protein,bmr,tdee,fat,carbohydrate);
    }

    public static byte readSex() {
        Scanner scanner = new Scanner(System.in);
        byte sex;
        while (true) {
        System.out.print("请输入您的性别(0.男/1.女):");
        sex = scanner.nextByte();
        if (sex != 0 && sex != 1) {
            System.out.println("性别输入有误，请重新输入0或1！");
            continue;
        }
        break;
    }
        return sex;
    }

    public static double readValue(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print("请输入您的" + prompt + "：");
            value = scanner.nextDouble();
            if (value < min || value > max){
                System.out.println("输入有误，请输入" + min + "-" + max + "之间的值！");
                continue;
            }
            break;
         }
        return value;
    }

    public static double calculateBmi(short height, double weight) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

    public static String calculateString(double bmi) {
        String bodyType ;
        if (bmi < 18.5){
            bodyType = "偏瘦";
        }
        else if (bmi >= 18.5 && bmi < 24){
            bodyType = "正常";
        }
        else if (bmi >= 24 && bmi < 28){
            bodyType = "超重";
        }
        else {
            bodyType = "肥胖";
        }
        return bodyType;
    }

    public static String setGoal(String bodyType) {
        return switch (bodyType) {
            case "偏瘦" -> "增肌";
            case "正常" -> "保持";
            default -> "减脂";
        };
    }

    public static double calculateBmr(byte sex, double weight, short height, byte age) {
        return sex == 0 ?
                88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age) :
                447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
    }

    public static double calculateTdee(byte intensity, double bmr) {
        double activityFactor = switch (intensity){
             case 0 -> 1.2;
             case 1 -> 1.375;
             case 2 -> 1.55;
             default -> 1.725;
         };
        return  bmr * activityFactor;
    }

    public static double calculateDailyCalorie(String goal, double tdee) {
        return switch (goal) {
            case "减脂" -> tdee - 300;
            case "增肌" -> tdee + 300;
            default -> tdee;
        };
    }

    public static double calculateProtein(double dailyCalorie) {
        final double PROTEIN_PERCENT = 0.3;
        final int PROTEIN_KCAL_PER_GRAM = 4;
        return dailyCalorie * PROTEIN_PERCENT / PROTEIN_KCAL_PER_GRAM;
    }

    public static double calculateFat(double dailyCalorie) {
        final double FAT_PERCENT = 0.2;
        final int FAT_KCAL_PER_GRAM = 9;
        return dailyCalorie * FAT_PERCENT / FAT_KCAL_PER_GRAM;
    }

    public static double calculateCarb(double dailyCalorie) {
        final double CARB_PERCENT = 0.5;
        final int CARB_KCAL_PER_GRAM = 4;
        return dailyCalorie * CARB_PERCENT / CARB_KCAL_PER_GRAM;
    }

    public static void printResult(double bmi, String bodyType, String goal,
                     double dailyCalorie, double protein,double bmr,
                     double tdee, double fat, double carbohydrate){
          System.out.println("您的BMI是: " + String.format("%.1f",bmi) + ",体型是: " + bodyType );
          System.out.println("建议您持续" + goal );
          System.out.println("------------饮食计划------------");
          System.out.println("您的每日基础代谢为：" + (int) bmr + "千卡，结合运动后的总代谢：" + (int) tdee + "千卡" );
          System.out.println("结合运动目标.建议你每日摄入："+ (int) dailyCalorie + "千卡");
          System.out.println("蛋白质：" + (int) protein + "克");
          System.out.println("脂肪：" + (int)fat + "克");
          System.out.println("碳水化合物：" + (int) carbohydrate + "克");
          System.out.println("--------------------------------");
    }
}