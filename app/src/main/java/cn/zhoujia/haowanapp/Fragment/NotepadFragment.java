package cn.zhoujia.haowanapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.balysv.materialripple.MaterialRippleLayout;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Activity.NotePadActivity;
import cn.zhoujia.haowanapp.R;
import cn.zhoujia.haowanapp.Utils.DataBaseUtil;
import cn.zhoujia.haowanapp.Utils.MapComparator;
import cn.zhoujia.haowanapp.greendao.Notepad;
import freemarker.template.utility.DateUtil;

/**
 * Created by Zhoujia on 2016/3/21.
 */
public class NotepadFragment extends Fragment {
    @Bind(R.id.recyclerview_notepad)
    XRecyclerView recyclerviewNotepad;
    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    private Cursor cursor;
    NotepadAdapter notepadAdapter;
    ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notepad, container, false);

        ButterKnife.bind(this, rootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewNotepad.setLayoutManager(layoutManager);

        recyclerviewNotepad.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerviewNotepad.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerviewNotepad.setArrowImageView(R.mipmap.iconfont_downgrey);
        LoadingListener(recyclerviewNotepad);

        selectNoteData();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                recyclerviewNotepad.refreshComplete();
                selectNoteData();
                notepadAdapter.notifyDataSetChanged();
            }
        }, 1000);

        Log.e("NotePadActivity", "onResume");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static NotepadFragment newInstance(String name) {
        NotepadFragment nFragment = new NotepadFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, name);
        nFragment.setArguments(mBundle);
        return nFragment;
    }

    private void LoadingListener(final XRecyclerView materialListview) {
        materialListview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mData.clear();
                        selectNoteData();
                        notepadAdapter.notifyDataSetChanged();
                        materialListview.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // contentlistEntityList.clear();
                        notepadAdapter.notifyDataSetChanged();
                        materialListview.noMoreLoading();
                    }
                }, 1000);
            }
        });

    }

    public void selectNoteData() {
        mData = DataBaseUtil.selectNotepadAllData();//带条件的查询
        notepadAdapter = new NotepadAdapter(this.getActivity(), mData);
        recyclerviewNotepad.setFocusable(false);
        recyclerviewNotepad.setAdapter(notepadAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.notepadmenu, menu);

        //Select search item
        final MenuItem searchnoteItem = menu.findItem(R.id.menu_searchnote);
        searchnoteItem.setVisible(true);

        SearchView searchView = (SearchView) searchnoteItem.getActionView();
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

        //监控当searchView关闭时调用事件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            MenuItemCompat.setOnActionExpandListener(searchnoteItem,
                    new MenuItemCompat.OnActionExpandListener() {
                        @Override
                        public boolean onMenuItemActionExpand(MenuItem menuItem) {
                            return true;
                        }

                        @Override
                        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                            selectNoteData();
                            Log.e("NotepadFragment", "添加关闭事件");
                            return true;
                        }
                    });
        } else {
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    selectNoteData();
                    Log.e("NotepadFragment", "添加关闭事件");
                    return false;
                }
            });
        }

        menu.findItem(R.id.menu_addnote).setVisible(true);
        mSearchCheck = false;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menu_addnote:
                // Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NotePadActivity.class);
                intent.putExtra("action", "addnotepad");
                startActivity(intent);
                break;
            case R.id.menu_searchnote:
                mSearchCheck = true;
                // Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if (mSearchCheck) {
                SelectDataLike(s);
            }
            return false;
        }
    };

    private void SelectDataLike(String searchtxt) {
        if (searchtxt.length() != 0) {
            cursor = DataBaseUtil.selectDataWhere("NOTEPADCONTENT Like ", "'%" + searchtxt + "%'");
            int columnsSize = cursor.getColumnCount();
            ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
            while (cursor.moveToNext()) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < columnsSize; i++) {
                    map.put("id", cursor.getString(0));
                    map.put("notepadcontent", cursor.getString(1));
                    map.put("notepaddate", cursor.getString(2));
                }
                mData.add(map);
                // 排序
                Collections.sort(mData, new MapComparator());
            }

            notepadAdapter = new NotepadAdapter(this.getActivity(), mData);
            recyclerviewNotepad.setFocusable(false);
            recyclerviewNotepad.setAdapter(notepadAdapter);
        }
    }

    class NotepadAdapter extends XRecyclerView.Adapter<NotepadAdapter.ViewHolder> {
        private Context mContext;
        private ArrayList<Map<String, Object>> notepadAdapterList;

        public NotepadAdapter(Context context, ArrayList<Map<String, Object>> notepadAdapterList) {
            this.mContext = context;
            this.notepadAdapterList = notepadAdapterList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notepadcardview, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.notepadContent.setText(notepadAdapterList.get(position).get("notepadcontent").toString());
            viewHolder.notepadDate.setText(notepadAdapterList.get(position).get("notepaddate").toString().split(" ")[0]);
            viewHolder.ripplelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NotePadActivity.class);
                    intent.putExtra("action", "lookcontene");
                    intent.putExtra("id", notepadAdapterList.get(position).get("id").toString());
                    mContext.startActivity(intent);
                }

            });

            viewHolder.ripplelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new MaterialDialog.Builder(mContext)
                            .title(R.string.delete)
                            .content(R.string.delete_note_info)
                            .positiveText(R.string.ok)
                            .negativeText(R.string.cancel)
                            .onAny(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    switch (which) {
                                        case POSITIVE:
                                            DataBaseUtil.delete("_id=?", new String[]{notepadAdapterList.get(position).get("id").toString()});
                                            selectNoteData();
                                            break;
                                        case NEGATIVE:
                                            Toast.makeText(mContext, "你点击了取消", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                            }).show();
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return notepadAdapterList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.notepad_content)
            TextView notepadContent;
            @Bind(R.id.notepad_date)
            TextView notepadDate;
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
}
