package cn.zhoujia.haowanapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.HourlyForecastEntity;
import cn.zhoujia.haowanapp.R;

/**
 * 每3小时天气状况Adapter
 * Created by Zhoujia on 2016/3/10.
 */
public class WeatherHourlyAdapter extends BaseAdapter {

    private Context context;
    List<HourlyForecastEntity> hourlyForecastEntityList;
    LayoutInflater layoutInflater;

    public WeatherHourlyAdapter(Context context, List<HourlyForecastEntity> hourlyForecastEntityList) {
        this.context = context;
        this.hourlyForecastEntityList = hourlyForecastEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return hourlyForecastEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return hourlyForecastEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_hourly_forecast_gridview, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.txtHourlyForecastTime.setText(hourlyForecastEntityList.get(position).getDate().substring(11));
        holder.txtHourlyForecastTmp.setText(hourlyForecastEntityList.get(position).getTmp()+" ℃");
        holder.txtHourlyForecastWinddir.setText(hourlyForecastEntityList.get(position).getWind().getDir());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.txt_hourly_forecast_time)
        TextView txtHourlyForecastTime;
        @Bind(R.id.txt_hourly_forecast_tmp)
        TextView txtHourlyForecastTmp;
        @Bind(R.id.txt_hourly_forecast_winddir)
        TextView txtHourlyForecastWinddir;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
