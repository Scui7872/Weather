package com.example.activity.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCity,tvWeather,tvTemp,tvWind,tvPm;
    private ImageView ivIcon;
    private List<WeatherInfo> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoList = JsonParse.getInstance().getInfosFromJson(MainActivity.this);
        initView();
        getCityData("北京");
    }

    private void initView() {
        tvCity = findViewById(R.id.tv_city);
        tvWeather = findViewById(R.id.tv_weather);
        tvTemp = findViewById(R.id.tv_temp);
        tvWind = findViewById(R.id.tv_wind);
        tvPm = findViewById(R.id.tv_pm);
        ivIcon = findViewById(R.id.iv_icon);
        findViewById(R.id.btn_bj).setOnClickListener(this);
        findViewById(R.id.btn_sh).setOnClickListener(this);
        findViewById(R.id.btn_gz).setOnClickListener(this);
        findViewById(R.id.btn_zj).setOnClickListener(this);
    }

    private void setData(WeatherInfo info) {
        if(info==null)
            return;
        tvCity.setText(info.getCity());
        tvWeather.setText(info.getWeather());
        tvTemp.setText(info.getTemp());
        tvWind.setText("风力:" + info.getWind());
        tvPm.setText("PM:" + info.getPm());
        if(("晴转多云".equals(info.getWeather()))) {
            ivIcon.setImageResource(R.drawable.cloud_sun);
        } else if(("多云".equals(info.getWeather()))) {
            ivIcon.setImageResource(R.drawable.clouds);
        } else if(("晴".equals(info.getWeather()))) {
            ivIcon.setImageResource(R.drawable.sun);
        }
    }

    private void getCityData(String city) {
        for(WeatherInfo info : infoList) {
            if(info.getCity().equals(city)) {
                setData(info);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bj:
                getCityData("北京");
                break;
            case R.id.btn_sh:
                getCityData("上海");
                break;
            case R.id.btn_gz:
                getCityData("广州");
                break;
            case R.id.btn_zj:
                getCityData("浙江");
                break;
        }

    }
}