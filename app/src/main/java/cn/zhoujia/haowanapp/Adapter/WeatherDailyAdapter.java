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
import cn.zhoujia.haowanapp.Bean.WeatherBean.HeWeatherdataServiceEntity.DailyForecastEntity;
import cn.zhoujia.haowanapp.R;

/**
 * 七天天气状况Adapter
 * Created by Zhoujia on 2016/3/10.
 */
public class WeatherDailyAdapter extends BaseAdapter {

    private Context mcontext;
    List<DailyForecastEntity> dailyForecastEntityList;
    LayoutInflater layoutInflater;

    public WeatherDailyAdapter(Context context, List<DailyForecastEntity> dailyForecastEntityList) {
        this.mcontext = context;
        this.dailyForecastEntityList = dailyForecastEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dailyForecastEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return dailyForecastEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_daily_forecast_gridview, null);
            new ViewHolder(convertView);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.txtDailyForecastDate.setText(dailyForecastEntityList.get(position).getDate());
        holder.txtHourlyForecastTmp.setText(dailyForecastEntityList.get(position).getTmp().getMax()+" ℃—"+dailyForecastEntityList.get(position).getTmp().getMin()+"℃");
        holder.txtHourlyForecastTxtD.setText(dailyForecastEntityList.get(position).getCond().getTxt_d());
        holder.txtHourlyForecastWinddir.setText(dailyForecastEntityList.get(position).getWind().getDir());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.txt_daily_forecast_date)
        TextView txtDailyForecastDate;
        @Bind(R.id.txt_hourly_forecast_txt_d)
        TextView txtHourlyForecastTxtD;
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
