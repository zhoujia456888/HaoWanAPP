package cn.zhoujia.haowanapp.Bean;

import java.util.List;

/**
 * Created by Zhoujia on 2016/3/8.
 */
public class BeautyBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-03-06 14:11","title":"性感黛欣霓内衣写真","description":"美女写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_4e1ed310e8b3553d266bd619949ec01c3-760x500.jpg","url":"http://m.xxxiao.com/435"},{"ctime":"2016-03-06 14:11","title":"忧郁美妞蕾丝内衣大秀酥胸诱惑","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150711/8-150G1155412214.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51982.html"},{"ctime":"2016-03-06 14:11","title":"美女内衣尽展媚惑仪态写真集","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150708/8-150FQ51205129.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51955.html"},{"ctime":"2016-03-06 14:11","title":"童颜巨乳美女惊艳诱人写真照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150707/8-150FGA62Q22.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/49162.html"},{"ctime":"2016-03-06 14:11","title":"范冰冰高清壁纸图片","description":"美女写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_c319e31fd34fb215d17644f5b58a7734-760x500.jpg","url":"http://m.xxxiao.com/1803"},{"ctime":"2016-03-06 14:11","title":"Beautyleg &#8211; Arvi 私房美腿写真","description":"美女写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/05/m.xxxiao.com_e7e731faf790487ccaf90d11774fae6b-760x500.jpg","url":"http://m.xxxiao.com/1353"},{"ctime":"2016-03-06 14:11","title":"貌美如仙的少女爆乳诱惑写真照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150714/8-150G411430N04.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/49174.html"},{"ctime":"2016-03-06 14:11","title":"清纯可人的少女化身童话精灵","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150716/8-150G6105622400.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51948.html"},{"ctime":"2016-03-06 14:11","title":"大眼睛美女粉嫩露乳可爱自拍照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150716/8-150G6104955258.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51952.html"},{"ctime":"2016-03-06 14:11","title":"柔弱美丽女神性感诱惑纱衣高清图","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150717/9-150GG62410923.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/51941.html"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-03-06 14:11
     * title : 性感黛欣霓内衣写真
     * description : 美女写真
     * picUrl : http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_4e1ed310e8b3553d266bd619949ec01c3-760x500.jpg
     * url : http://m.xxxiao.com/435
     */

    private List<NewslistEntity> newslist;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setNewslist(List<NewslistEntity> newslist) {
        this.newslist = newslist;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<NewslistEntity> getNewslist() {
        return newslist;
    }

    public static class NewslistEntity {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCtime() {
            return ctime;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public String getUrl() {
            return url;
        }
    }
}
