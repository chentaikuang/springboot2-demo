import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ParseRuleContentOpts {
    public static void main(String[] args) {
        String content = "SELECT * FROM auth_menu limit 1";
//        content= "SELECT order_status,XXX,YYY yyy from mall_center_order.order_order WHERE id = #orderId#";
        content = "SELECT COUNT(1) total, from mall_center_promotion.prmt_coupon_member WHERE id in (#couponIds#) and state = 2";
//        content = "order:commit:coupon:#id#";
        String upperCase = content.toUpperCase();

        List<String> columnOpts = new ArrayList<>();
        if (upperCase.startsWith("SELECT")) {
            int startIndex = "SELECT".length();
            int endIndex = upperCase.indexOf("FROM");
            String subStr = content.substring(startIndex, endIndex);
            System.out.println(subStr);
            String[] columnArr = subStr.split(",");
            for (String column : columnArr) {
                if (StringUtils.isBlank(column)) {
                    System.out.println("空列别名 ：[" + column + "]");
                    continue;
                }
                String[] aliasColumns = column.trim().split("\\s+");
                String aliasColumn = aliasColumns[aliasColumns.length - 1];
                System.out.println(aliasColumn);
                columnOpts.add(aliasColumn);
            }
        } else if (upperCase.startsWith("UPDATE") || upperCase.startsWith("DELETE") || upperCase.startsWith("TRUNCATE")) {
            //todo 不支持
        } else {
            columnOpts.add("RESULT_KEY");
        }
        columnOpts.forEach(opt -> {
            System.out.println("下拉选项：" + opt);
        });
    }
}
