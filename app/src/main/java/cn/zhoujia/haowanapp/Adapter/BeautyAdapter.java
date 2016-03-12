package cn.zhoujia.haowanapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Bean.BeautyBean.NewslistEntity;
import cn.zhoujia.haowanapp.R;

/**
 * Created by Zhoujia on 2016/3/12.
 */
public class BeautyAdapter extends BaseAdapter {

    private Context mcontext;
    LayoutInflater layoutInflater;
    List<NewslistEntity> newslistEntityList;

    public BeautyAdapter(Context context, List<NewslistEntity> newslistEntityList) {
        this.mcontext = context;
        this.newslistEntityList = newslistEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return newslistEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return newslistEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_beautymulti, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Picasso.with(mcontext).load(newslistEntityList.get(position).getPicUrl()).into(holder.imgBeauty);

        holder.txtBeauty.setText(newslistEntityList.get(position).getTitle().toString());
        holder.imgBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri uri = Uri.parse(newslistEntityList.get(position).getUrl());
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                mcontext.startActivity(it);
            }
        });
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.img_beauty)
        ImageView imgBeauty;
        @Bind(R.id.txt_beauty)
        TextView txtBeauty;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
