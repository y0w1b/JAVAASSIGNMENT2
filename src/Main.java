import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 起始化scanner
        Scanner scanner = new Scanner(System.in);
        // 数组名称用于print数组时作为标题
        System.out.println("请输入数组名称： ");
        String name = scanner.nextLine();

        // 输入数组长度，直到用户输入为合理数值（大于1的整数）
        int length;
        while (true) {
            try {
                System.out.println("请输入数组长度（整数且至少为1）： ");
                length = scanner.nextInt();
                if (length < 1) {
                    System.out.println("错误：数组长度至少为1！");
                }
                else break;
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("错误：错误的操作，请重新输入！");
            }
        }

        // 创建数组
        System.out.println("数组创建完成，当前数组容量为" + length);
        ArrayExtension array = new ArrayExtension(length, name);

        // 用户输入下一步要进行的操作
        int action;
        while(true) {
            System.out.println("请输入以下数字进行下一步操作，可选择的操作有： ");
            System.out.println("-1 - 退出系统\t0 - 添加数据\t1 - 删除数据\t2 - 查找数据\t3 - 修改数据\t4 - 打印当前数组");

            // 保证输入若不为整数时不会crash
            try {
                action = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("错误：错误的操作，请重新输入！");
                continue;
            }

            // 根据用户的指令进行不同的功能，nextNum用于获取用户要添加、查找或删除的数据，index用于放置可能的返回下标
            int nextNum;
            int index;
            switch (action) {
                // 退出程序
                case -1:
                    return;
                case 0:
                    // 添加数字，直到用户输入返回指令前可一直添加
                    while(true) {
                        try{
                            // 输入数字并添加入数组
                            System.out.println("请输入要添加的数字或输入 'b' 返回到上一界面：");
                            nextNum = scanner.nextInt();
                            array.addElement(nextNum);
                        }
                        catch (InputMismatchException e) {
                            // 当输入不合理时，处理可能的错误并打印错误信息或返回上一界面
                            String command = scanner.nextLine();
                            if (command.equals("b")) {
                                break;
                            }
                            else {
                                System.out.println("错误：错误的操作，请重新输入！");
                            }
                        }
                    }
                    break;
                case 1:
                    // 删除数字，直到用户输入返回指令前可一直进行删除
                    while(true) {
                        try {
                            // 根据输入的下标查找并删除数字
                            System.out.println("请输入要删除的数字所在的下标或输入 'b' 返回到上一界面：");
                            index = scanner.nextInt();
                            nextNum = array.deleteElement(index);
                            // 若下标合理则打印具体成功信息
                            System.out.println("已在下标 " + index + " 找到并删除数字 " + nextNum);
                        }
                        catch (InputMismatchException e) {
                            // 当输入不合理时，处理可能的错误并打印错误信息或返回上一界面
                            String command = scanner.nextLine();
                            if (command.equals("b")) {
                                break;
                            }
                            else {
                                System.out.println("错误：错误的操作，请重新输入！");
                            }
                        }
                        catch (IndexOutOfRangeException e) {
                            // 若下标不合理则打印错误信息
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 2:
                    // 查找数字所在的所有下标，直到用户输入返回指令可一直进行查找
                    while(true) {
                        try {
                            // 根据输入的数字查找
                            System.out.println("请输入要查找的数字或输入 'b' 返回到上一界面：");
                            nextNum = scanner.nextInt();
                            ArrayList<Integer> indices = array.searchElement(nextNum);
                            if (indices.isEmpty()) {
                                // 若数字不存在于数组，打印信息
                                System.out.println("数字 " + nextNum + " 不存在于数组");
                            }
                            else {
                                // 若输入数字存在于数组，打印此数字存在的所有下标
                                System.out.print("在下标 ");
                                for (int num: indices) {
                                    System.out.print(num + " ");
                                }
                                System.out.print("处找到数字 " + nextNum + "\n");
                            }
                        }
                        catch (InputMismatchException e) {
                            // 当输入不合理时，处理可能的错误并打印错误信息或返回上一界面
                            String command = scanner.nextLine();
                            if (command.equals("b")) {
                                break;
                            }
                            else {
                                System.out.println("错误：错误的操作，请重新输入！");
                            }
                        }
                    }
                    break;
                case 3:
                    // 修改指定下标的数据，直到用户输入返回指令可一直进行查找
                    while(true) {
                        try {
                            // 根据输入的下标查找并删除数字
                            System.out.println("请输入要修改的数字所在的下标或输入 'b' 返回到上一界面：");
                            index = scanner.nextInt();
                            System.out.println("请输入新的数字或输入 'b' 返回到上一界面：");
                            nextNum = scanner.nextInt();
                            array.replaceElement(index, nextNum);
                            // 若下标合理则打印具体成功信息
                            System.out.println("已将下标 " + index + " 处的数字修改为 " + nextNum);
                        }
                        catch (InputMismatchException e) {
                            // 当输入不合理时，处理可能的错误并打印错误信息或返回上一界面
                            String command = scanner.nextLine();
                            if (command.equals("b")) {
                                break;
                            }
                            else {
                                System.out.println("错误：错误的操作，请重新输入！");
                            }
                        }
                        catch (IndexOutOfRangeException e) {
                            // 若下标不合理则打印错误信息
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    // 打印当前数组
                    array.printArray();
                    break;
                default:
                    // 处理其他的错误指令
                    System.out.println("错误：错误的操作，请重新输入！");
            }
        }
    }
}
