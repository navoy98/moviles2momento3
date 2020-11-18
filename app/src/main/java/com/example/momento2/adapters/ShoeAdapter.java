package com.example.momento2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.momento2.R;
import com.example.momento2.models.ShoesModels;

import java.util.ArrayList;

public class ShoeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ShoesModels> modelsArrayList;

    public ShoeAdapter(Context context, ArrayList<ShoesModels> modelsArrayList) {
        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public int getCount() {
        return modelsArrayList.size();
    }

    @Override
    public ShoesModels getItem(int position) {
        return modelsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.shoes_list_item, parent, false);
        }

        TextView tv_shoe_list_item_description = convertView.findViewById(R.id.tv_shoe_list_item_description);
        TextView tv_shoe_list_item_brand = convertView.findViewById(R.id.tv_shoe_list_item_brand);

        tv_shoe_list_item_description.setText(getItem(position).getDescription());
        tv_shoe_list_item_brand.setText(getItem(position).getBrand());

        return convertView;
    }
}
