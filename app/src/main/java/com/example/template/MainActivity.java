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
import com.example.template.module.DefineTextViewActivity;
import com.example.template.module.notification.NotificationActivity;
import com.example.template.module.recycleview.RecycleViewTestActivity;
import com.example.template.module.recycleview.RvToViewPagerActivity;

public class MainActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                UIUtil.showActivity(mActivity, RvToViewPagerActivity.class);
                break;
            case R.id.three:
                UIUtil.showActivity(mActivity, NotificationActivity.class);
                break;
            case R.id.four:
                UIUtil.showActivity(mActivity, DefineTextViewActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
