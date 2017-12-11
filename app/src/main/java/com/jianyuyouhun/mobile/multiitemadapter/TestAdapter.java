package com.jianyuyouhun.mobile.multiitemadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jianyuyouhun.mobile.multiitemadapter.library.MultiItemAdapter;

import org.xutils.x;

import java.util.List;


/**
 * 测试多item适配器
 * Created by wangyu on 2017/12/8.
 */

public class TestAdapter extends MultiItemAdapter {

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    protected void initItemType(List<Integer> typeList) {
        typeList.add(ItemId.TYPE_ONE);
        typeList.add(ItemId.TYPE_TWO);
        typeList.add(ItemId.TYPE_TREE);
    }

    @Override
    protected Class<? extends ViewHolder> getViewHolderByItemType(Integer itemType) {
        switch (itemType) {
            case ItemId.TYPE_ONE:
                return ViewHolder1.class;
            case ItemId.TYPE_TWO:
                return ViewHolder2.class;
            case ItemId.TYPE_TREE:
                return ViewHolder3.class;
            default:
                throw new RuntimeException("未解析的itemViewHolder, itemType: " + itemType);
        }
    }

    public static class ViewHolder1 extends ViewHolder<Item1> {

        private TextView mTextView;

        private ImageView imageView;

        @Override
        protected void setItemView(View itemView) {
            super.setItemView(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text1);
            imageView = (ImageView) itemView.findViewById(R.id.list_image);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.adapter_multi_item_1;
        }

        @Override
        protected void onBindView(MultiItemAdapter adapter, Item1 info, int position) {
            mTextView.setText(info.getName());
            final String url = info.getUrl();
            imageView.setTag(url);
            x.image().bind(imageView, url);
        }
    }

    public static class ViewHolder2 extends ViewHolder<Item2> {

        private TextView mTextView;

        @Override
        protected void setItemView(View itemView) {
            super.setItemView(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text2);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.adapter_multi_item_2;
        }

        @Override
        protected void onBindView(MultiItemAdapter adapter, Item2 info, int position) {
            mTextView.setText(info.getName());
        }
    }

    public static class ViewHolder3 extends ViewHolder<Item3> {

        private TextView mTextView;

        @Override
        protected void setItemView(View itemView) {
            super.setItemView(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text3);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.adapter_multi_item_3;
        }

        @Override
        protected void onBindView(MultiItemAdapter adapter, Item3 info, int position) {
            mTextView.setText(info.getName());
        }
    }
}
