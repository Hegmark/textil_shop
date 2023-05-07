package com.example.textil_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Delete extends AppCompatActivity {

    private FirebaseUser user;
    private Order item;
    TextView orderWhat;
    TextView orderQuantity;
    List<Order> morder;

    private FirebaseFirestore mFirestore;
    private CollectionReference mOrders;
    String orderId;
    String what;
    int how;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mFirestore = FirebaseFirestore.getInstance();
        mOrders = mFirestore.collection("order");

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            finish();
        }
        orderWhat = (TextView)findViewById(R.id.orderWhat);
        orderQuantity = (TextView)findViewById(R.id.orderQuantity);
        what = getIntent().getStringExtra("item");
        how = (int) getIntent().getFloatExtra("yards",0);

        mFirestore.collection("order").whereEqualTo("uid", user.getUid()).get().addOnSuccessListener((OnSuccessListener<QuerySnapshot>) queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                item = document.toObject(Order.class);
                float asd =  item.getYard();
                int asdd = (int) asd;
                if(item.getTextil().equals(what) &&  asdd== how){
                    orderWhat.setText(item.getTextil());
                    orderQuantity.setText(Integer.toString(asdd));
                    orderId = document.getId();
                }
            }
        });

    }



    public void back(View view) {
        Intent intent = new Intent(this, MyOrdersActivity.class);
        startActivity(intent);
    }

    public void del(View view) {
        Intent intent = new Intent(this, MyOrdersActivity.class);
        mFirestore.collection("order").document(orderId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(intent);
            }
        });
    }
}