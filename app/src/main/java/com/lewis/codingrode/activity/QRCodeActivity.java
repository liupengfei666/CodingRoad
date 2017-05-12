package com.lewis.codingrode.activity;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.lewis.codingrode.R;
import com.lewis.codingrode.base.BaseActivity;
import com.lewis.codingrode.databinding.ActQrcodeBinding;

/**
 * Created by lewis on 2017/5/12.
 */

public class QRCodeActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener, View.OnClickListener{

    private ActQrcodeBinding mBinding;
    private CaptureManager mCaptureManager;
    private DecoratedBarcodeView mBarcodeView;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_qrcode);
        mButton = mBinding.btnLight;
        mButton.setOnClickListener(this);
        mBarcodeView = mBinding.barcodeScanner;
        mBarcodeView.setTorchListener(this);
        if (!hasFlash()) {
            mBinding.btnLight.setVisibility(View.GONE);
        }

        // 重要代码，初始化捕获
        mCaptureManager = new CaptureManager(this, mBarcodeView);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
        mCaptureManager.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCaptureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCaptureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mCaptureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mBarcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    /**
     * Check if the device's camera has a Flashlight.
     * @return true if there is Flashlight, otherwise false.
     */
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    public void onTorchOn() {
        mButton.setText("Turn Off FlashLight");
    }

    @Override
    public void onTorchOff() {
        mButton.setText("Turn On FlashLight");
    }

    @Override
    public void onClick(View v) {
        if ("Turn On FlashLight".equals(mButton.getText())) {
            mBarcodeView.setTorchOn();
        } else {
            mBarcodeView.setTorchOff();
        }
    }
}
