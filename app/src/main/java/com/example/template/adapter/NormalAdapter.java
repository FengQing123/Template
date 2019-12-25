package com.example.template.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2019/12/23.
 * <p>
 * <p>
 * 创建RecycleView Adapter的一般步骤：
 * 创建NormalAdapter继承自RecycleView.Adapter<NormalAdapter.VH>
 * 创建静态内部类VH继承自RecycleView.ViewHolder
 * 在 onCreateViewHolder(ViewGroup parent,in viewType)映射Item LayoutId,创建VH并返回
 * 在 void onBindViewHolder(VH holder,int position)中，为holder设置指定数据
 * 在 int getItemCount()中，返回item的个数
 */
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {

    private List<String> mDatas;

    public NormalAdapter(List<String> data) {
        mDatas = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * 这里需要注意：不能使用：
         * LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview,null);
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mTextName.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 的点击事件
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static class VH extends RecyclerView.ViewHolder {

        private final TextView mTextName;

        VH(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.tv_name);
        }
    }
}
