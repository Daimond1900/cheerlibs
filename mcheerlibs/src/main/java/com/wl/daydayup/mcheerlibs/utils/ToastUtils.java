package com.wl.daydayup.mcheerlibs.utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wl.daydayup.mcheerlibs.R;

/**
 * Created by 1900 on 2017/9/18.
 */

public class ToastUtils {

    public enum ToastImage {
        SUCCESS(R.drawable.ic_toast_success),
        CUSTOM(-1);

        private int imageId;

        ToastImage(int imageId) {
            this.imageId = imageId;
        }

        public int getImageId() {
            return imageId;
        }

        public ToastImage setImageId(int imageId) {
            this.imageId = imageId;
            return this;
        }
    }


    private View toastLayout;
    private ImageView toastImageView;
    private TextView toastTextView;
    private Context mContext;
    private boolean mIsToastMore;
    private Toast toastNew;

    private ToastUtils(ToastBuilder toastBuilder) {
        this.toastLayout = toastBuilder.toastLayout;
        this.toastImageView = toastBuilder.toastImageView;
        this.toastTextView = toastBuilder.toastTextView;
        this.mContext = toastBuilder.mContext;
        mIsToastMore = toastBuilder.mIsToastMore;
        this.toastNew = toastBuilder.toastNew;
    }

    /**
     * 只含有文字信息的吐司
     *
     * @param message 提示信息
     */
    public void showToast(String message) {

        toastTextView.setText(message);

        if (toastImageView.getDrawable() == null) {
            toastImageView.setVisibility(View.GONE);
        } else {
            toastImageView.setVisibility(View.VISIBLE);
        }
        toastNew.setView(toastLayout);
        toastNew.show();
    }


    // msg img
    public static class ToastBuilder {
        private View toastLayout;
        private ImageView toastImageView;
        private TextView toastTextView;
        private Context mContext;
        private boolean mIsToastMore = true;
        private Toast toastNew;

        public ToastBuilder(Context mContext) {
            toastLayout = LayoutInflater.from(mContext).inflate(R.layout.toast, null);
            toastImageView = toastLayout.findViewById(R.id.imageView);
            toastTextView = toastLayout.findViewById(R.id.message);
            this.mContext = mContext;
            toastNew = new Toast(mContext);

            init();
        }

        /**
         *  默认的值
         *  <p>
         *      显示时间 短
         *      背景颜色黑色
         *  </p>
         *
         */
        private void init() {
            setToastTime(Toast.LENGTH_SHORT);
            setToastBgColor(android.R.color.background_dark);
        }

        public ToastBuilder setToastTime(int time) {
            toastNew.setDuration(time);
            return this;
        }

        public ToastBuilder setToastImage(ToastImage toastImage) {
            if (toastImage != null && toastImage.getImageId() != -1) {
                toastImageView.setImageResource(toastImage.getImageId());
            }
            return this;
        }

        public ToastBuilder setIsToastMore(boolean is) {
            mIsToastMore = is;
            return this;
        }

        public ToastBuilder setToastBgColor(int color) {
            GradientDrawable gradientDrawable = (GradientDrawable) toastLayout.getBackground();
            gradientDrawable.setColor(mContext.getResources().getColor(color));
            return this;
        }

        public ToastUtils build() {
            return new ToastUtils(this);
        }
    }

    //获取屏幕高度
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        int height = wm.getDefaultDisplay().getHeight();
    //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
//        toastStart.setGravity(Gravity.TOP, 0, height / 3);
//        toastStart.setGravity(Gravity.BOTTOM,0,0);

}
