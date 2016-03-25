// Generated code from Butter Knife. Do not modify!
package cn.zhoujia.haowanapp.Fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class JokeFragment$$ViewBinder<T extends cn.zhoujia.haowanapp.Fragment.JokeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558578, "field 'materialListview'");
    target.materialListview = finder.castView(view, 2131558578, "field 'materialListview'");
  }

  @Override public void unbind(T target) {
    target.materialListview = null;
  }
}
