package com.example.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.common.util.UIUtil;
import com.example.template.app.BaseActivity;
import com.example.template.module.recycleview.RecycleViewTestActivity;

public class MainActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //菜单选项
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                UIUtil.showActivity(mActivity, RecycleViewTestActivity.class);
                break;
            case R.id.two:
                Toast.makeText(mContext, "two", Toast.LENGTH_SHORT).show();
                break;
            case R.id.three:
                Toast.makeText(mContext, "three", Toast.LENGTH_SHORT).show();
                break;
            case R.id.four:
                Toast.makeText(mContext, "four", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
