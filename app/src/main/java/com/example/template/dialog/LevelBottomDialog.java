package com.example.template.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.dialog.CommonBottomDialog;
import com.example.common.dialog.CommonDialog;
import com.example.common.util.L;
import com.example.template.R;

/**
 * 功能描述：
 * Created by gfq on 2020/1/6
 **/
public class LevelBottomDialog extends CommonBottomDialog {

    private static final String TAG = "LevelDialog";

    public static LevelBottomDialog newInstance() {
        return new LevelBottomDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_level, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_left).setOnClickListener(v -> L.e(TAG, "ButtonLeft"));

        view.findViewById(R.id.btn_right).setOnClickListener(v -> L.e(TAG, "ButtonRight"));
    }
}
