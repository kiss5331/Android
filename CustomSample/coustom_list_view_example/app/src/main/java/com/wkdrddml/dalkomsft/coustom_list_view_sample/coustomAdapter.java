package com.wkdrddml.dalkomsft.coustom_list_view_sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dalkomsft on 14. 9. 7.
 */

public class coustomAdapter extends ArrayAdapter<CostomData> {

    private ArrayList<CostomData> objects;

    public coustomAdapter(Context context, int resource, ArrayList<CostomData> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview, null);
        }
        CostomData costomData = objects.get(position);

        if (v != null) {
            ImageView imageView = (ImageView) v.findViewById(R.id.image);
            TextView textView = (TextView)v.findViewById(R.id.text);

            imageView.setBackgroundResource(costomData.getImage());
            textView.setText(costomData.getText());

        }


        return v;
    }

}


class CostomData {
    private int Image;
    private String text;


    public CostomData(Context context, int Image, String text) {
        this.Image = Image;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return Image;
    }

}

