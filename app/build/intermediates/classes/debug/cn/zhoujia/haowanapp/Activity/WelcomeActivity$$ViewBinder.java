// Generated code from Butter Knife. Do not modify!
package cn.zhoujia.haowanapp.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WelcomeActivity$$ViewBinder<T extends cn.zhoujia.haowanapp.Activity.WelcomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558602, "field 'shimmerAppname' and method 'onClick'");
    target.shimmerAppname = finder.castView(view, 2131558602, "field 'shimmerAppname'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558603, "field 'hintWelcome' and method 'onClick'");
    target.hintWelcome = finder.castView(view, 2131558603, "field 'hintWelcome'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.shimmerAppname = null;
    target.hintWelcome = null;
  }
}
