package com.dss886.dotaautochess.feature.filter;

import java.io.Serializable;

/**
 * Created by dss886 on 2019/1/30.
 */
public interface IFilterController {

    int DETAIL_TYPE_PRICE = 0;
    int DETAIL_TYPE_SPECIES = 1;
    int DETAIL_TYPE_PROFESSION = 2;

    void goDetail(int type, Serializable data);

}
