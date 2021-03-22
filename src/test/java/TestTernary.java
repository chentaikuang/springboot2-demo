public class TestTernary {
    public static void main(String[] args) {
        Integer a = 3, b = 4, c = null;
        boolean flag = false;
        System.out.println(flag ? a * b : c);//NullPointerException:自动拆箱造成空指针
    }
}
