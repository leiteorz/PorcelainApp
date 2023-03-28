package com.android.china.room;

import com.android.china.model.GuanCang;
import com.android.china.room.dao.GuanCangDao;
import com.android.china.utils.MyApplication;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GuanCang.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract GuanCangDao guanCangDao();
}
