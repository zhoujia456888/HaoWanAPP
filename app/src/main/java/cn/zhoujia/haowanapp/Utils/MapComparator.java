package cn.zhoujia.haowanapp.Utils;

import java.util.Comparator;
import java.util.Map;

/**
 * ArrayList排序
 * Created by Zhoujia on 2016/3/25.
 */
public class MapComparator implements Comparator<Map<String, Object>> {

    @Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        // TODO Auto-generated method stub
        String b1 = o1.get("notepaddate").toString();
        String b2 = o2.get("notepaddate").toString();
        if (b2 != null) {
            return b2.compareTo(b1);
        }
        return 0;
    }
}
