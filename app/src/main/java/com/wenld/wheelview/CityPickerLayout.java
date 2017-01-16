package com.wenld.wheelview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.wenld.wheel.WheelView;

import java.util.ArrayList;

/**
 * Created by wenld on 2016/6/25.
 */
public class CityPickerLayout extends LinearLayout {

    private WheelView mProvincePicker;
    private WheelView mCityPicker;

    private int mCurrProvinceIndex = -1;
    private int mCurrCityIndex = -1;

    private AreaDataUtil mAreaDataUtil;
    private ArrayList<String> mProvinceList = new ArrayList<>();

    public CityPickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAreaInfo();
    }

    public CityPickerLayout(Context context) {
        this(context, null);
    }

    private void getAreaInfo() {
        mAreaDataUtil = new AreaDataUtil(getContext());
        mProvinceList = mAreaDataUtil.getProvinces();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.layout_city_picker, this);

        mProvincePicker = (WheelView) findViewById(R.id.province_wv);
        mCityPicker = (WheelView) findViewById(R.id.city_wv);
//
        mProvincePicker.setData(mProvinceList);
        mProvincePicker.setDefault(0);
//
        String defaultProvince = mProvinceList.get(0);
        mCityPicker.setData(mAreaDataUtil.getCityByProvince(defaultProvince));
        mCityPicker.setDefault(1);
//
        mProvincePicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                if (mCurrProvinceIndex != id) {
                    mCurrProvinceIndex = id;
                    String selectProvince = mProvincePicker.getSelectedText();
                    if (selectProvince == null || selectProvince.equals(""))
                        return;

                    // get city names by province
                    ArrayList<String> city = mAreaDataUtil.getCityByProvince(mProvinceList.get(id));
                    if (city.size() == 0) {
                        return;
                    }

                    mCityPicker.setData(city);

                    if (city.size() > 1) {
                        //if city is more than one,show start index == 1
                        mCityPicker.setDefault(1);
                    } else {
                        mCityPicker.setDefault(0);
                    }
                }

            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        mCityPicker.setOnSelectListener(new WheelView.OnSelectListener() {

            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                if (mCurrCityIndex != id) {
                    mCurrCityIndex = id;
                    String selectCity = mCityPicker.getSelectedText();
                    if (selectCity == null || selectCity.equals(""))
                        return;
                    int lastIndex = Integer.valueOf(mCityPicker.getListSize());
                    if (id > lastIndex) {
                        mCityPicker.setDefault(lastIndex - 1);
                    }
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }

    public String getProvince() {
        if (mProvincePicker == null) {
            return null;
        }
        return mProvincePicker.getSelectedText();
    }

    public String getCity() {
        if (mCityPicker == null) {
            return null;
        }
        return mCityPicker.getSelectedText();
    }

    public void update(String province, String city) {
        for (int i = 0; i < mProvinceList.size(); i++) {
            if (province.equals(mProvinceList.get(i))) {
                mCurrProvinceIndex = i;
                mProvincePicker.setDefault(mCurrProvinceIndex);
                ArrayList<String> citys = mAreaDataUtil.getCityByProvince(mProvinceList.get(mCurrProvinceIndex));
                if (citys.size() == 0) {
                    return;
                }
                mProvincePicker.setDefault(mCurrProvinceIndex);
                for (int j = 0; j < citys.size(); j++) {
                    if (citys.get(j).equals(city)) {
                        mCityPicker.setData(citys);
                        mCityPicker.setDefault(j);
                        return;
                    }
                }
                break;
            }
        }
    }
}