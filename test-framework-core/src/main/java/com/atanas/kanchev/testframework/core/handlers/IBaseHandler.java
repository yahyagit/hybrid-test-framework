package com.atanas.kanchev.testframework.core.handlers;

/**
 * Created by atanas on 24/03/2016.
 */
interface IBaseHandler {

    default void sleep(long mils) {
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
