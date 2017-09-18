package com.wl.daydayup.cheerlibs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wl.daydayup.mcheerlibs.utils.LogUtils;
import com.wl.daydayup.mcheerlibs.utils.NetUtils;
import com.wl.daydayup.mcheerlibs.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.test_isnet_bt)
    Button mTestIsnetBt;
    @BindView(R.id.test_iswifi_bt)
    Button mTestIswifiBt;
    @BindView(R.id.test_opensetting_bt)
    Button mTestOpensettingBt;
    @BindView(R.id.test_defualt_toast_bt)
    Button mTestToastBt;
    @BindView(R.id.test_changeBg_toast_bt)
    Button mTestChangeBgToastBt;
    @BindView(R.id.test_imgtoast_bt)
    Button mTestImgtoastBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test_isnet_bt,
            R.id.test_iswifi_bt,
            R.id.test_opensetting_bt,
            R.id.test_defualt_toast_bt,
            R.id.test_changeBg_toast_bt,
            R.id.test_imgtoast_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_isnet_bt:
                if (NetUtils.isConnected(this)) {
                    LogUtils.w(TAG, "有网络");
                } else {
                    LogUtils.i(TAG, "网络连接失败");
                }
                break;
            case R.id.test_iswifi_bt:
                if (NetUtils.isWifi(this)) {
                    LogUtils.d(TAG, "是无线网");
                } else {
                    LogUtils.d(TAG, "不是无线网");
                }
                break;
            case R.id.test_opensetting_bt:
                if (!NetUtils.isConnected(this)) {
                    NetUtils.openSetting(this);
                }
                break;
            case R.id.test_defualt_toast_bt:
                new ToastUtils.ToastBuilder(this)
                        .build().showToast("默认土司");
                break;
            case R.id.test_changeBg_toast_bt:
                new ToastUtils.ToastBuilder(this)
                        .setToastBgColor(android.R.color.holo_green_light)
                        .build().showToast("改变背景颜色的土司");
                break;
            case R.id.test_imgtoast_bt:
                new ToastUtils.ToastBuilder(this)
                        .setToastImage(ToastUtils.ToastImage.SUCCESS)
                        .setToastBgColor(android.R.color.holo_purple)
                        .build().showToast("我是自定义土司");
                break;
        }
    }
}
