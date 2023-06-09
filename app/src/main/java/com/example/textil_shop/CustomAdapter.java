package com.example.textil_shop;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Item> implements View.OnClickListener{

        private ArrayList<Item> dataSet;
        Context mContext;

        private static class ViewHolder {
            TextView txtName;
            TextView txtPrice;
            TextView txtDesc;
        }

        public CustomAdapter(ArrayList<Item> data, Context context) {
            super(context, R.layout.row_item, data);
            this.dataSet = data;
            this.mContext=context;

        }

        @Override
        public void onClick(View v) {
            int position=(Integer) v.getTag();
            Object object= getItem(position);
            Item ItemModel=(Item)object;
        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Item item = getItem(position);

            ViewHolder viewHolder;

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.row_item, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
                viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.price);
                viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.textil_desc);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            result.startAnimation(animation);
            lastPosition = position;

            viewHolder.txtName.setText(item.getName());
            String p = "$"+ item.getPrice() + "/yard";
            viewHolder.txtPrice.setText(p);
            viewHolder.txtDesc.setText(item.getDesc());

            return convertView;
        }
    }

