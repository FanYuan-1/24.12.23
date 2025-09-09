package vip.persevere.huarongdao;

import java.util.Random;
import java.util.Scanner;

/*
* 数字华容道游戏
*/
public class ShuXueHrd {
    public static void main(String[] args) {
        //1.生成棋盘
        int [][] boards = initBoards();
        do {
        //2.打印棋盘
        printBoard(boards);
        //3.录入键盘移动方向
        int[] direction = readDirection();
        //4.移动棋子
            tryMove(boards, direction);
        //5.判断是否成功
            if(isSuccess(boards)){
                printBoard(boards);
                System.out.println("成功");
                break;
            }
        }while (true);
    }

    public static int[][] initBoards() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入棋盘大小：");
        // 生成棋盘
        int count = 1;
        int size = scanner.nextInt();
        int[][] boards = new int[size][size];
        for (int i = 0; i < boards.length; i++) {
            int[] row = boards[i];
            for (int j = 0; j < row.length; j++) {
                boards[i][j] = count;
                count++;
            }
        }
        boards[size - 1][size - 1] = 0;
        Random random = new Random();
        // 打乱棋盘 (模拟人移动棋子)
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(4);
            //随机生成一个移动方向
            int[] directionStr = {};
            String[] directions = {"w", "s", "a", "d"};
            String direction = directions [index];
            switch (direction){
                case "w" ->  directionStr = new int[]{1, 0};
                case "s" ->  directionStr = new int[]{-1, 0};
                case "a" ->  directionStr = new int[]{0, 1};
                case "d" ->  directionStr = new int[]{0, -1};
            }
            //移动棋子
            tryMove(boards, directionStr);
        }
        return boards;
    }

    public static boolean isSuccess(int[][] boards) {
        int count = 1;
        for (int i = 0; i < boards.length; i++) {
            int[] row = boards[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] != count && row[j] != 0){
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public static void tryMove(int[][] boards, int[] direction) {
        int[] emptyIndex = {};
        for (int i = 0; i < boards.length; i++) {
            int[] row = boards[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 0) {
                    emptyIndex = new int[]{i, j};
                }
            }
        }
        int[] targetIndex = new int[]{emptyIndex[0] + direction[0], emptyIndex[1] + direction[1]};
        //判断是否越界
        if (targetIndex[0] < 0 || targetIndex[0] >= boards.length || targetIndex[1] < 0 || targetIndex[1] >= boards[0].length) {
            //System.out.println("越界了");
            return;
        }
        boards[emptyIndex[0]][emptyIndex[1]] = boards[targetIndex[0]][targetIndex[1]];
        boards[targetIndex[0]][targetIndex[1]] = 0;
    }

    public static int[] readDirection() {
        System.out.print("请输入移动方向(w,s,a,d)：");
        int[] direction = new int[2];
        while (direction[0] == 0 && direction[1] == 0) {
        Scanner scanner = new Scanner(System.in);
        String directionStr = scanner.nextLine();
            switch (directionStr) {
                case "w" -> direction = new int[]{1, 0};
                case "s" -> direction = new int[]{-1, 0};
                case "a" -> direction = new int[]{0, 1};
                case "d" -> direction = new int[]{0, -1};
                default -> {
                    System.out.print("输入有误请输入w a s d");
                }
            }
        }

        return direction;
    }

    public static void printBoard(int [][] boards){
        for (int i = 0; i < boards.length; i++) {
            int[] row = boards[i];
            System.out.println("----------------------");
            System.out.print("| ");
            for (int j = 0; j < row.length; j++) {
                String numStr = row[j] == 0? "  " : (row[j] < 10 ? row[j] + " " : row[j] + "");
                System.out.print(numStr + " | ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
}