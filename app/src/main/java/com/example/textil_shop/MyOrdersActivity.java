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

public class MyOrdersActivity extends AppCompatActivity {

    private FirebaseUser user;

    private ArrayList<Order> itemData;
    private ListView listView;
    private static AdapterOrder adapter;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            finish();
        }

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("textil");
        itemData = new ArrayList<>();
        initData();

    }

    private void initData() {
        mFirestore.collection("order").whereEqualTo("uid", user.getUid()).get().addOnSuccessListener((OnSuccessListener<QuerySnapshot>) queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Order item = document.toObject(Order.class);
                itemData.add(item);
            }
            dataDone();
        });
    }

    public void dataDone(){
        listView=(ListView)findViewById(R.id.listOrder);
        adapter= new AdapterOrder(itemData,getApplicationContext());
        listView.setAdapter(adapter);listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order item= itemData.get(position);
                Del(item);
            }
        });
    }

    private void Del(Order item) {
        Intent intent = new Intent(this, Delete.class);
        intent.putExtra("item", item.getTextil());
        intent.putExtra("yards", item.getYard());
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, BrowsingActivity.class);
        startActivity(intent);
    }
}