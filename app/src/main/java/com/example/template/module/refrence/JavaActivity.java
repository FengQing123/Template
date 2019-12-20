package com.example.template.module.refrence;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.template.R;
import com.example.template.app.BaseActivity;

/**
 * 功能描述：
 * Created by gfq on 2019/12/20.
 */
public class JavaActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
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
                Toast.makeText(mContext, "one", Toast.LENGTH_SHORT).show();
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
