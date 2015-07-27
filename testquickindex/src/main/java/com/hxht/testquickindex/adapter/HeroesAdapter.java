package com.hxht.testquickindex.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hxht.testquickindex.MainActivity;
import com.hxht.testquickindex.R;
import com.hxht.testquickindex.domain.Heroes;

import java.util.List;

public class HeroesAdapter extends BaseAdapter {

    private Context mContext;
    private List<Heroes> list;

    public HeroesAdapter(Context mContext, List<Heroes> list) {
        this.list = list;
        this.mContext = mContext;
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
        View view = null ;
        if (convertView == null){
            view = View.inflate(mContext,R.layout.activity_lv_item,null);
        }else{
            view = convertView ;
        }

        Heroes heroes = list.get(position);
        String currentStr = String.valueOf(heroes.getPinyin().charAt(0));

        ViewHolder holder = ViewHolder.getViewHolder(view);

        String str = null ;

        if (position == 0){
            str = currentStr ;
        }else{
            String lastStr = String.valueOf(list.get(position - 1).getPinyin().charAt(0));

            if (!TextUtils.equals(lastStr,currentStr)){
                str = currentStr ;
            }
        }


        holder.tv_letter.setVisibility(str == null?View.GONE:View.VISIBLE);
        holder.tv_name.setText(heroes.getName());
        holder.tv_letter.setText(String.valueOf(heroes.getPinyin().charAt(0)));

        return view;
    }

    static class ViewHolder {

        TextView tv_letter;
        TextView tv_name;

        public ViewHolder(TextView tv_name, TextView tv_letter) {
            this.tv_name = tv_name;
            this.tv_letter = tv_letter;
        }

        public static ViewHolder getViewHolder(View view) {
            Object tag = view.getTag();
            if (tag != null) {
                return (ViewHolder) tag;
            } else {
                return new ViewHolder(((TextView) view.findViewById(R.id.tv_name)), ((TextView) view.findViewById(R.id.tv_letter)));
            }
        }
    }
}
