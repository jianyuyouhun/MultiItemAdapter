package com.jianyuyouhun.mobile.multiitemadapter;


import com.jianyuyouhun.mobile.multiitemadapter.library.MultiItem;

/**
 * item1
 * Created by wangyu on 2017/12/8.
 */

public class Item1 implements MultiItem {

    private String name;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return ItemId.TYPE_ONE;
    }
}
