package webpicasso.bwie.mbenben.bookdamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import java.util.ArrayList;
import webpicasso.bwie.mbenben.bookdamo.com.bean.BookType;

public class MainActivity extends AppCompatActivity {
    private ListView mListView = (ListView) findViewById(R.id.main_list);
    private ArrayList<BookType.Result> mList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 请求数据，利用xUtils
        // HttpUtils http = new HttpUtils();
        RequestQueue mQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://apis.juhe.cn/goodbook/catalog?dtype=&key=26f4887845dddc5355fdd627228160ab",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 解析json
                        Gson gson = new Gson();
                        // 获得解析对象
                        BookType book = gson.fromJson(response, BookType.class);
                        // 获得集合，为集合赋值
                        mList = book.result;
                        // 加载数据
                        MyAdapter adapter = new MyAdapter();
                        // 绑定
                        mListView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        // 监听点击事件
      //  mListView.setOnItemClickListener(new);
        /**
         *  Fragment
         */

    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 获得引用
            ViewHolder holder;
            // 优化判空
            if (convertView == null) {
                // 获得对象
                convertView = View.inflate(MainActivity.this, R.layout.activity_item, null);
                holder = new ViewHolder();
                holder.mTv = (TextView) convertView.findViewById(R.id.item_tv);
                // 绑定
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // 获得当前对象，赋值
            BookType.Result result = mList.get(position);
            holder.mTv.setText(result.catalog);
            // 返回
            return convertView;
        }

        class ViewHolder {
            TextView mTv;
        }
    }
}