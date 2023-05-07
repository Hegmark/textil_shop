package com.example.textil_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrderingActivity extends AppCompatActivity {

    private FirebaseUser user;
    private Item item;
    TextView desc;
    TextView name;
    TextView price;
    EditText number;

    private FirebaseFirestore mFirestore;
    private CollectionReference mOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);

        mFirestore = FirebaseFirestore.getInstance();
        mOrders = mFirestore.collection("order");

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            finish();
        }
        desc = (TextView)findViewById(R.id.orderDesc);
        name = (TextView)findViewById(R.id.orderName);
        price = (TextView)findViewById(R.id.orderPrice);
        number = (EditText) findViewById(R.id.orderNumber);
        item = new Item(getIntent().getStringExtra("item"),getIntent().getStringExtra("desc"),getIntent().getIntExtra("price",0));
        desc.setText(item.getDesc());
        name.setText(item.getName());
        String pri = "$"+Integer.toString(item.getPrice())+"/yard";
        price.setText(pri);
    }

    public void back(View view) {
        Intent intent = new Intent(this, BrowsingActivity.class);
        startActivity(intent);
    }

    public void order(View view) {
        try{
            Float yard = Float.parseFloat(number.getText().toString());
            Order order = new Order(user.getUid(),item.getName(),yard);
            mOrders.add(order);

            Intent intent = new Intent(this, BrowsingActivity.class);
            startActivity(intent);
        }catch(Exception e){
            Toast.makeText(OrderingActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}