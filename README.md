# MultiItemAdapter的使用教程 #

## 为何要设计 ##

我们在开发中偶尔会遇到列表展示不同布局的需求，有些时候，数据类型以及布局大同小异。而有的时候则截然不同。

如果区别不大，那么我们可以自己在adapter的getView中去进行判断，同时viewHolder也可以写的大一些，包含所有的视图view定义。只是这种解决方案在面对多变的需求的时候会变得难以操控。最终会导致代码很难再扩展。

> **所以，这个框架的诞生就是为了解决上面的各种'小麻烦'**

## 框架引入 ##

在项目的根目录下的gradle中添加

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

在app目录的gradle中添加依赖

    compile 'com.github.jianyuyouhun:MultiItemAdapter:1.1'

## 使用 ##

### 1、实体类需要实现接口MultiItem ###

`MultiItem`接口只有一个实现方法，返回了item的类型如下：

	public interface MultiItem {
	
	    @IntRange(from = Integer.MIN_VALUE, to = 0)
	    int getItemType();
	}

**这里我们规定返回item类型的值为非正数**，因为MultiItemAdapter继承了BaseAdapter并重写了`getItemViewType`和`getViewTypeCount`方法，而系统级代码就规定了所有item类型的getItemType的值不能大于item类型的数目，否则会报错。那么为了避免这种情况的发生，我们做出如上规定。

### 2、适配器继承MultiItemAdapter ###

在此之前，我们先模拟一下特点的需求场景，现在有三种不同的显示需求。


1. 文字+图片显示
2. 标题居中显示
3. 文本右对齐显示

那么我们可以得到三种实体类型，先命名为Item1,Item2,Item3，代码如下：

	public class Item1 {
	
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
	}

	public class Item2 {
	
	    private String name;
	
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	}
	
	public class Item3{
	
	    private String name;
	
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	}


**接下来继承MultiItemAdapter开发，我们需要重写三个方法。**具体使用我会详细说明


    /**
     * 注册item类型
     * @param typeList
     */
    protected abstract void initItemType(List<Integer> typeList);

    /**
     * 根据不同的itemType返回不同的viewHolder类型
     * @param itemType
     * @return
     */
    protected abstract Class<? extends ViewHolder> getViewHolderByItemType(Integer itemType);

    /**
     * 绑定item布局内容
     * @param itemType      item类型
     * @param viewHolder    viewHolder
     * @param multiItem     multiItem
     * @param position      当前位置
     */
    protected abstract void bindView(int itemType, ViewHolder viewHolder, MultiItem multiItem, int position);

#### initItemType(List<Integer> typeList)方法 ####


此方法用来注册子类型的id，也就是标识唯一itemType。为了方便管理，我在demo中定义了一个枚举整型数据ItemId，具体开发中你可以不用这么设计。

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


那么回到正题，我们注册的方式就是向typeList中添加我们的itemType；例如：

    @Override
    protected void initItemType(List<Integer> typeList) {
        typeList.add(ItemId.TYPE_ONE);
        typeList.add(ItemId.TYPE_TWO);
        typeList.add(ItemId.TYPE_TREE);
    }

#### getViewHolderByItemType(Integer itemType)方法 ####

这个方法如何理解呢？首先我们自己的ViewHolder要继承MultiItemAdapter的ViewHolder，同时保证ViewHolder为static类型（如果是内部类声明的话）

ViewHolder声明如下：（这里我是写在adapter内部，所以均加上static修饰符），xml就不写了，**我把xml的绑定写在了这里面**

    public static class ViewHolder1 extends ViewHolder {

        private TextView mTextView;

        private ImageView imageView;

        @Override
        protected void setItemView(View itemView) {
            super.setItemView(itemView);
            mTextView = itemView.findViewById(R.id.list_item_text1);
            imageView = itemView.findViewById(R.id.list_image);
        }

        @Override
        protected int getLayoutId() {//这里绑定layoutId
            return R.layout.adapter_multi_item_1;
        }
    }

    public static class ViewHolder2 extends ViewHolder {

        private TextView mTextView;

        @Override
        protected void setItemView(View itemView) {
            super.setItemView(itemView);
            mTextView = itemView.findViewById(R.id.list_item_text2);
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
            mTextView = itemView.findViewById(R.id.list_item_text3);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.adapter_multi_item_3;
        }
    }

回到正题，我们的代码是这样的：

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

#### bindView(int itemType, ViewHolder viewHolder, MultiItem multiItem, int position)方法 ####

这个方法就比较好理解了，要做的就是根据不同的itemType将viewHolder强转为目标ViewHolder（viewHolder1,viewHolder2或viewHolder3）,然后我们的三个实体类需要实现MultiItem接口，返回对应的itemType，在这里就将multiItem同样的强转为我们的item1，item2，item3。然后做对应的显示逻辑

### 3、更多 ###

demo中图片绑定使用了xUtils3，动态权限使用了EZPermission。详细可查看源码。下面是效果图：

![](GIF.gif)

2017/12/8 17:12:14 created by 王宇