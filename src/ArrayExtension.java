import java.util.ArrayList;

// ArrayExtension为数组array增加了不用下标添加数据、删除下标所在数据并将后面数据前移、查找给定数据所在的多个下标、
// 修改给定下标的数据、打印当前数组、自动拓展数组容量等功能。
public class ArrayExtension {
    // Array为正常数组储存数据，
    // curIndex用于储存数组第一个空位的下标，
    // arrayHeader作为数组标题在打印时使用
    private int[] array;
    private int curIndex;
    private final String arrayHeader;

    // 创建对象时设置数组初始容量和标题
    public ArrayExtension(int length, String header) {
        array = new int[length];
        curIndex = 0;
        arrayHeader = header;
    }

    // 给数组添加数据，若剩余容量小于或等于总容量的20%，则扩大容量为之前的50%
    public void addElement(int number) {
        // 储存数据
        array[curIndex] = number;
        // 后移第一空位下标
        curIndex++;
        System.out.println("数字 " + number + " 添加成功！");
        // 通过第一空位下标和当前数组容量判断剩余容量是否小于或等于20%
        double volumeStatus = (double)curIndex / array.length;
        if (volumeStatus >= 0.8) {
            // 若已占容量大于或等于80%（剩余容量是否小于或等于20%）则进行扩容
            System.out.println("当前数组容量已被占用80%以上，将扩容50%");
            this.extendLength();
        }
        System.out.println("更新后的数组：");
        // 打印更新后的数组
        this.printArray();
    }

    // 查找输入数字所在的所有下标，用ArrayList返回所有下标
    public ArrayList<Integer> searchElement(int target) {
        ArrayList<Integer> indices = new ArrayList<>();
        // 遍历数组，若数组中的数字与用户输入相等，将下标存入ArrayList
        for (int i = 0; i < curIndex; i++) {
            if (target == array[i]) {
                indices.add(i);
            }
        }
        return indices;
    }

    // 修改/替换给定下标的数字
    public void replaceElement(int index, int value) throws IndexOutOfRangeException {
        if (index < curIndex && index >= 0) {
            // 若所给下标在已存储的区间内，修改数据并打印修改后的数组
            array[index] = value;
            printArray();
        }
        else {
            // 若下标不在区间内，抛出异常由Main处理
            throw new IndexOutOfRangeException();
        }
    }

    // 删除给定下标的数字
    public int deleteElement(int index) throws IndexOutOfRangeException {
        if (index < curIndex && index >= 0){
            // 记录所删除数据用于返回
            int deletedNum = array[index];
            // 若所给下标在已存储的区间内，将删除数据所在下标之后的数据改写到此下标之后，
            // 即，在删除数据的同时把后面的数据前移
            System.arraycopy(array, index + 1, array, index, curIndex - index);
            // 保证第一空位下标数值正确
            curIndex--;
            // 打印修改后的数组
            this.printArray();
            return deletedNum;
        }
        else {
            // 若下标不在区间内，抛出异常由Main处理
            throw new IndexOutOfRangeException();
        }
    }

    // 打印当前数组
    public void printArray() {
        System.out.println("\n名称：" + arrayHeader + "\t容量：" + array.length);
        for (int i = 0; i < curIndex; i++) {
            System.out.println(i + " " + array[i]);
        }
        System.out.println();
    }

    // 为数组扩容50%
    private void extendLength() {
        // 扩容50%，Math.round用与将得数四舍五入，这种情况下当length为1时也能正确扩容到2
        int newLength = (int)Math.round(array.length * 1.5);
        // 将原来的数据复制到新的数组中
        int[] array = new int[newLength];
        System.arraycopy(this.array, 0, array, 0, this.array.length);
        // 使array引用扩容并获取了原数据的新数组
        this.array = array;
        System.out.println("扩容完成，当前数组容量为" + array.length);
    }
}
