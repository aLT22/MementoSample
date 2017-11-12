package com.bytebuilding.memento.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytebuilding.memento.R;
import com.bytebuilding.memento.mvp.model.Memento;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Turkin A. on 12.11.2017.
 */

public class MementoRecyclerAdapter extends RecyclerView.Adapter<MementoRecyclerAdapter.ViewHolder> {

    private static final String TAG = MementoRecyclerAdapter.class.getSimpleName();

    private Context mContext;
    private List<Memento> mList;

    private OnItemClickDelegate mDelegate;

    public MementoRecyclerAdapter(Context context, List<Memento> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setDelegate(OnItemClickDelegate delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.memento_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Memento item = mList.get(position);

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
        void onItemClick(Memento item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}