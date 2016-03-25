package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "cn.zhoujia.haowanapp.greendao");
        addTABLE(schema);
        try {
            new DaoGenerator().generateAll(schema, "../HaoWanApp/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTABLE(Schema schema) {
        /**创建记事本表**/
        Entity rooms = schema.addEntity("Notepad");
        rooms.addIdProperty().primaryKey().autoincrement();
        rooms.addStringProperty("notepadcontent");
        rooms.addStringProperty("notepaddate");
    }

}
