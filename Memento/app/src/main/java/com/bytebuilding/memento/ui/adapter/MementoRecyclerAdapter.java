package com.bytebuilding.memento.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.mvp.model.MementoDemo;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public class MementoRecyclerAdapter extends RecyclerView.Adapter<MementoRecyclerAdapter.ViewHolder> {

    private static final String TAG = "MementoRecyclerAdapter";

    private Context mContext;
    private List<MementoDemo> mList;

    private OnItemClickDelegate mDelegate;

    public MementoRecyclerAdapter(Context context, List<MementoDemo> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setDelegate(OnItemClickDelegate delegate) {
        this.mDelegate = delegate;
    }

    public void setList(List<MementoDemo> list) {
        this.mList = new LinkedList<>();

        mList.addAll(list);
    }

    public List<MementoDemo> getList() {
        return this.mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.memento_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MementoDemo item = mList.get(position);

        holder.mTitle.setText(item.getTitle());
        holder.mDescription.setText(item.getDescription());
        holder.mDate.setText(item.getDate());

        if (mDelegate != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDelegate.onItemClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickDelegate {
        void onItemClick(MementoDemo item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTitle;

        @BindView(R.id.tv_description)
        TextView mDescription;

        @BindView(R.id.tv_creation_date)
        TextView mDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}