package com.example.mfood;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<Information_Food> listFood;
    public MyAdapter(Context context, List listFood) {
        this.context = context;
        this.listFood = listFood;
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int position) {
        return listFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder
    {
        Button btPrice;
        ImageView imgFood;
        TextView tvFoodName , tvDesFood;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_row, parent, false);
            viewHolder.btPrice = convertView.findViewById(R.id.btPrice);
            viewHolder.imgFood = convertView.findViewById(R.id.imgFood);
            viewHolder.tvDesFood = convertView.findViewById(R.id.tvDescription);
            viewHolder.tvFoodName = convertView.findViewById(R.id.tvFoodName);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Information_Food information_food =listFood.get(position);

        viewHolder.btPrice.setText("Price:"+String.valueOf(information_food.getPriceFood())+"$");
        viewHolder.tvFoodName.setText("Food's name: "+String.valueOf(information_food.getNameFood()));
        viewHolder.tvDesFood.setText(String.valueOf(information_food.getDesFood()));
        viewHolder.imgFood.setImageBitmap(StringToBitMap(information_food.getImgFood()));
        return convertView;
    }
    public Bitmap StringToBitMap(String encodedString){
        byte[] bitmapArray;
        bitmapArray = Base64.decode(encodedString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
    }
}
