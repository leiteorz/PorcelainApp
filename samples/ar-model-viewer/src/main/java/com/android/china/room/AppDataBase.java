package com.android.china.room;

import android.content.Context;

import com.android.china.model.GuanCang;
import com.android.china.model.MyWork;
import com.android.china.room.dao.GuanCangDao;
import com.android.china.utils.MyApplication;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GuanCang.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "TaoCi_db.db";
    private static AppDataBase mInstance;

    public static synchronized AppDataBase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return mInstance;
    }
//    白忙活
    public abstract GuanCangDao guanCangDao();
}
