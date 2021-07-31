package com.subtitle.transfer.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MyArrayList extends ArrayList {
    private Object[] myElementData;

    public MyArrayList(){
        super();
    }
    public  MyArrayList(Object[] o){
        setMyElementData(o);
    }
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {

            return -1;

        } else {
            for (Object myElementDatum : myElementData)
                if (myElementDatum.equals(o))
                    return 1;
        }
        return -1;
    }

    public void setMyElementData(Object[] myElementData) {
        this.myElementData = myElementData;
    }
}