package applist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ganggongui.dalkomsoft02.com.colorpad.R;

/**
 * Created by ganggongui on 15. 2. 1..
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext = null;
    private ArrayList<ListData> mListData = new ArrayList<ListData>();

    public ListViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void addItem(Drawable icon, String appname) {
        ListData addInfo = null;
        addInfo = new ListData();
        addInfo.icon = icon;
        addInfo.appname = appname;
        mListData.add(addInfo);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_apps, null);

            holder.icon = (ImageView) convertView.findViewById(R.id.IV_icon);
            holder.appname = (TextView) convertView.findViewById(R.id.TV_appname);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListData mData = mListData.get(position);

        if (mData.icon != null) {
            holder.icon.setVisibility(View.VISIBLE);
            holder.icon.setImageDrawable(mData.icon);
        } else {
            holder.icon.setVisibility(View.GONE);
        }

        holder.appname.setText(mData.appname);


        return convertView;
    }
}
