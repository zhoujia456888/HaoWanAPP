package cn.zhoujia.haowanapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Bean.BeautyBean.TngouEntity;
import cn.zhoujia.haowanapp.R;

/**
 * Created by Zhoujia on 2016/3/12.
 */
public class BeautyAdapter extends XRecyclerView.Adapter<BeautyAdapter.ViewHolder> {
    private Context mcontext;
    List<TngouEntity> tngouEntityList;

    public BeautyAdapter(Context context, List<TngouEntity> tngouEntityList) {
        this.mcontext = context;
        this.tngouEntityList = tngouEntityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_beautymulti, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
       // viewHolder.setIsRecyclable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String imgUrl = "";
        holder.imgBeauty.setTag(imgUrl);
        // 预设一个图片
        holder.imgBeauty.setImageResource(R.drawable.ic_launcher);
        // 通过 tag 来防止图片错位
        if (holder.imgBeauty.getTag() != null && holder.imgBeauty.getTag().equals(imgUrl)) {
            Picasso.with(mcontext).load("http://tnfs.tngou.net/img/" + tngouEntityList.get(position).getImg()).into(holder.imgBeauty);
        }
        holder.txtBeauty.setText(tngouEntityList.get(position).getTitle().toString());
        holder.imgBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri uri = Uri.parse("http://www.tngou.net/tnfs/show/" + tngouEntityList.get(position).getId());
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                mcontext.startActivity(it);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return tngouEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_beauty)
        ImageView imgBeauty;
        @Bind(R.id.txt_beauty)
        TextView txtBeauty;
        @Bind(R.id.ripplelayout)
        MaterialRippleLayout ripplelayout;
        @Bind(R.id.card_view)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
