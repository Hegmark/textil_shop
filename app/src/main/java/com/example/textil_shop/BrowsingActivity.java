package com.example.textil_shop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class BrowsingActivity extends AppCompatActivity {

    //private static final String LOG_TAG = BrowsingActivity.class.getName();
    //private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private FirebaseUser user;

    private ArrayList<Item> itemData;
    private ListView listView;
    private static CustomAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_browsing);

            listView=(ListView)findViewById(R.id.list);

            itemData = new ArrayList<>();

            initData();

            adapter= new CustomAdapter(itemData,getApplicationContext());

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Item item= itemData.get(position);
                    //TODO
                    Snackbar.make(view, item.getName(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                }
            });
        }

    private void initData() {
        itemData.add(new Item("Silk Dreams","Luxurious silk fabric with a soft drape and beautiful sheen.",99));
        itemData.add(new Item("Cotton Bliss", "Soft and breathable cotton perfect for everyday wear", 15));
        itemData.add(new Item("Velvet Touch", "Plush velvet with a smooth finish and rich color", 65));
        itemData.add(new Item("Linen Breeze", "Lightweight and airy linen ideal for summer clothing", 25));
        itemData.add(new Item("Woolen Wonders", "Warm and cozy wool perfect for cold weather garments", 45));
        itemData.add(new Item("Denim Delight", "Durable denim with a classic indigo hue", 20));
        itemData.add(new Item("Lace Elegance", "Delicate lace with intricate patterns and scalloped edges", 30));
        itemData.add(new Item("Satin Shine", "Smooth and lustrous satin ideal for formal attire", 50));
        itemData.add(new Item("Knit Comfort", "Stretchy and comfortable knit fabric for casual wear", 12));
        itemData.add(new Item("Brocade Beauty", "Opulent brocade with metallic accents and a textured finish", 75));
    }


}