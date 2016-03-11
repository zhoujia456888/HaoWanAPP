package cn.zhoujia.haowanapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.hybridsquad.android.library.BitmapUtil;

import java.io.File;

import cn.zhoujia.haowanapp.R;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Zhoujia on 2016/1/11.
 */
public class ImageShower extends Activity {
    PhotoView dalog_image;
    String imagestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);
        dalog_image=(PhotoView)findViewById(R.id.dalog_image);
        Intent intent = getIntent();
        imagestr = intent.getStringExtra("imagestr");
        Log.e("imagestr",imagestr);
        File file  = new File(imagestr.split("///")[1]);
        if (!file .exists()) {
            dalog_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            dalog_image.setImageBitmap(BitmapUtil.decodeUriAsBitmap(this, Uri.parse(imagestr)));
        }
    }

}