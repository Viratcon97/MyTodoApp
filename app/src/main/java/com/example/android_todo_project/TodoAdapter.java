package com.example.android_todo_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<TodoData> {

    List<TodoData> dataList;
    int customLayoutId;

    public TodoAdapter(@NonNull Context context, int resource, @NonNull List<TodoData> objects) {
        super(context, resource, objects);
        dataList = objects;
        customLayoutId = resource;
    }

    @Override public int getCount() {
        return dataList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            // getting reference to the main layout and initializing
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(customLayoutId, null);
        }
        // initializing the imageview and textview and setting data
        TextView title = v.findViewById(R.id.tvTitle);
        TextView description = v.findViewById(R.id.tvDescription);

        TodoData data = dataList.get(position);
        title.setText(data.getTitle());
        description.setText(data.getDescription());
        return v;
    }
}
