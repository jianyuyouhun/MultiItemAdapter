package cn.longmaster.mobile.multiitemadapter;

import cn.longmaster.mobile.multiitemadapter.library.MultiItem;

/**
 * item3
 * Created by wangyu on 2017/12/8.
 */

public class Item3 implements MultiItem {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return ItemId.TYPE_TREE;
    }
}

