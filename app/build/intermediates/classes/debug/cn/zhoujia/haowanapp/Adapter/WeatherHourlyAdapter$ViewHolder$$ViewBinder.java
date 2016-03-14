// Generated code from Butter Knife. Do not modify!
package cn.zhoujia.haowanapp.Adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WeatherHourlyAdapter$ViewHolder$$ViewBinder<T extends cn.zhoujia.haowanapp.Adapter.WeatherHourlyAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558619, "field 'txtHourlyForecastTime'");
    target.txtHourlyForecastTime = finder.castView(view, 2131558619, "field 'txtHourlyForecastTime'");
    view = finder.findRequiredView(source, 2131558617, "field 'txtHourlyForecastTmp'");
    target.txtHourlyForecastTmp = finder.castView(view, 2131558617, "field 'txtHourlyForecastTmp'");
    view = finder.findRequiredView(source, 2131558618, "field 'txtHourlyForecastWinddir'");
    target.txtHourlyForecastWinddir = finder.castView(view, 2131558618, "field 'txtHourlyForecastWinddir'");
  }

  @Override public void unbind(T target) {
    target.txtHourlyForecastTime = null;
    target.txtHourlyForecastTmp = null;
    target.txtHourlyForecastWinddir = null;
  }
}
