import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListStr {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        List list = new ArrayList<>();
        list.add(1L);
        list.add(2L);

        map.put("T", list);
        System.out.println(map.get("T").toString());
    }
}
