package com.example.template.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.bean.Star;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/10/16
 **/
public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {

    private List<Star> mList;

    public StarAdapter(List<Star> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_star, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        holder.mTv.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class StarViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_star);
        }
    }


    /**
     * 是否是组的第一个item
     *
     * @param pos
     * @return
     */
    public boolean isGroupHeader(int pos) {
        if (pos == 0) {
            return true;
        } else {
            String currentName = getGroupName(pos);
            String preName = getGroupName(pos - 1);
            if (currentName.equals(preName)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public String getGroupName(int pos) {
        return mList.get(pos).getGroupName();
    }


}
