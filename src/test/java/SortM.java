public class SortM {
    public static void main(String[] args) {
        int[] num = {2, 4, 3, 1, 9, 0};
        choiceSort(num);
    }

    /**
     * 因为这种算法总是在不断的选择剩余元素中最小者，因此得名选择排序
     * O（n²）
     * @param num
     */
    private static void choiceSort(int[] num) {
        for (int n = 0; n < num.length; n++) {

            for (int m = (n + 1); m < num.length; m++) {
                if (num[n] > num[m]) {
                    int tem = num[n];
                    num[n] = num[m];
                    num[m] = tem;
                }
            }
        }

        for (int n = 0; n < num.length; n++) {
            System.out.println(num[n]);
        }
    }
}
