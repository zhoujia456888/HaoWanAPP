// Generated code from Butter Knife. Do not modify!
package cn.zhoujia.haowanapp.Adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class JokeAdapter$ViewHolder$$ViewBinder<T extends cn.zhoujia.haowanapp.Adapter.JokeAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558630, "field 'jokeTitle'");
    target.jokeTitle = finder.castView(view, 2131558630, "field 'jokeTitle'");
    view = finder.findRequiredView(source, 2131558631, "field 'jokeContent'");
    target.jokeContent = finder.castView(view, 2131558631, "field 'jokeContent'");
    view = finder.findRequiredView(source, 2131558632, "field 'jokeFunny'");
    target.jokeFunny = finder.castView(view, 2131558632, "field 'jokeFunny'");
    view = finder.findRequiredView(source, 2131558633, "field 'jokeBored'");
    target.jokeBored = finder.castView(view, 2131558633, "field 'jokeBored'");
    view = finder.findRequiredView(source, 2131558620, "field 'cardView'");
    target.cardView = finder.castView(view, 2131558620, "field 'cardView'");
    view = finder.findRequiredView(source, 2131558621, "field 'ripplelayout'");
    target.ripplelayout = finder.castView(view, 2131558621, "field 'ripplelayout'");
  }

  @Override public void unbind(T target) {
    target.jokeTitle = null;
    target.jokeContent = null;
    target.jokeFunny = null;
    target.jokeBored = null;
    target.cardView = null;
    target.ripplelayout = null;
  }
}
