package cn.zhoujia.haowanapp.Adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.balysv.materialripple.MaterialRippleLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Bean.JokeBean.ShowapiResBodyEntity.ContentlistEntity;
import cn.zhoujia.haowanapp.R;

/**
 * 笑话Adapter
 * Created by Zhoujia on 2016/3/9.
 */
public class JokeAdapter extends XRecyclerView.Adapter<JokeAdapter.ViewHolder> {
    private List<ContentlistEntity> contentlistEntityList;
    private Context mContext;

    public JokeAdapter(Context context, List<ContentlistEntity> contentlistEntityList) {
        this.mContext = context;
        this.contentlistEntityList = contentlistEntityList;

        //    Log.e("JokeAdapter", "contentlistEntityList.size():" + contentlistEntityList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jokecardview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.jokeTitle.setText(contentlistEntityList.get(position).getTitle());
        viewHolder.jokeContent.setText(Html.fromHtml(contentlistEntityList.get(position).getText()));
    }

    @Override
    public int getItemCount() {
        return contentlistEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.joke_title)
        TextView jokeTitle;
        @Bind(R.id.joke_content)
        TextView jokeContent;
        @Bind(R.id.joke_funny)
        TextView jokeFunny;
        @Bind(R.id.joke_bored)
        TextView jokeBored;
        @Bind(R.id.card_view)
        CardView cardView;
        @Bind(R.id.ripplelayout)
        MaterialRippleLayout ripplelayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ripplelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new MaterialDialog.Builder(mContext)
                            .items(R.array.share_joke)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    switch (which) {
                                        case 0:
                                            Intent shareIntent = new Intent();
                                            shareIntent.setAction(Intent.ACTION_SEND);
                                            shareIntent.putExtra(Intent.EXTRA_TEXT, jokeContent.getText().toString());
                                            shareIntent.setType("text/plain");
                                            //设置分享列表的标题，并且每次都显示分享列表
                                            mContext.startActivity(Intent.createChooser(shareIntent, "分享到"));
                                            break;
                                        case 1:
                                            ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                            // 将文本内容放到系统剪贴板里。
                                            cm.setText(jokeContent.getText().toString());
                                            Toast.makeText(mContext, "内容已经复制到剪切板", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                            })
                            .positiveText(R.string.cancel)
                            .show();
                    return false;
                }
            });
        }
    }
}
