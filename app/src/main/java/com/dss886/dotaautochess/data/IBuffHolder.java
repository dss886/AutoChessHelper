package com.dss886.dotaautochess.data;

import java.util.List;

/**
 * Created by dss886 on 2019/1/28.
 */
public interface IBuffHolder {

    String getName();

    int getColorRes();

    String getBuffName();

    List<Buff> getBuffList();

}
