package cn.zhoujia.haowanapp.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zhoujia.haowanapp.Activity.BaseActivity;
import cn.zhoujia.haowanapp.Activity.MainActivity;
import cn.zhoujia.haowanapp.Adapter.WeatherDailyAdapter;
import cn.zhoujia.haowanapp.Adapter.WeatherHourlyAdapter;
import cn.zhoujia.haowanapp.Bean.WeatherBean;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.AqiEntity;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.BasicEntity;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.DailyForecastEntity;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.HourlyForecastEntity;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.NowEntity;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.SuggestionEntity;
import cn.zhoujia.haowanapp.MyApplication;
import cn.zhoujia.haowanapp.R;
import cn.zhoujia.haowanapp.Service.LocationService;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 天气界面
 */
public class MainFragment extends Fragment {

    @Bind(R.id.weather_scrollview)
    ScrollView weatherScrollview;
    @Bind(R.id.rotate_header_web_view_frame)
    PtrClassicFrameLayout rotateHeaderWebViewFrame;
    @Bind(R.id.img_weathr)
    ImageView imgWeathr;
    @Bind(R.id.txt_weather_city)
    TextView txtWeatherCity;
    @Bind(R.id.weather_deta)
    TextView weatherDeta;
    @Bind(R.id.weather_temperature)
    TextView weatherTemperature;
    @Bind(R.id.txt_aqiqlty)
    TextView txtAqiqlty;
    @Bind(R.id.layout_aqi)
    LinearLayout layoutAqi;
    @Bind(R.id.txt_aqipm25)
    TextView txtAqipm25;
    @Bind(R.id.txt_send_temp)
    TextView txtSendTemp;
    @Bind(R.id.txt_humidity)
    TextView txtHumidity;
    @Bind(R.id.txt_precipitation)
    TextView txtPrecipitation;
    @Bind(R.id.txt_pressure)
    TextView txtPressure;
    @Bind(R.id.txt_seeing)
    TextView txtSeeing;
    @Bind(R.id.txt_wind_direction)
    TextView txtWindDirection;
    @Bind(R.id.txt_wind_power)
    TextView txtWindPower;
    @Bind(R.id.txt_wind_speed)
    TextView txtWindSpeed;
    @Bind(R.id.grid_hourly_forecast)
    GridView gridHourlyForecast;
    @Bind(R.id.grid_daily_forecast)
    GridView gridDailyForecast;
    @Bind(R.id.txt_comfbrf)
    TextView txtComfbrf;
    @Bind(R.id.txt_comftxt)
    TextView txtComftxt;
    @Bind(R.id.txt_cwbrf)
    TextView txtCwbrf;
    @Bind(R.id.txt_cwtxt)
    TextView txtCwtxt;
    @Bind(R.id.txt_drsgbrf)
    TextView txtDrsgbrf;
    @Bind(R.id.txt_drsgtxt)
    TextView txtDrsgtxt;
    @Bind(R.id.txt_sportbrf)
    TextView txtSportbrf;
    @Bind(R.id.txt_sporttxt)
    TextView txtSporttxt;
    @Bind(R.id.txt_flubrf)
    TextView txtFlubrf;
    @Bind(R.id.txt_flutxt)
    TextView txtFlutxt;
    @Bind(R.id.txt_travbrf)
    TextView txtTravbrf;
    @Bind(R.id.txt_travtxt)
    TextView txtTravtxt;
    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    private LocationService locationService;

    WeatherBean weatherBean;
    String status;
    HeWeatherdataServiceEntity weatherdataServiceEntity;
    AqiEntity aqiEntity;
    BasicEntity basicEntity;
    NowEntity nowEntity;
    SuggestionEntity suggestionEntity;
    List<DailyForecastEntity> dailyForecastEntityList;
    List<HourlyForecastEntity> hourlyForecastEntityList;
    WeatherDailyAdapter weatherDailyAdapter;
    WeatherHourlyAdapter weatherHourlyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationService = ((MyApplication) this.getActivity().getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = this.getActivity().getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this, rootView);

        Log.e("MainFragment", "onCreateView");

