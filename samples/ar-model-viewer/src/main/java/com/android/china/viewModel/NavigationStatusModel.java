package com.android.china.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigationStatusModel extends ViewModel {
    /**
     * 底部任务栏的状态
     * 0:主页
     * 1:创造
     * 2:我的
     */
    public final static MutableLiveData<Integer> status = new MutableLiveData<>(0);

    public void setStatus(int choice){
        status.setValue(choice);
    }

}
