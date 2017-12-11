package com.jianyuyouhun.mobile.multiitemadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jianyuyouhun.mobile.multiitemadapter.library.MultiItem;
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

    @Override
    protected void bindView(int itemType, ViewHolder viewHolder, MultiItem multiItem, int position) {
        switch (itemType) {
            case ItemId.TYPE_ONE:
                ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
                Item1 item1 = (Item1) multiItem;
                bindItem1View(viewHolder1, item1, position);
                break;
            case ItemId.TYPE_TWO:
                ViewHolder2 viewHolder2 = (ViewHolder2) viewHolder;
                Item2 item2 = (Item2) multiItem;
                bindItem2View(viewHolder2, item2, position);
                break;
            case ItemId.TYPE_TREE:
                ViewHolder3 viewHolder3 = (ViewHolder3) viewHolder;
                Item3 item3 = (Item3) multiItem;
                bindItem3View(viewHolder3, item3, position);
                break;
        }
    }

    private void bindItem1View(ViewHolder1 viewHolder, Item1 item, int position) {
        viewHolder.mTextView.setText(item.getName());
        final String url = item.getUrl();
        viewHolder.imageView.setTag(url);
        x.image().bind(viewHolder.imageView, url);
    }

    private void bindItem2View(ViewHolder2 viewHolder2, Item2 item2, int position) {
        viewHolder2.mTextView.setText(item2.getName());
    }

    private void bindItem3View(ViewHolder3 viewHolder3, Item3 item3, int position) {
        viewHolder3.mTextView.setText(item3.getName());
    }

    public static class ViewHolder1 extends ViewHolder {

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
    }

    public static class ViewHolder2 extends ViewHolder {

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
    }

    public static class ViewHolder3 extends ViewHolder {

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
    }
}
