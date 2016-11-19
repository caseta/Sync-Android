package com.sync.taylorcase.sync.ChooseItems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sync.taylorcase.sync.R;

import java.util.ArrayList;

public class ChooseItemsAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> items;
    private static LayoutInflater inflater = null;

    public ChooseItemsAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
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
        text.setText(items.get(position));
        return view;
    }
}
