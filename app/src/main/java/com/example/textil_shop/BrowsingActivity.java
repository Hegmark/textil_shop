package com.example.textil_shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BrowsingActivity extends AppCompatActivity {

    private static final String LOG_TAG = BrowsingActivity.class.getName();
    private FirebaseUser user;

    private ArrayList<Item> itemData;
    private ListView listView;
    private static CustomAdapter adapter;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_browsing);

            user = FirebaseAuth.getInstance().getCurrentUser();
            if(user != null) {
                Log.d(LOG_TAG, "Authenticated user!");
            } else {
                Log.d(LOG_TAG, "Unauthenticated user!");
                finish();
            }

            mFirestore = FirebaseFirestore.getInstance();
            mItems = mFirestore.collection("textil");
            itemData = new ArrayList<>();
            initData();

        }

    private void initData() {
            mFirestore.collection("textil").get().addOnSuccessListener((OnSuccessListener<QuerySnapshot>) queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Item item = document.toObject(Item.class);
                        itemData.add(item);
                    }
                    dataDone();
                });


        /* itemData.add(new Item("Silk Dreams","Luxurious silk fabric with a soft drape and beautiful sheen.",99));
         itemData.add(new Item("Cotton Bliss", "Soft and breathable cotton perfect for everyday wear", 15));
         itemData.add(new Item("Velvet Touch", "Plush velvet with a smooth finish and rich color", 65));
         itemData.add(new Item("Linen Breeze", "Lightweight and airy linen ideal for summer clothing", 25));
         itemData.add(new Item("Woolen Wonders", "Warm and cozy wool perfect for cold weather garments", 45));
         itemData.add(new Item("Denim Delight", "Durable denim with a classic indigo hue", 20));
         itemData.add(new Item("Lace Elegance", "Delicate lace with intricate patterns and scalloped edges", 30));
         itemData.add(new Item("Satin Shine", "Smooth and lustrous satin ideal for formal attire", 50));
         itemData.add(new Item("Knit Comfort", "Stretchy and comfortable knit fabric for casual wear", 12));
         itemData.add(new Item("Brocade Beauty", "Opulent brocade with metallic accents and a textured finish", 75));
         itemData.add(new Item("Cashmere Cloud", "Soft and luxurious cashmere with a delicate texture", 120));
         itemData.add(new Item("Organic Oasis", "Certified organic cotton with a natural feel", 18));
         itemData.add(new Item("Tropical Temptation", "Bright and bold tropical print on lightweight rayon", 35));
         itemData.add(new Item("Cozy Fleece", "Warm and snuggly fleece for cold weather outfits", 22));
         itemData.add(new Item("Embellished Elegance", "Intricately beaded and embroidered fabric for formal wear", 85));
         itemData.add(new Item("Chambray Charm", "Soft and durable chambray with a denim-like look", 28));
         itemData.add(new Item("Percale Perfection", "Crisp and cool percale cotton ideal for bedding", 40));
         itemData.add(new Item("Plush Pile", "Thick and fluffy pile fabric for cozy blankets and rugs", 50));
         itemData.add(new Item("Stretch Silk", "Luxurious silk with a touch of stretch for added comfort", 110));
         itemData.add(new Item("Leather Luxe", "Buttery soft leather with a subtle sheen and supple feel", 150));


        /* mItems.add(new Item("Silk Dreams","Luxurious silk fabric with a soft drape and beautiful sheen.",99));
         mItems.add(new Item("Cotton Bliss", "Soft and breathable cotton perfect for everyday wear", 15));
         mItems.add(new Item("Velvet Touch", "Plush velvet with a smooth finish and rich color", 65));
         mItems.add(new Item("Linen Breeze", "Lightweight and airy linen ideal for summer clothing", 25));
         mItems.add(new Item("Woolen Wonders", "Warm and cozy wool perfect for cold weather garments", 45));
         mItems.add(new Item("Denim Delight", "Durable denim with a classic indigo hue", 20));
         mItems.add(new Item("Lace Elegance", "Delicate lace with intricate patterns and scalloped edges", 30));
         mItems.add(new Item("Satin Shine", "Smooth and lustrous satin ideal for formal attire", 50));
         mItems.add(new Item("Knit Comfort", "Stretchy and comfortable knit fabric for casual wear", 12));
         mItems.add(new Item("Brocade Beauty", "Opulent brocade with metallic accents and a textured finish", 75));
         mItems.add(new Item("Cashmere Cloud", "Soft and luxurious cashmere with a delicate texture", 120));
         mItems.add(new Item("Organic Oasis", "Certified organic cotton with a natural feel", 18));
         mItems.add(new Item("Tropical Temptation", "Bright and bold tropical print on lightweight rayon", 35));
         mItems.add(new Item("Cozy Fleece", "Warm and snuggly fleece for cold weather outfits", 22));
         mItems.add(new Item("Embellished Elegance", "Intricately beaded and embroidered fabric for formal wear", 85));
         mItems.add(new Item("Chambray Charm", "Soft and durable chambray with a denim-like look", 28));
         mItems.add(new Item("Percale Perfection", "Crisp and cool percale cotton ideal for bedding", 40));
         mItems.add(new Item("Plush Pile", "Thick and fluffy pile fabric for cozy blankets and rugs", 50));
         mItems.add(new Item("Stretch Silk", "Luxurious silk with a touch of stretch for added comfort", 110));
         mItems.add(new Item("Leather Luxe", "Buttery soft leather with a subtle sheen and supple feel", 150));*/

    }
        public void order(View view){
        Intent intent = new Intent(this, MyOrdersActivity.class);
        startActivity(intent);
    }
    public void ordering(Item item){
        Intent intentToOrder = new Intent(this, OrderingActivity.class);
        intentToOrder.putExtra("item", item.getName());
        intentToOrder.putExtra("desc", item.getDesc());
        intentToOrder.putExtra("price", item.getPrice());
        startActivity(intentToOrder);
    }

    public void dataDone(){
        listView=(ListView)findViewById(R.id.list);
        adapter= new CustomAdapter(itemData,getApplicationContext());
        listView.setAdapter(adapter);listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item= itemData.get(position);
                ordering(item);
            }
        });
    }
}