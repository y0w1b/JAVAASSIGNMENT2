// 用于处理下标ArrayExtension中，输入的下标不在0和curIndex的区间中的异常
// 由于curIndex小于或等于数组的容量，固不能直接使用IndexOutOfBoundsException
public class IndexOutOfRangeException extends Exception{
    public String getMessage() {
        return "错误：当前下标未储存数据，请输入正确下标";
    }
}
