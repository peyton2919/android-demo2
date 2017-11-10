package cn.peyton.android.demo.json.bean;

import java.util.List;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-10 14:20
 * </pre>
 */

public class DataInfo {

    /**
     * rs_code : 1000
     * data : {"count":5,"items":[{"id":45,"title":"\\u575a\\u679c"},{"id":132,"title":"\\u7092\\u8d27"},{"id":166,"title":"\\u871c\\u996f"},{"id":195,"title":"果脯"}]}
     * rs_msg : success
     */

    private String rs_code;
    private DataBean data;
    private String rs_msg;

    public String getRs_code() {
        return rs_code;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public static class DataBean {
        /**
         * count : 5
         * items : [{"id":45,"title":"\\u575a\\u679c"},{"id":132,"title":"\\u7092\\u8d27"},{"id":166,"title":"\\u871c\\u996f"},{"id":195,"title":"果脯"}]
         */

        private int count;
        private List<ItemsBean> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "count=" + count +
                    ", items=" + items +
                    '}';
        }

        public static class ItemsBean {
            /**
             * id : 45
             * title : \u575a\u679c
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "ItemsBean{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "DataInfo{" +
                "rs_code='" + rs_code + '\'' +
                ", data=" + data +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }
}
