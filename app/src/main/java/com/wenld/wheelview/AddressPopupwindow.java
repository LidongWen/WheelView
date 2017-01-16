package com.wenld.wheelview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


/**
 * Created by wenld on 2016/6/24.
 */
public class AddressPopupwindow extends PopupWindow {
    View view;
    Context paramContext;
    CitySelectedListener listener;
    private CityPickerLayout mCityPickerLayout;
    private TextView mShowSelectedBtn;

    public AddressPopupwindow(Context paramContext, CitySelectedListener lis) {
        this.view = ((LayoutInflater) paramContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.popupwindow_view, null);
        this.paramContext = paramContext;
        this.listener = lis;
        mCityPickerLayout = (CityPickerLayout) view.findViewById(R.id.city_picker);
        mShowSelectedBtn = (TextView) view.findViewById(R.id.show_select_btn);
        mShowSelectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCitySelectedListener(mCityPickerLayout.getProvince(), mCityPickerLayout.getCity());
                }
                dismiss();
//                mSelectedTv.setText(new StringBuffer().append(mCityPickerLayout.getProvince())
//                        .append("  ").append(mCityPickerLayout.getCity()));
            }
        });
        setContentView(this.view);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.popUpwindow);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E9E9E9")));
    }

    public void update(String province, String city) {
        mCityPickerLayout.update(province, city);
    }

    @Override
    public void dismiss() {
        backgroundAlpha(1f);
        super.dismiss();
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams params = ((Activity) paramContext).getWindow().getAttributes();
        params.alpha = bgAlpha;
        ((Activity) paramContext).getWindow().setAttributes(params);
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        backgroundAlpha(0.7f);
    }

    public interface CitySelectedListener {
        void onCitySelectedListener(String province, String city);
    }
}
