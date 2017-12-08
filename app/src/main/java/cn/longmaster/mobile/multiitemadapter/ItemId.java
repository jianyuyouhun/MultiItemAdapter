package cn.longmaster.mobile.multiitemadapter;

import android.support.annotation.IntDef;

import static cn.longmaster.mobile.multiitemadapter.ItemId.TYPE_ONE;
import static cn.longmaster.mobile.multiitemadapter.ItemId.TYPE_TREE;
import static cn.longmaster.mobile.multiitemadapter.ItemId.TYPE_TWO;

/**
 * ItemId类型枚举，值不能为正数，可以为0
 * Created by wangyu on 2017/12/8.
 */


@IntDef({TYPE_ONE, TYPE_TWO, TYPE_TREE})
@interface ItemId {
    int TYPE_ONE = -1;
    int TYPE_TWO = -2;
    int TYPE_TREE = -3;
}
