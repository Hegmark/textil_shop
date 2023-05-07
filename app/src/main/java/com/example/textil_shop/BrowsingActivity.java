package com.example.textil_shop;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class BrowsingActivity extends AppCompatActivity {

    private static final String LOG_TAG = BrowsingActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private FirebaseUser user;

    private FrameLayout redCircle;
    private TextView countTextView;
    private int cartItems = 0;
    private int gridNumber = 1;

    private RecyclerView mRecyclerView;
    private List<Item> mItemsData;
    //private ItemAdapter mAdapter;

    private SharedPreferences preferences;

    private boolean viewRow = true;
        ArrayList<DataModel> dataModels;
        ListView listView;
        private static CustomAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_browsing);

            listView=(ListView)findViewById(R.id.list);

            dataModels= new ArrayList<>();

            dataModels.add(new DataModel("Apple Pie", "Android 1.0", "1","September 23, 2008"));
            dataModels.add(new DataModel("Banana Bread", "Android 1.1", "2","February 9, 2009"));
            dataModels.add(new DataModel("Cupcake", "Android 1.5", "3","April 27, 2009"));
            dataModels.add(new DataModel("Donut","Android 1.6","4","September 15, 2009"));
            dataModels.add(new DataModel("Eclair", "Android 2.0", "5","October 26, 2009"));
            dataModels.add(new DataModel("Froyo", "Android 2.2", "8","May 20, 2010"));
            dataModels.add(new DataModel("Gingerbread", "Android 2.3", "9","December 6, 2010"));
            dataModels.add(new DataModel("Honeycomb","Android 3.0","11","February 22, 2011"));
            dataModels.add(new DataModel("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011"));
            dataModels.add(new DataModel("Jelly Bean", "Android 4.2", "16","July 9, 2012"));
            dataModels.add(new DataModel("Kitkat", "Android 4.4", "19","October 31, 2013"));
            dataModels.add(new DataModel("Lollipop","Android 5.0","21","November 12, 2014"));
            dataModels.add(new DataModel("Marshmallow", "Android 6.0", "23","October 5, 2015"));

            adapter= new CustomAdapter(dataModels,getApplicationContext());

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    DataModel dataModel= dataModels.get(position);

                    Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                }
            });
        }


    }