package cn.zhoujia.haowanapp.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zhoujia.haowanapp.MyApplication;
import cn.zhoujia.haowanapp.R;
import cn.zhoujia.haowanapp.Utils.DataBaseUtil;
import cn.zhoujia.haowanapp.greendao.Notepad;

/**
 * Created by Zhoujia on 2016/3/21.
 */
public class NotePadActivity extends BaseActivity {
    @Bind(R.id.edit_notepadcontent)
    EditText editNotepadcontent;
    String content;
    @Bind(R.id.layout_notepadcont)
    LinearLayout layoutNotepadcont;
    @Bind(R.id.toolbar3)
    Toolbar toolbar3;
    private NotePadActivity activity = NotePadActivity.this;
    String action, id, notecontent, notedate;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepadcontext);
        ButterKnife.bind(this);
        //设置toolbar标题为空
        toolbar3.setTitle(getString(R.string.notepad_page));
        setSupportActionBar(toolbar3);
        //变色状态栏
        BaseActivity.ColourStatusBar(activity);
        //返回按键
        toolbar3.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });


        Intent intent = this.getIntent();
        action = intent.getStringExtra("action");
        id = intent.getStringExtra("id");
        if (action.equals("lookcontene")) {
            //查看notepad内容
            editNotepadcontent.setFocusable(true);
            editNotepadcontent.setFocusableInTouchMode(true);
            editNotepadcontent.requestFocus();
            editNotepadcontent.findFocus();
            InputMethodManager imm = (InputMethodManager) editNotepadcontent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            seleteNote(id);

            content = editNotepadcontent.getText().toString();
            editNotepadcontent.setSelection(content.length());//将光标移至文字末尾


        } else if (action.equals("addnotepad")) {
            //添加新的notepad
            editNotepadcontent.setFocusable(true);
            editNotepadcontent.setFocusableInTouchMode(true);
            editNotepadcontent.requestFocus();
            editNotepadcontent.findFocus();
            InputMethodManager imm = (InputMethodManager) editNotepadcontent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());
        if (action.equals("lookcontene")) {
            ContentValues updateValues = new ContentValues();
            updateValues.put("notepadcontent", editNotepadcontent.getText().toString());
            updateValues.put("notepaddate", date);
            DataBaseUtil.update(updateValues, "_id=?", new String[]{id});
        } else if (action.equals("addnotepad") && editNotepadcontent.getText().length() != 0) {
            Notepad notepad = new Notepad();
            notepad.setNotepadcontent(editNotepadcontent.getText().toString());
            notepad.setNotepaddate(date);
            DataBaseUtil.insert(notepad);
        }
        Log.e("NotePadActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.list.remove(this);
        Log.e("NotePadActivity", "onDestroy");
    }


    private void seleteNote(String id) {
        cursor = DataBaseUtil.selectDataWhere("_id=", id);
        int columnsSize = cursor.getColumnCount();
        Log.e("NotePadActivity", "columnsSize:" + columnsSize);
        while (cursor.moveToNext()) {
            for (int i = 0; i < columnsSize; i++) {
                notecontent = cursor.getString(1);
                notedate = cursor.getString(2);
            }
        }
        toolbar3.setTitle(notedate.split(" ")[0]);
        toolbar3.setSubtitle(notedate.split(" ")[1]);
        editNotepadcontent.setText(notecontent);
    }

    @OnClick({R.id.edit_notepadcontent, R.id.layout_notepadcont})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_notepadcont:
                editNotepadcontent.setFocusable(true);
                editNotepadcontent.setFocusableInTouchMode(true);
                editNotepadcontent.requestFocus();
                editNotepadcontent.findFocus();
                InputMethodManager imm = (InputMethodManager) editNotepadcontent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                break;
        }
    }
}
