package cn.zhoujia.haowanapp.Bean;

import java.util.List;

/**
 * Created by Zhoujia on 2016/3/8.
 */
public class BeautyBean {
    /**
     * status : true
     * total : 225
     * tngou : [{"count":1294,"fcount":0,"galleryclass":1,"id":9,"img":"/ext/150714/b32d42d218ebcdb85d39e93ffff390c3.jpg","rcount":0,"size":10,"time":1436876053000,"title":"办公室女郎的丝袜诱惑"},{"count":2646,"fcount":0,"galleryclass":1,"id":10,"img":"/ext/150714/832903f1079ad2a74867e5cbd9dcf1a2.jpg","rcount":0,"size":12,"time":1436876134000,"title":"美腿丝袜美女"},{"count":1027,"fcount":0,"galleryclass":1,"id":12,"img":"/ext/150714/e4f3e179bf9be9845329a5e46a337c5b.jpg","rcount":0,"size":10,"time":1436876387000,"title":"嫩模高清私房性感寫真"},{"count":1020,"fcount":0,"galleryclass":1,"id":16,"img":"/ext/150714/cf37a74a0b2b167e0d5d1bd7f429de8f.jpg","rcount":0,"size":8,"time":1436878222000,"title":"丰满少妇的性感内衣抠逼诱惑"},{"count":1398,"fcount":0,"galleryclass":1,"id":18,"img":"/ext/150714/e76407c9a23da57a0f30690aa7917f3e.jpg","rcount":0,"size":6,"time":1436878500000,"title":"MiStar苏小曼姿势性感诱人私房照"},{"count":422,"fcount":0,"galleryclass":1,"id":29,"img":"/ext/150717/aa321f513adc4458dfd8a46e29ab8a9b.jpg","rcount":0,"size":12,"time":1437143724000,"title":"性感妖艳的女秘书"},{"count":379,"fcount":0,"galleryclass":1,"id":33,"img":"/ext/150719/eb9762ca0c96b2a70772b3abe2735ad2.jpg","rcount":0,"size":16,"time":1437270130000,"title":"性感美腿私房诱惑"},{"count":426,"fcount":0,"galleryclass":1,"id":36,"img":"/ext/150719/e83c7545fd7f2a3a70ed835c8f5173dd.jpg","rcount":0,"size":23,"time":1437271886000,"title":"长腿美女的诱惑"},{"count":374,"fcount":0,"galleryclass":1,"id":39,"img":"/ext/150719/c035c44b0efbe2735899b88ad669c66e.jpg","rcount":0,"size":10,"time":1437273457000,"title":"不只是吸引- 刘颖君白丝魅惑"},{"count":357,"fcount":0,"galleryclass":1,"id":45,"img":"/ext/150719/0359141b049f9f70da9c9e6e562c24c2.jpg","rcount":0,"size":14,"time":1437313304000,"title":"嫩模杨依黑丝美腿"},{"count":353,"fcount":0,"galleryclass":1,"id":50,"img":"/ext/150719/f821bc2cb8e0c09a7478be57dae4ed66.jpg","rcount":0,"size":9,"time":1437315349000,"title":"吊带黑丝极致诱惑"},{"count":356,"fcount":0,"galleryclass":1,"id":53,"img":"/ext/150719/31176d4a408a03b06aabe58384833b5f.jpg","rcount":0,"size":18,"time":1437316270000,"title":"黑人丝袜美女诱惑"},{"count":624,"fcount":0,"galleryclass":1,"id":58,"img":"/ext/150720/6f68aa333f191fb82fc3399f037c6dc8.jpg","rcount":0,"size":20,"time":1437396224000,"title":"黑色蕾丝漂亮美女"},{"count":427,"fcount":0,"galleryclass":1,"id":60,"img":"/ext/150720/100861ca19af5d886b0a9b87428ac1cd.jpg","rcount":0,"size":8,"time":1437396658000,"title":"白嫩玉体魅影写真"},{"count":395,"fcount":0,"galleryclass":1,"id":61,"img":"/ext/150720/07dda37c6fcf84629fa5edac3e00a96c.jpg","rcount":0,"size":11,"time":1437396867000,"title":"网袜撩人姿态实在是诱惑"},{"count":341,"fcount":0,"galleryclass":1,"id":62,"img":"/ext/150720/b750b595082b9860bedf4bc7e7897c00.jpg","rcount":0,"size":22,"time":1437397204000,"title":"顶级美腿模特的性感高跟"},{"count":311,"fcount":0,"galleryclass":1,"id":63,"img":"/ext/150720/e4f3e179bf9be9845329a5e46a337c5b.jpg","rcount":0,"size":10,"time":1437397357000,"title":"嫩模高清私房性感寫真"},{"count":309,"fcount":0,"galleryclass":1,"id":69,"img":"/ext/150721/cf066d02e97e5fbcd2ad27b0c6dd95a4.jpg","rcount":0,"size":16,"time":1437477695000,"title":"黑丝高跟美女"},{"count":281,"fcount":0,"galleryclass":1,"id":75,"img":"/ext/150721/0c8bb777e6593e10247f61560b69ccf5.jpg","rcount":0,"size":11,"time":1437478281000,"title":"美貌浴室短裙文胸水中湿身诱惑"},{"count":553,"fcount":0,"galleryclass":1,"id":76,"img":"/ext/150722/81bf963a3dcfeefdb518707fbd734950.jpg","rcount":0,"size":10,"time":1437579550000,"title":"女神的巨大美胸性感迷人"}]
     */

    private boolean status;
    private int total;
    /**
     * count : 1294
     * fcount : 0
     * galleryclass : 1
     * id : 9
     * img : /ext/150714/b32d42d218ebcdb85d39e93ffff390c3.jpg
     * rcount : 0
     * size : 10
     * time : 1436876053000
     * title : 办公室女郎的丝袜诱惑
     */

    private List<TngouEntity> tngou;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTngou(List<TngouEntity> tngou) {
        this.tngou = tngou;
    }

    public boolean isStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public List<TngouEntity> getTngou() {
        return tngou;
    }

    public static class TngouEntity {
        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public void setCount(int count) {
            this.count = count;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCount() {
            return count;
        }

        public int getFcount() {
            return fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public int getRcount() {
            return rcount;
        }

        public int getSize() {
            return size;
        }

        public long getTime() {
            return time;
        }

        public String getTitle() {
            return title;
        }
    }
}
