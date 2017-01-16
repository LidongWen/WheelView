package com.wenld.wheelview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    AddressPopupwindow popupwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow == null) {
                    popupwindow = new AddressPopupwindow(MainActivity.this, citySelectedListener);
                }
//                popupwindow.update(tvProvince.getText().toString(), tvCity.getText().toString());
                popupwindow.showAtLocation(findViewById(R.id.activity_main), Gravity.BOTTOM, 0, 0);
            }
        });
    }

    private AddressPopupwindow.CitySelectedListener citySelectedListener = new AddressPopupwindow.CitySelectedListener() {
        @Override
        public void onCitySelectedListener(String province, String city) {
//            tvProvince.setText(province);
//            tvCity.setText(city);
        }
    };
}
