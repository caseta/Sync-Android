package com.sync.taylorcase.sync.ChooseItems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sync.taylorcase.sync.R;

public class ChooseItemsAdapter extends BaseAdapter {

    Context context;
    String[] items;
    private static LayoutInflater inflater = null;

    public ChooseItemsAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.custom_item_row, null);
        TextView text = (TextView) view.findViewById(R.id.item_text);
        text.setText(items[position]);
        return view;
    }
}



//    Context context;
//    String[] data;
//    private static LayoutInflater inflater = null;
//
//    public yourAdapter(Context context, String[] data) {
//        // TODO Auto-generated constructor stub
//        this.context = context;
//        this.data = data;
//        inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return data.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return data[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        View vi = convertView;
//        if (vi == null)
//            vi = inflater.inflate(R.layout.row, null);
//        TextView text = (TextView) vi.findViewById(R.id.text);
//        text.setText(data[position]);
//        return vi;
//    }