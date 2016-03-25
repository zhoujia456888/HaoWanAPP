// Generated code from Butter Knife. Do not modify!
package cn.zhoujia.haowanapp.Adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WeatherDailyAdapter$ViewHolder$$ViewBinder<T extends cn.zhoujia.haowanapp.Adapter.WeatherDailyAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558622, "field 'txtDailyForecastDate'");
    target.txtDailyForecastDate = finder.castView(view, 2131558622, "field 'txtDailyForecastDate'");
    view = finder.findRequiredView(source, 2131558623, "field 'txtHourlyForecastTxtD'");
    target.txtHourlyForecastTxtD = finder.castView(view, 2131558623, "field 'txtHourlyForecastTxtD'");
    view = finder.findRequiredView(source, 2131558624, "field 'txtHourlyForecastTmp'");
    target.txtHourlyForecastTmp = finder.castView(view, 2131558624, "field 'txtHourlyForecastTmp'");
    view = finder.findRequiredView(source, 2131558625, "field 'txtHourlyForecastWinddir'");
    target.txtHourlyForecastWinddir = finder.castView(view, 2131558625, "field 'txtHourlyForecastWinddir'");
  }

  @Override public void unbind(T target) {
    target.txtDailyForecastDate = null;
    target.txtHourlyForecastTxtD = null;
    target.txtHourlyForecastTmp = null;
    target.txtHourlyForecastWinddir = null;
  }
}
