package webpicasso.bwie.mbenben.bookdamo.com.bean;

import java.util.ArrayList;

/**
 * Created by MBENBEN on 2016/11/11.
 */
public class BookType {
    public String resultcode;
    public String reason;
    public ArrayList<Result> result;
    public int error_code;

    public class Result{
        public String id;
        public String catalog;
    }
}
