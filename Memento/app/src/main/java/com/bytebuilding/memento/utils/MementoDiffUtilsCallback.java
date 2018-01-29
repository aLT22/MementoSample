package com.bytebuilding.memento.utils;

import android.support.v7.util.DiffUtil;

import com.bytebuilding.memento.mvp.model.MementoDemo;

import java.util.List;

/**
 * Created by Turkin A. on 13.11.2017.
 */

public class MementoDiffUtilsCallback extends DiffUtil.Callback {

    public static final String TAG = "MementoDiffUtilsCallback";

    private List<MementoDemo> oldList;
    private List<MementoDemo> newList;

    public MementoDiffUtilsCallback(List<MementoDemo> oldList, List<MementoDemo> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        MementoDemo oldMementoDemo = oldList.get(oldItemPosition);
        MementoDemo newMementoDemo = newList.get(newItemPosition);

        return oldMementoDemo.getTitle().equals(newMementoDemo.getTitle())
                && oldMementoDemo.getDate().equals(newMementoDemo.getDate());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        MementoDemo oldMementoDemo = oldList.get(oldItemPosition);
        MementoDemo newMementoDemo = newList.get(newItemPosition);

        return oldMementoDemo.equals(newMementoDemo);
    }
}
