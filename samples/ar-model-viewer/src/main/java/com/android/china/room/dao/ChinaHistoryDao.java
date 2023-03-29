package com.android.china.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.china.model.ChinaHistory;
import com.android.china.model.GuanCang;

import java.util.List;
@Dao
public interface ChinaHistoryDao {
    @Insert
    void insertChinaHistory(List<ChinaHistory> chinaHistories);
    /**
     * 更新数据
     */
    @Update
    void updateChinaHistory(ChinaHistory ... chinaHistories);
    /**
     * 删除数据
     */
    @Delete
    void deleteChinaHistory(ChinaHistory ...chinaHistories);
    /**
     * 查询全部
     */
    @Query("select * from ChinaHistory")
    List<ChinaHistory> queryAllChinaHistory();
    /**
     * 根据name查询
     */
    @Query("select * from ChinaHistory where name LIKE '%'||:name||'%'")
    List<ChinaHistory> queryChinaHistorysByName(String name);
}
