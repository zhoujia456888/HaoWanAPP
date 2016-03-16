package cn.zhoujia.haowanapp.Bean;

import java.util.List;

/**
 * Created by Zhoujia on 2016/3/16.
 */
public class TranslateBean {
    /**
     * errNum : 0
     * errMsg : success
     * retData : {"from":"en","to":"zh","trans_result":[{"src":"I am chinese, and you?","dst":"我是中国人，你呢？"}]}
     */

    private int errNum;
    private String errMsg;
    /**
     * from : en
     * to : zh
     * trans_result : [{"src":"I am chinese, and you?","dst":"我是中国人，你呢？"}]
     */

    private RetDataEntity retData;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String from;
        private String to;
        /**
         * src : I am chinese, and you?
         * dst : 我是中国人，你呢？
         */

        private List<TransResultEntity> trans_result;

        public void setFrom(String from) {
            this.from = from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public void setTrans_result(List<TransResultEntity> trans_result) {
            this.trans_result = trans_result;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public List<TransResultEntity> getTrans_result() {
            return trans_result;
        }

        public static class TransResultEntity {
            private String src;
            private String dst;

            public void setSrc(String src) {
                this.src = src;
            }

            public void setDst(String dst) {
                this.dst = dst;
            }

            public String getSrc() {
                return src;
            }

            public String getDst() {
                return dst;
            }
        }
    }
}
