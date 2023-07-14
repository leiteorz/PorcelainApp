package com.android.china.room;

import android.content.Context;

import com.android.china.model.ChinaHistory;
import com.android.china.model.DiyWork;
import com.android.china.model.GuanCang;
import com.android.china.model.MyCollect;
import com.android.china.model.MyWork;
import com.android.china.room.dao.ChinaHistoryDao;
import com.android.china.room.dao.DiyWorkDao;
import com.android.china.room.dao.GuanCangDao;

import com.android.china.room.dao.MyCollectionDao;
import com.android.china.utils.MyApplication;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GuanCang.class, ChinaHistory.class, MyCollect.class,DiyWork.class},version = 2,exportSchema = false)
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
    public abstract GuanCangDao guanCangDao();
    public abstract ChinaHistoryDao ChinaHistoryDao();
    public abstract MyCollectionDao myCollectionDao();

    public abstract DiyWorkDao diyWorkDao();
}
