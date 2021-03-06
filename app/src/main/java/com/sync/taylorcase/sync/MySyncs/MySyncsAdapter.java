package com.sync.taylorcase.sync.MySyncs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sync.taylorcase.sync.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MySyncsAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> groups;
    ArrayList<String> names;
    private static LayoutInflater inflater = null;

    public MySyncsAdapter(Context context, ArrayList<String> groups, ArrayList<String> names) {
        this.context = context;
        this.groups = groups;
        this.names = names;
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
            view = inflater.inflate(R.layout.custom_my_syncs_row, null);
        }

        TextView groupName = (TextView) view.findViewById(R.id.my_syncs_group);
        TextView name = (TextView) view.findViewById(R.id.my_syncs_name);

        groupName.setText(groups.get(position));
        name.setText(names.get(position));
//        TextView textView1 = (TextView) view.findViewById(R.id.my_syncs_textview_1);
//        TextView textView2 = (TextView) view.findViewById(R.id.my_syncs_textview_2);
//        TextView textView3 = (TextView) view.findViewById(R.id.my_syncs_textview_3);
//        TextView textView4 = (TextView) view.findViewById(R.id.my_syncs_textview_4);

//        ArrayList<String> keys = getAllKeys();

//        ArrayList<String> matchingItems = syncs.get(keys.get(0));

//        Log.e("YEET", "syncs is: " + syncs);
//        Log.e("YEET", "matchingItems is: " + matchingItems);

//        name.setText(getKeyAt(position));

//        if (matchingItems.size() == 1) {
//            textView1.setText(matchingItems.get(0));
//            textView2.setVisibility(View.GONE);
//            textView3.setVisibility(View.GONE);
//            textView4.setVisibility(View.GONE);
//        } else if (matchingItems.size() == 2) {
//            textView1.setText(matchingItems.get(0));
//            textView2.setText(matchingItems.get(1));
//            textView3.setVisibility(View.GONE);
//            textView4.setVisibility(View.GONE);
//        } else if (matchingItems.size() == 3) {
//            textView1.setText(matchingItems.get(0));
//            textView2.setText(matchingItems.get(1));
//            textView3.setText(matchingItems.get(2));
//            textView4.setVisibility(View.GONE);
//        } else if (matchingItems.size() == 4) {
//            textView1.setText(matchingItems.get(0));
//            textView2.setText(matchingItems.get(1));
//            textView3.setText(matchingItems.get(2));
//            textView4.setText(matchingItems.get(3));
//        } else {
//            // TODO: ERROR
//        }

        return view;
    }

//    public ArrayList<String> getAllKeys() {
//        ArrayList<String> keys = new ArrayList<>();
//        for (String key : syncs.keySet()) {
//            keys.add(key);
//        }
//        return keys;
//    }
//
//    public String getKeyAt(int position) {
//        for (String key : syncs.keySet()) {
//            if (syncs.get(key).equals(position)) {
//                return key;
//            }
//        }
//        return null;
//    }

}
