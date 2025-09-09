package vip.persevere.ai;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ImageToAscii {

    private static final String ASCII_CHARS = "@%#*+=-:. ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入图片路径
        System.out.print("请输入图片路径: ");
        String inputImagePath = scanner.nextLine();

        // 提示用户输入输出文件路径
        System.out.print("请输入输出文件路径: ");
        String outputFilePath = scanner.nextLine();

        // 提示用户输入缩放比例
        System.out.print("请输入缩放比例（例如 0.5 表示缩小一半，2 表示放大两倍）: ");
        double scale = scanner.nextDouble();

        // 检查文件是否存在
        File imageFile = new File(inputImagePath);
        if (!imageFile.exists()) {
            System.out.println("文件不存在: " + inputImagePath);
            return;
        }

        // 检查文件是否可读
        if (!imageFile.canRead()) {
            System.out.println("文件不可读: " + inputImagePath);
            return;
        }

        // 尝试读取图像并转换为ASCII艺术，然后保存到文件中
        try {
            // 读取输入图像
            BufferedImage originalImage = ImageIO.read(imageFile);
            if (originalImage == null) {
                System.out.println("无法读取图片文件。");
                return;
            }

            // 调整图像大小
            BufferedImage scaledImage = scaleImage(originalImage, scale);

            // 将图像转换为ASCII艺术
            String asciiArt = convertImageToAscii(scaledImage);
            // 将ASCII艺术保存到文件中
            saveAsciiToFile(asciiArt, outputFilePath);
            // 打印保存成功信息
            System.out.println("ASCII艺术已保存到 " + outputFilePath);
        } catch (IOException e) {
            // 捕获IO异常并打印堆栈跟踪信息
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static BufferedImage scaleImage(BufferedImage originalImage, double scale) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int scaledWidth = (int) (originalWidth * scale);
        int scaledHeight = (int) (originalHeight * scale);

        BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    private static String convertImageToAscii(BufferedImage image) {
        // 创建StringBuilder对象，用于存储最终的ASCII艺术字符串
        StringBuilder asciiArt = new StringBuilder();
        // 获取图像的宽度和高度
        int width = image.getWidth();
        int height = image.getHeight();

        // 遍历图像的每一行
        for (int y = 0; y < height; y++) {
            // 遍历图像的每一列
            for (int x = 0; x < width; x++) {
                // 获取当前像素的RGB值
                int pixel = image.getRGB(x, y);
                // 将RGB值转换为灰度值
                int gray = (int) (0.299 * ((pixel >> 16) & 0xff) + 0.587 * ((pixel >> 8) & 0xff) + 0.114 * (pixel & 0xff));
                // 根据灰度值计算对应的ASCII字符索引
                int index = gray * (ASCII_CHARS.length() - 1) / 255;
                // 将对应的ASCII字符添加到结果字符串中
                asciiArt.append(ASCII_CHARS.charAt(index));
            }
            // 每一行的末尾添加换行符
            asciiArt.append("\n");
        }

        // 返回最终的ASCII艺术字符串
        return asciiArt.toString();
    }

    /**
     * 将ASCII艺术保存到指定的文件路径
     * 此方法负责将给定的ASCII艺术字符串写入到指定路径的文件中它使用FileWriter来写入文件，
     * 并自动管理资源的关闭，以确保在操作完成后文件被正确释放
     *
     * @param asciiArt ASCII艺术的字符串表示，需要保存的内容
     * @param filePath 文件路径，指示将ASCII艺术保存到哪里
     * @throws IOException 如果写入文件时发生I/O错误，例如文件路径无效或磁盘空间不足
     */
    private static void saveAsciiToFile(String asciiArt, String filePath) throws IOException {
        // 使用try-with-resources语句自动管理FileWriter资源，确保在块结束时关闭文件
        try (FileWriter writer = new FileWriter(filePath)) {
            // 将ASCII艺术写入文件
            writer.write(asciiArt);
        }
        // 注意：这里没有捕获IOException，因为它在方法签名中声明，由调用者处理
    }
}
