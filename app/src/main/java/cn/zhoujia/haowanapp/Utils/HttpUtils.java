package cn.zhoujia.haowanapp.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zhoujia on 2016/3/5.
 */
public class HttpUtils {
    //定位json获取！
        public static String getJsonContent(String path) throws IOException {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inStream = conn.getInputStream();
            while ((len = inStream.read(data)) != -1) {
                outStream.write(data, 0, len);
            }
            inStream.close();
            return new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
        }


}
