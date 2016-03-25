package cn.zhoujia.haowanapp.Utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

import cn.zhoujia.haowanapp.MyApplication;
import cn.zhoujia.haowanapp.greendao.Notepad;
import cn.zhoujia.haowanapp.greendao.NotepadDao;

/**
 * Created by Zhoujia on 2016/3/24.
 */
public class DataBaseUtil {
    private static Cursor cursor;
    static NotepadDao notepadDao = MyApplication.mDaoSession.getNotepadDao();
    static String textColumn = NotepadDao.Properties.Notepadcontent.columnName;

    public static Boolean insert(Notepad notepad) {
        notepadDao.insert(notepad);//插入数据
        return true;
    }

    //查询设备数据库所有数据
    public static ArrayList selectNotepadAllData() {
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        cursor = MyApplication.getDb().query(notepadDao.getTablename(), notepadDao.getAllColumns(), null, null, null, null, orderBy);
        int columnsSize = cursor.getColumnCount();
        ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
        while (cursor.moveToNext()) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < columnsSize; i++) {
                map.put("id", cursor.getString(0));
                map.put("notepadcontent", cursor.getString(1));
                map.put("notepaddate", cursor.getString(2));
            }
            mData.add(map);
            // 排序
            Collections.sort(mData, new MapComparator());
        }
        return mData;
    }

    /**
     * 带条件的查询
     *
     * @param selectinfo  查询条件
     * @param selectvalue 查询的值
     * @return
     */
    public static Cursor selectDataWhere(String selectinfo, String selectvalue) {
        cursor = MyApplication.getDb().query(notepadDao.getTablename(), notepadDao.getAllColumns(), selectinfo + selectvalue, null, null, null, null);
        return cursor;
    }

    /**
     * 修改数据库数据
     *
     * @param updateValues ContentValues
     * @param updateinfo   修改条件
     * @param updatevalue  修改的值
     * @return
     */
    public static boolean update(ContentValues updateValues, String updateinfo, String[] updatevalue) {
        MyApplication.getDb().update(notepadDao.getTablename(), updateValues, updateinfo, updatevalue);
        return true;
    }

    /**
     * 删除数据库数据
     *
     * @param deleteinfo  删除的条件
     * @param deletevalue 删除的值
     * @return
     */
    public static Boolean delete(String deleteinfo, String[] deletevalue) {
        MyApplication.getDb().delete(notepadDao.getTablename(), deleteinfo, deletevalue);
        return true;
    }

}
