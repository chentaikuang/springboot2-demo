package xiaochen.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class DateUtil {
    public static String curDate() {
        return DateFormatUtils.format(new Date(), "YYYY-MM-dd hh:mm:ss");
    }
}
