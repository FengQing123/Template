package com.example.template.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.bean.PersonBean;

import java.util.List;

/**
 * 功能描述：具有动画特性的Adapter
 * 需要注意的是，要使RecycleView 具有动画，那么
 * 添加需要调用notifyItemInserted()
 * 删除需要调用notifyItemRemoved()
 * 移动需要调用notifyItemMoved()
 * 改变需要调用notifyItemChanged()
 * <p>
 * Created by gfq on 2019/12/24.
 */
public class PersonAnimatorAdapter extends RecyclerView.Adapter<PersonAnimatorAdapter.VH> {

    private List<PersonBean> mDatas;

    private OnStartDragListener mListener;

    public PersonAnimatorAdapter(List<PersonBean> data) {
        mDatas = data;
    }

    @NonNull
    @Override
    public PersonAnimatorAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        return new PersonAnimatorAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAnimatorAdapter.VH holder, int position) {

        PersonBean personBean = mDatas.get(position);
        if (personBean != null) {
            holder.mTextName.setText(personBean.getName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 的点击事件
            }
        });

        /**
         * touch 图片时实现回调
         */
        holder.mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (mListener != null) {
                        mListener.startDrag(holder);
                    }
                }
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public static class VH extends RecyclerView.ViewHolder {

        private final TextView mTextName;

        private final ImageView mImageView;

        VH(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.tv_name);
            mImageView = itemView.findViewById(R.id.image_head);
        }
    }

    /**
     * 添加数据
     *
     * @param pos
     * @param personBean
     */
    public void addData(int pos, PersonBean personBean) {
        if (pos < 0 || pos > mDatas.size() || personBean == null) {
            return;
        }
        mDatas.add(pos, personBean);
        notifyItemInserted(pos);
    }

    /**
     * 删除数据
     *
     * @param pos
     */
    public void removeData(int pos) {
        if (pos < 0 || pos >= mDatas.size()) {
            return;
        }
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    /**
     * 移动数据
     *
     * @param fromPos
     * @param toPos
     */
    public void moveData(int fromPos, int toPos) {
        notifyItemMoved(fromPos, toPos);
    }


    /**
     * 改变数据
     *
     * @param pos
     * @param personBean
     */
    public void notifyData(int pos, PersonBean personBean) {
        if (pos < 0 || pos > mDatas.size() || personBean == null) {
            return;
        }
        mDatas.set(pos, personBean);
        notifyItemChanged(pos);
    }

    public void setDragListener(OnStartDragListener listener) {
        this.mListener = listener;
    }

    public interface OnStartDragListener {
        void startDrag(RecyclerView.ViewHolder holder);
    }
}
