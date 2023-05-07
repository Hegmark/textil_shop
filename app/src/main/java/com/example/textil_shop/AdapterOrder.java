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

public class AdapterOrder extends ArrayAdapter<Order> implements View.OnClickListener{

    private ArrayList<Order> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView nameForOrder;
        TextView NumberForOrder;
    }

    public AdapterOrder(ArrayList<Order> data, Context context) {
        super(context, R.layout.row_item_for_order, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Order ItemModel=(Order)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Order item = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_for_order, parent, false);
            viewHolder.nameForOrder = (TextView) convertView.findViewById(R.id.nameForOrder);
            viewHolder.NumberForOrder = (TextView) convertView.findViewById(R.id.NumberForOrder);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.nameForOrder.setText(item.getTextil());
        String p = item.getYard() + "yards";
        viewHolder.NumberForOrder.setText(p);
        return convertView;
    }
}

