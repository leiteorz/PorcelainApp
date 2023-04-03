package com.android.china.room.dao;

import com.android.china.model.MyCollect;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyCollectionDao {
    /**
     * 插入收藏实例(单个)
     */
    @Insert
    void insertMyCollection(MyCollect collect);

    /**
     * 插入馆藏实例
     */
    @Insert
    void insertMyCollections(List<MyCollect> collects);

    /**
     * 删除数据
     */
    @Delete
    void deleteMyCollection(MyCollect collect);

    /**
     * 查询全部
     */
    @Query("select * from MyCollects")
    List<MyCollect> queryAllMyCollections();

    /**
     * 根据id和kind查询
     */
    @Query("select * from MyCollects where id=:id and kind=:kind")
    MyCollect queryMyCollectionByIdAndKind(int id,int kind);

    /**
     * 查询是否收藏
     */
    @Query("select isCollected from MyCollects where id=:id and kind=:kind")
    int queryIfCollectedById(int id,int kind);

    /**
     * 根据flag、id和kind更新isCollected
     */
    @Query("update MyCollects set isCollected=:flag where id=:id and kind=:kind")
    int updateIsCollected(int flag,int id,int kind);
}
