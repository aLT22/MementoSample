package com.bytebuilding.memento.utils;

import android.support.v7.util.DiffUtil;

import com.bytebuilding.memento.mvp.model.Memento;

import java.util.List;
import java.util.Objects;

/**
 * Created by Turkin A. on 13.11.2017.
 */

public class MementoDiffUtilsCallback extends DiffUtil.Callback {

    public static final String TAG = MementoDiffUtilsCallback.class.getSimpleName();

    private List<Memento> oldList;
    private List<Memento> newList;

    public MementoDiffUtilsCallback(List<Memento> oldList, List<Memento> newList) {
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
        Memento oldMemento = oldList.get(oldItemPosition);
        Memento newMemento = newList.get(newItemPosition);

        return oldMemento.getTitle().equals(newMemento.getTitle())
                && oldMemento.getDate().equals(newMemento.getDate());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Memento oldMemento = oldList.get(oldItemPosition);
        Memento newMemento = newList.get(newItemPosition);

        return oldMemento.equals(newMemento);
    }
}
