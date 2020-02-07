package com.example.animation_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<Model> list;

    public MyAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    private class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_adapter,parent,false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imgView);
            holder.textView = convertView.findViewById(R.id.tvName);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300,300);
            holder.imageView.setLayoutParams(layoutParams);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Model model = list.get(position);
        holder.imageView.setImageResource(model.getIcon());
        holder.textView.setText(model.getName());

        Animation animationUtils = AnimationUtils.loadAnimation(context,R.anim.anim);
        convertView.startAnimation(animationUtils);
        return convertView;
    }
}
