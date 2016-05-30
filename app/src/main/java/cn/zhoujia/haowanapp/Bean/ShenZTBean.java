package cn.zhoujia.haowanapp.Bean;

/**
 * Created by Zhoujia on 2016/5/25.
 */
public class ShenZTBean{

    /**
     * code : 0
     * message : 操作成功
     * success : true
     * data : {"card_number":666559680,"card_balance":"4.45","balance_time":"2016-05-15 10:55:40","card_validity":"2045-04-29","current_time":"2016-05-22 03:20:36"}
     */

    private int code;
    private String message;
    private boolean success;
    /**
     * card_number : 666559680
     * card_balance : 4.45
     * balance_time : 2016-05-15 10:55:40
     * card_validity : 2045-04-29
     * current_time : 2016-05-22 03:20:36
     */

    private DataEntity data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private int card_number;
        private String card_balance;
        private String balance_time;
        private String card_validity;
        private String current_time;

        public int getCard_number() {
            return card_number;
        }

        public void setCard_number(int card_number) {
            this.card_number = card_number;
        }

        public String getCard_balance() {
            return card_balance;
        }

        public void setCard_balance(String card_balance) {
            this.card_balance = card_balance;
        }

        public String getBalance_time() {
            return balance_time;
        }

        public void setBalance_time(String balance_time) {
            this.balance_time = balance_time;
        }

        public String getCard_validity() {
            return card_validity;
        }

        public void setCard_validity(String card_validity) {
            this.card_validity = card_validity;
        }

        public String getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }
    }
}
