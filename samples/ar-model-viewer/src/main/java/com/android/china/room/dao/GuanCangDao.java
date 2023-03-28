package com.android.china.room.dao;

import com.android.china.model.GuanCang;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;

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
    void insertGuanCangs(List<GuanCang> guanCangs);
}
