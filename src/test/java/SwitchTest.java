public class SwitchTest {
    public static void main(String[] args) {
        int n = 3;
        switch (n) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
            default:
                System.out.println("default1");
        }
//        3
//        4
//        5
//        default1
        System.out.println("----------");

        switch (n) {
            default:
                System.out.println("default2");
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
//                3
//                4
//                5
        }
        System.out.println("----------");
        switch (n) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
                break;
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
            default:
                System.out.println("default3");
        }
//        3 ,匹配3执行break后结束
        System.out.println("----------");
        n = 6;
        switch (n) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
                break;
            case 4:
                System.out.println(4);
            case 5:
                System.out.println(5);
            default:
                System.out.println("default4");
        }
//        default4，default只有在全部不匹配情况下才会执行
    }
}
