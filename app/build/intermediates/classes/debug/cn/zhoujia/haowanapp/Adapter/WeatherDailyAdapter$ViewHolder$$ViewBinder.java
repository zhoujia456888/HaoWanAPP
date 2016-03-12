// Generated code from Butter Knife. Do not modify!
package cn.zhoujia.haowanapp.Adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WeatherDailyAdapter$ViewHolder$$ViewBinder<T extends cn.zhoujia.haowanapp.Adapter.WeatherDailyAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558654, "field 'txtDailyForecastDate'");
    target.txtDailyForecastDate = finder.castView(view, 2131558654, "field 'txtDailyForecastDate'");
    view = finder.findRequiredView(source, 2131558655, "field 'txtHourlyForecastTxtD'");
    target.txtHourlyForecastTxtD = finder.castView(view, 2131558655, "field 'txtHourlyForecastTxtD'");
    view = finder.findRequiredView(source, 2131558656, "field 'txtHourlyForecastTmp'");
    target.txtHourlyForecastTmp = finder.castView(view, 2131558656, "field 'txtHourlyForecastTmp'");
    view = finder.findRequiredView(source, 2131558657, "field 'txtHourlyForecastWinddir'");
    target.txtHourlyForecastWinddir = finder.castView(view, 2131558657, "field 'txtHourlyForecastWinddir'");
  }

  @Override public void unbind(T target) {
    target.txtDailyForecastDate = null;
    target.txtHourlyForecastTxtD = null;
    target.txtHourlyForecastTmp = null;
    target.txtHourlyForecastWinddir = null;
  }
}
