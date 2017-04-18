package com.lewis.codingrode.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lewis on 17-4-17.
 * 基类
 */

public class BaseActivity extends AppCompatActivity{

    protected void startActivity(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
