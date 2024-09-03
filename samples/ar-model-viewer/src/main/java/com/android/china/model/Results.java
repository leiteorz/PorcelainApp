package com.android.china.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @Author Crwei
 * date 2023/7/10 15:58
 */

public class Results implements Serializable {
    public long log_id;
    public ResultEasydl[] results;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public ResultEasydl[] getResults() {
        return results;
    }

    public void setResults(ResultEasydl[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Results{" +
                "log_id=" + log_id +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
