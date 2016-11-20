package com.sync.taylorcase.sync.JoinGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sync.taylorcase.sync.R;

import java.util.ArrayList;

public class JoinGroupAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> groups;
    private static LayoutInflater inflater = null;

    public JoinGroupAdapter(Context context, ArrayList<String> groups) {
        this.context = context;
        this.groups = groups;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.custom_groups_row, null);
        }
        TextView text = (TextView) view.findViewById(R.id.custom_groups_row_text);
        text.setText(groups.get(position));

        return view;
    }
}
