package com.jianyuyouhun.mobile.multiitemadapter;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.jianyuyouhun.mobile.multiitemadapter.library.MultiItem;
import com.jianyuyouhun.permission.library.EZPermission;
import com.jianyuyouhun.permission.library.OnRequestPermissionResultListener;
import com.jianyuyouhun.permission.library.PRequester;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private static String[] urls = new String[]{
            "http://img3.imgtn.bdimg.com/it/u=1749061261,2462112140&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1949438216,3782070973&fm=23&gp=0.jpg",
            "http://imgs.91danji.com/Upload/201419/2014191037351347278.jpg",
            "http://img.newyx.net/newspic/image/201410/14/90ea74d5b0.jpg"
    };

    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        adapter = new TestAdapter(this);
        listView.setAdapter(adapter);
        requestWritePermission();
    }

    private void go() {
        adapter.setData(buildData());
    }

    private void requestWritePermission() {
        EZPermission.Companion.getInstance().requestPermission(this,
                new PRequester(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                new OnRequestPermissionResultListener() {
                    @Override
                    public void onRequestSuccess(String s) {
                        go();
                    }

                    @Override
                    public void onRequestFailed(String s) {
                        Toast.makeText(MainActivity.this, "请授予权限", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private List<MultiItem> buildData() {
        List<MultiItem> multiItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * 10) % 3;
            if (random == 0) {
                Item1 item1 = new Item1();
                int pos = (int) (Math.random() * 10) % 4;
                switch (pos) {
                    case 0:
                        item1.setName("艾希" + i);
                        break;
                    case 1:
                        item1.setName("猴子" + i);
                        break;
                    case 2:
                        item1.setName("瑞文" + i);
                        break;
                    case 3:
                        item1.setName("剑圣" + i);
                        break;
                }
                item1.setUrl(urls[pos]);
                multiItems.add(item1);
            } else if (random == 1) {
                Item2 item2 = new Item2();
                item2.setName("消息" + i + "：这是一条没有营养的消息，你可以看，可以不看，看或者不看，它就在这里，单纯的为了撑起视图的高度而写下");
                multiItems.add(item2);
            } else {
                Item3 item3 = new Item3();
                item3.setName("标题" + i);
                multiItems.add(item3);
            }
        }
        return multiItems;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EZPermission.Companion.getInstance().onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EZPermission.Companion.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
