public class TestCatchException {
    public static void main(String[] args) {
        Integer nm = null;
        try {
            boolean b = nm.intValue() > 0; //NullPointerException
            System.out.println(">0");

            nm = 0;
            int mod = 1 / nm; //ArithmeticException
            System.out.println("mod:" + mod);
        } catch (ArithmeticException e) {
            System.out.println("Err:" + e.getMessage());
        }
        System.out.println("----------------");
    }
}
