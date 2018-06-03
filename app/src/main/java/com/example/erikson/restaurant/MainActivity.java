package com.example.erikson.restaurant;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.erikson.restaurant.ItemMenu.ItemMenuStructure;
import com.example.erikson.restaurant.ItemMenu.MenuBaseAdapter;
import com.example.erikson.restaurant.ItemMenu.OnLoadCompleImg;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements OnLoadCompleImg{

    private ListView LIST;
    private ArrayList<ItemMenuStructure> LISTINFO;
    private MenuBaseAdapter ADAPTER;
    private MainActivity root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LISTINFO = new ArrayList<ItemMenuStructure>();
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadData();
        loadComponent();
    }

    private void loadComponent() {
        LIST = (ListView)this.findViewById(R.id.foodlist);

    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Cargando datos...");
        progressDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://181.114.179.122:7070/api/v1.0/food", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray listData = response.getJSONArray("info");
                    for(int i = 0; i < listData.length(); i++){
                        JSONObject obj = listData.getJSONObject(i);
                        String name = obj.getString("name");
                        int cantidad = obj.getInt("quantity");
                        String url = obj.getString("avatar");
                        String id = obj.getString("_id");
                        ItemMenuStructure item = new ItemMenuStructure(name, url, cantidad, id);
                        LISTINFO.add(item);
                    }
                    ADAPTER = new MenuBaseAdapter(root,LISTINFO);
                    LIST.setAdapter(ADAPTER);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {
        img.setImageBitmap(imgsourceimg);
    }
}
