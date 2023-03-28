package com.android.china.room;

import com.android.china.model.GuanCang;
import com.android.china.room.dao.GuanCangDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GuanCang.class},version = 1)
public abstract class AppDataBase  {
    public abstract GuanCangDao guanCangDao();
}
