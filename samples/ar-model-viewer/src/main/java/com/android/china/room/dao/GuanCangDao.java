package com.android.china.room.dao;

import com.android.china.model.GuanCang;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GuanCangDao {
    /**
     * 插入馆藏实例(单个)
     */
    @Insert
    void insertGuanCang(GuanCang guanCang);
    /**
     * 插入馆藏实例(多个)
     */
    @Insert
    void insertGuanCangs(List<GuanCang> guanCangs);
    /**
     * 更新数据
     */
    @Update
    void updateGuanCang(GuanCang guanCang);
    /**
     * 删除数据
     */
    @Delete
    void deleteGuanCang(GuanCang guanCang);
    /**
     * 查询全部
     */
    @Query("select * from GuanCangs")
    List<GuanCang> queryAllGuanCangs();
    /**
     * 根据name查询
     */
    @Query("select * from GuanCangs where name like '%'||:name||'%'")
    List<GuanCang> queryGuanCangsByName(String name);

    @Query("select * from GuanCangs where id=:id")
    GuanCang queryGuanCangById(int id);
}
