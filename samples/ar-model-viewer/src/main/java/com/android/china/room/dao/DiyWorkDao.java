package com.android.china.room.dao;

import com.android.china.model.DiyWork;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DiyWorkDao {
    /**
     * 插入diy作品
     */
    @Insert
    void insertDiyWork(DiyWork work);
    /**
     * 查询所有Diy作品
     */
    @Query("select * from DiyWorks")
    List<DiyWork> queryDiyWorks();
}
