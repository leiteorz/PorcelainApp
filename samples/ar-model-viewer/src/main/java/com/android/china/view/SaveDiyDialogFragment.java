package com.android.china.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.android.china.room.AppDataBase;
import com.android.china.room.dao.DiyWorkDao;
import com.google.ar.sceneform.samples.gltf.R;
import com.tencent.mmkv.MMKV;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SaveDiyDialogFragment extends DialogFragment {
    public interface SaveDiyDialogListener{
        public void onDialogPositiveClick(SaveDiyDialogFragment dialog);
    }

    SaveDiyDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (SaveDiyDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "must implement DialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.save_diy_dialog,null))
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogPositiveClick(SaveDiyDialogFragment.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SaveDiyDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}
