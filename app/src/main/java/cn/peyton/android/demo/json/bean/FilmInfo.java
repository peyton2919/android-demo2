package cn.peyton.android.demo.json.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <span style="color:red;font: 16px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;">
 * </span>
 * <pre>
 * 作者: <a href="http://www.peyton.cn">peyton</a>
 * 邮箱: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * 创建时间: 2017-11-10 14:51
 * </pre>
 */

public class FilmInfo {
    private int code;

    private List<FilmBean> list;


    public static   class FilmBean{
        private  String aid;
        private String author;
        private int coins;
        private String copyright;
        private String create;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getCoins() {
            return coins;
        }

        public void setCoins(int coins) {
            this.coins = coins;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCreate() {
            return create;
        }

        public void setCreate(String create) {
            this.create = create;
        }

        public FilmBean() {
        }

        public FilmBean(String aid, String author, int coins, String copyright, String create) {
            this.aid = aid;
            this.author = author;
            this.coins = coins;
            this.copyright = copyright;
            this.create = create;
        }

        @Override
        public String toString() {
            return "FilmBean{" +
                    "aid='" + aid + '\'' +
                    ", author='" + author + '\'' +
                    ", coins=" + coins +
                    ", copyright='" + copyright + '\'' +
                    ", create=" + create +
                    '}';
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<FilmBean> getList() {
        if (null == list) list = new ArrayList<>();
        return list;
    }

    public void setList(List<FilmBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FilmInfo{" +
                "code=" + code +
                ", list=" + list +
                '}';
    }
}
