package com.bytebuilding.memento.rx;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Turkin A. on 19.10.17.
 */

public class MementoRxBus {

    private PublishSubject<Object> mBus = PublishSubject.create();

    public MementoRxBus() {
    }

    public Observable<Object> observable() {
        return this.mBus;
    }

    public void send(Object o) {
        mBus.onNext(o);
    }

}
