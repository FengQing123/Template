package com.example.common.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.BuildConfig;
import com.example.common.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * 功能描述： 底部弹窗Dialog 通用设置
 * Created by gfq on 2020/1/6
 **/
public abstract class CommonBottomDialog extends BottomSheetDialogFragment {

    private DialogInterface.OnDismissListener mOnDismissListener;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private BottomSheetBehavior<View> mBehavior;

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener listener) {
        mOnCancelListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.dialog_bottom);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog instanceof BottomSheetDialog) {
            FrameLayout bottomSheet = ((BottomSheetDialog) dialog).getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                behavior.setHideable(enableDragToClose());
            }
        }
    }

    @CallSuper
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.post(() -> {
            mBehavior = BottomSheetBehavior.from((View) view.getParent());
            mBehavior.setHideable(enableDragToClose());
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            mBehavior.setSkipCollapsed(enableDragToClose());
        });
    }

    /**
     * 是否开启向下拖动关闭
     */
    protected boolean enableDragToClose() {
        return true;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(dialog);
        }
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void show(FragmentManager manager, String tag) {
        this.show(manager.beginTransaction(), tag);
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        return isAdded() && getDialog() != null && getDialog().isShowing();
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        if (isResumed() || isAdded() || isVisible() || isRemoving() || isDetached()) {
            if (BuildConfig.DEBUG) {
                boolean isResumed = isResumed();
                boolean isAdded = isAdded();
                boolean isVisible = isVisible();
                boolean isRemoving = isRemoving();
                boolean isDetached = isDetached();
                Log.e(getClass().getSimpleName(), "showDialog Failed !isResumed=" + isResumed
                        + ",isAdded=" + isAdded + ",isVisible=" + isVisible + ",isRemoving=" + isRemoving + ",isDetached=" + isDetached);
            }
            return -1;
        }
        try {
            return transaction.add(this, tag).commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "showDialog Failed", e);
            e.printStackTrace();
            return -1;
        }
    }
}
