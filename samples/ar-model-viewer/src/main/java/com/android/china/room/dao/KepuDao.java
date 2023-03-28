package com.android.china.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.china.model.ChinaHistory;

import java.util.List;

@androidx.room.Dao
public interface KepuDao {
    @Insert
    void insert(ChinaHistory ... chinaHistories);
    @Update
    void update(ChinaHistory ... chinaHistories);
    @Delete
    void delete(ChinaHistory chinaHistories);
    @Query("select * from ChinaHistory where name='%name%'")
    List<ChinaHistory> query();
}
