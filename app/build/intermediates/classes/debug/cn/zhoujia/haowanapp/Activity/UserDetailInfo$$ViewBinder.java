// Generated code from Butter Knife. Do not modify!
package cn.zhoujia.haowanapp.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserDetailInfo$$ViewBinder<T extends cn.zhoujia.haowanapp.Activity.UserDetailInfo> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558589, "field 'personalIcon' and method 'onClick'");
    target.personalIcon = finder.castView(view, 2131558589, "field 'personalIcon'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558591, "field 'personalNickname'");
    target.personalNickname = finder.castView(view, 2131558591, "field 'personalNickname'");
    view = finder.findRequiredView(source, 2131558593, "field 'personalRealname'");
    target.personalRealname = finder.castView(view, 2131558593, "field 'personalRealname'");
    view = finder.findRequiredView(source, 2131558595, "field 'personalSex'");
    target.personalSex = finder.castView(view, 2131558595, "field 'personalSex'");
    view = finder.findRequiredView(source, 2131558599, "field 'personalBirth'");
    target.personalBirth = finder.castView(view, 2131558599, "field 'personalBirth'");
    view = finder.findRequiredView(source, 2131558601, "field 'personalResidcity'");
    target.personalResidcity = finder.castView(view, 2131558601, "field 'personalResidcity'");
    view = finder.findRequiredView(source, 2131558461, "field 'scroll'");
    target.scroll = finder.castView(view, 2131558461, "field 'scroll'");
    view = finder.findRequiredView(source, 2131558590, "field 'layoutPersonalNickname' and method 'onClick'");
    target.layoutPersonalNickname = finder.castView(view, 2131558590, "field 'layoutPersonalNickname'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558592, "field 'layoutPersonalRealname' and method 'onClick'");
    target.layoutPersonalRealname = finder.castView(view, 2131558592, "field 'layoutPersonalRealname'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558594, "field 'layoutPersonalSex' and method 'onClick'");
    target.layoutPersonalSex = finder.castView(view, 2131558594, "field 'layoutPersonalSex'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558598, "field 'layoutPersonalBirth' and method 'onClick'");
    target.layoutPersonalBirth = finder.castView(view, 2131558598, "field 'layoutPersonalBirth'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558600, "field 'layoutPersonalResidcity' and method 'onClick'");
    target.layoutPersonalResidcity = finder.castView(view, 2131558600, "field 'layoutPersonalResidcity'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558597, "field 'personalPhone'");
    target.personalPhone = finder.castView(view, 2131558597, "field 'personalPhone'");
    view = finder.findRequiredView(source, 2131558596, "field 'layoutPersonalPhone' and method 'onClick'");
    target.layoutPersonalPhone = finder.castView(view, 2131558596, "field 'layoutPersonalPhone'");
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
    target.personalIcon = null;
    target.personalNickname = null;
    target.personalRealname = null;
    target.personalSex = null;
    target.personalBirth = null;
    target.personalResidcity = null;
    target.scroll = null;
    target.layoutPersonalNickname = null;
    target.layoutPersonalRealname = null;
    target.layoutPersonalSex = null;
    target.layoutPersonalBirth = null;
    target.layoutPersonalResidcity = null;
    target.personalPhone = null;
    target.layoutPersonalPhone = null;
  }
}
