package com.jianyuyouhun.mobile.multiitemadapter.library;

import android.support.annotation.IntRange;

/**
 *
 * Created by wangyu on 2017/12/8.
 */

public interface MultiItem {

    @IntRange(from = Integer.MIN_VALUE, to = 0)
    int getItemType();
}
