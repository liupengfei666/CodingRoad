package com.lewis.codingrode.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActQrcodeSelectBinding;

/**
 * 基于封装之后的ZXing Android Embedded 库，项目地址：https://github.com/journeyapps/zxing-android-embedded当前最新版本是3.5.0。
 * 1、集成方法:在项目的build.gradle中添加以下依赖：
 * compile 'com.journeyapps:zxing-android-embedded:3.5.0'
 * compile 'com.google.zxing:core:3.3.0'
 * 2、直接通过Intent 调用 的方式来实现扫码功能 new IntentIntegrator(MainActivity.this).initiateScan();
 * 3、默认横屏，通过修改activity的属性可以更改为竖屏
 * Created by lewis on 2017/5/12.
 */

public class QRCodeSelectActivity extends BaseActivity implements View.OnClickListener {

    private ActQrcodeSelectBinding mBinding;
    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_qrcode_select);
        mBinding.btnScanBarcode.setOnClickListener(this);
        mBinding.btnCreateBarcode.setOnClickListener(this);
        mEditText = mBinding.edit;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("QRCodeSelectActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("QRCodeSelectActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scanBarcode://扫描条形码
                new IntentIntegrator(this).setCaptureActivity(QRCodeActivity.class).initiateScan();
                break;
            case R.id.btn_createBarcode:

                break;
        }
    }
}