        //下拉刷新
        rotateHeaderWebViewFrame.setLastUpdateTimeRelateObject(this);
        rotateHeaderWebViewFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, weatherScrollview, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                rotateHeaderWebViewFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rotateHeaderWebViewFrame.refreshComplete();
                        //此处添加下拉刷新事件
                        locationService.start();
                        Log.e("MainFragment", "rotateHeaderWebViewFrame.refreshComplete();");
                    }
                }, 100);
            }
        });

        // the following are default settings
        rotateHeaderWebViewFrame.setResistance(1.7f);
        rotateHeaderWebViewFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        rotateHeaderWebViewFrame.setDurationToClose(200);
        rotateHeaderWebViewFrame.setDurationToCloseHeader(1000);
        // default is false
        rotateHeaderWebViewFrame.setPullToRefresh(false);
        // default is true
        rotateHeaderWebViewFrame.setKeepHeaderWhenRefresh(true);
        rotateHeaderWebViewFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                rotateHeaderWebViewFrame.autoRefresh();
                Log.e("MainFragment", "rotateHeaderWebViewFrame.autoRefresh();");
            }
        }, 100);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        locationService.start();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationService.unregisterListener(mListener); //注销掉定位监听
        locationService.stop(); //停止定位服务
    }

    public static MainFragment newInstance(String text) {
        MainFragment mFragment = new MainFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        //Select search item
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setVisible(true);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

        //监控当searchView关闭时调用事件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            MenuItemCompat.setOnActionExpandListener(menuItem,
                    new MenuItemCompat.OnActionExpandListener() {
                        @Override
                        public boolean onMenuItemActionExpand(MenuItem menuItem) {
                            return true;
                        }

                        @Override
                        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
//                            locationService.start();
                            rotateHeaderWebViewFrame.autoRefresh();
                            return true;
                        }
                    });
        } else {
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    rotateHeaderWebViewFrame.autoRefresh();
//                    locationService.start();;
                    return false;
                }
            });
        }
        mSearchCheck = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.menu_search:
                mSearchCheck = true;
                Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            //点击键盘的搜索之后才进行搜索
            getweather(s);
            // Toast.makeText(getContext(), "什么都没有搜到，哈哈", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if (mSearchCheck) {
                //输入的同时会进行搜索
            }
            return false;
        }

    };

    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub

            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                locationService.stop();//停止定位
                //根据定位城市获取天气
                getweather(location.getCity().split("市")[0]);
                Log.e("MainFragment", location.getCity());
            }
        }
    };

    //根据定位城市获取天气 调用百度APIStoreSDK
    private void getweather(String city) {
        // locationService.stop();//停止定位
        Parameters para = new Parameters();
        para.put("city", city);
        ApiStoreSDK.execute("http://apis.baidu.com/heweather/weather/free",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess");
                        // logMsg(responseString);
                        //解析返回的数据
                        try {
                            AnalyzeData(responseString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("responseString", responseString);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("sdkdemo", "onComplete");
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {
                        Log.i("sdkdemo", "onError, status: " + status);
                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
                        logMsg(getStackTrace(e));
                    }
                });

    }

    private void AnalyzeData(String responseString) throws JSONException {
        Gson gson = new Gson();
        weatherBean = gson.fromJson(responseString, WeatherBean.class);
        weatherdataServiceEntity = weatherBean.getHeWeatherdataService().get(0);
        status = weatherdataServiceEntity.getStatus();
        if (status.equals("ok")) {
            aqiEntity = weatherdataServiceEntity.getAqi();
            basicEntity = weatherdataServiceEntity.getBasic();
            nowEntity = weatherdataServiceEntity.getNow();
            suggestionEntity = weatherdataServiceEntity.getSuggestion();

            txtWeatherCity.setText(basicEntity.getCity().toString());
            ImageLoader.getInstance().displayImage("http://files.heweather.com/cond_icon/" + nowEntity.getCond().getCode() + ".png", imgWeathr, BaseActivity.displayImageOptions());
            weatherDeta.setText(nowEntity.getCond().getTxt());
            weatherTemperature.setText(nowEntity.getTmp() + " ℃");
            txtAqiqlty.setText(aqiEntity.getCity().getQlty());
            txtAqipm25.setText(aqiEntity.getCity().getPm25() + " ug/m3");
            txtSendTemp.setText(nowEntity.getFl() + " ℃");
            txtHumidity.setText(nowEntity.getHum() + " %");
            txtPrecipitation.setText(nowEntity.getPcpn() + " mm");
            txtPressure.setText(nowEntity.getPres());
            txtSeeing.setText(nowEntity.getVis() + " km");
            txtWindDirection.setText(nowEntity.getWind().getDir());
            txtWindPower.setText(nowEntity.getWind().getSc() + " 级");
            txtWindSpeed.setText(nowEntity.getWind().getSpd() + " km/h");
            txtComfbrf.setText(suggestionEntity.getComf().getBrf());
            txtComftxt.setText(suggestionEntity.getComf().getTxt());
            txtCwbrf.setText(suggestionEntity.getCw().getBrf());
            txtCwtxt.setText(suggestionEntity.getCw().getTxt());
            txtDrsgbrf.setText(suggestionEntity.getDrsg().getBrf());
            txtDrsgtxt.setText(suggestionEntity.getDrsg().getTxt());
            txtSportbrf.setText(suggestionEntity.getSport().getBrf());
            txtSporttxt.setText(suggestionEntity.getSport().getTxt());
            txtFlubrf.setText(suggestionEntity.getFlu().getBrf());
            txtFlutxt.setText(suggestionEntity.getFlu().getTxt());
            txtTravbrf.setText(suggestionEntity.getTrav().getBrf());
            txtTravtxt.setText(suggestionEntity.getTrav().getTxt());

            hourlyForecastEntityList = weatherdataServiceEntity.getHourly_forecast();
            setGridHourlyForecast(hourlyForecastEntityList);


            dailyForecastEntityList = weatherdataServiceEntity.getDaily_forecast();
            setGridDailyForecast(dailyForecastEntityList);


        } else {
            Toast.makeText(this.getActivity(), "尚未查询到当前城市的天气", Toast.LENGTH_SHORT).show();
        }
    }

    private void setGridHourlyForecast(List<HourlyForecastEntity> hourlyForecastEntityList) {
        int size = hourlyForecastEntityList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        MainActivity.windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridHourlyForecast.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridHourlyForecast.setColumnWidth(itemWidth); // 设置列表项宽
        gridHourlyForecast.setHorizontalSpacing(5); // 设置列表项水平间距
        gridHourlyForecast.setStretchMode(GridView.NO_STRETCH);
        gridHourlyForecast.setNumColumns(size); // 设置列数量=列表集合数

        weatherHourlyAdapter = new WeatherHourlyAdapter(MainActivity.activity, hourlyForecastEntityList);
        gridHourlyForecast.setAdapter(weatherHourlyAdapter);
    }

    public void setGridDailyForecast(List<DailyForecastEntity> dailyForecastEntityList) {
        int size = dailyForecastEntityList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        MainActivity.windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridDailyForecast.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridDailyForecast.setColumnWidth(itemWidth); // 设置列表项宽
        gridDailyForecast.setHorizontalSpacing(5); // 设置列表项水平间距
        gridDailyForecast.setStretchMode(GridView.NO_STRETCH);
        gridDailyForecast.setNumColumns(size); // 设置列数量=列表集合数

        weatherDailyAdapter = new WeatherDailyAdapter(MainActivity.activity, dailyForecastEntityList);
        gridDailyForecast.setAdapter(weatherDailyAdapter);
    }

    public void logMsg(String str) {

        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();

    }

    String getStackTrace(Throwable e) {
        if (e == null) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        str.append(e.getMessage()).append("\n");
        for (int i = 0; i < e.getStackTrace().length; i++) {
            str.append(e.getStackTrace()[i]).append("\n");
        }
        return str.toString();
    }

    @OnClick({R.id.layout_aqi, R.id.txt_aqipm25})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_aqi:
                break;
            case R.id.txt_aqipm25:
                break;
        }
    }

}
