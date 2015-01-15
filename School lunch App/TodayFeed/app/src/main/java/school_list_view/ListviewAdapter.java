package school_list_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dalkomsoft02.ganggongui.todayfeed.R;

import java.util.ArrayList;

/**
 * Created by ganggongui on 14. 12. 19..
 */
public class ListviewAdapter extends BaseAdapter {

    private Context context;

    public ArrayList<ListData> listDatas = new ArrayList<ListData>();


    public ListviewAdapter(Context context) {
        super();
        this.context = context;
        listDatas.clear();
    }


    public void removeData() {
        listDatas.clear();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.schoollistlayout, null);

            viewHolder.SchoolName_TV = (TextView) convertView.findViewById(R.id.SchoolNameTV);

            viewHolder.SchoolAdress = (TextView) convertView.findViewById(R.id.SchoolAdressTV);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListData listData = listDatas.get(position);

        viewHolder.SchoolName_TV.setText(listData.SchoolName);

        viewHolder.SchoolAdress.setText(listData.Adress);

        return convertView;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return listDatas.size();
    }


    private class ViewHolder {

        TextView SchoolName_TV;

        TextView SchoolAdress;
    }


    public void addItem(String SchoolName, String SchoolAdress, String SchoolCode, String Juso) {
        ListData listData = new ListData();

        listData.SchoolName = SchoolName;

        listData.Adress = SchoolAdress;

        listData.Code = SchoolCode;

        listData.Joso = Juso;

        listDatas.add(listData);

        this.notifyDataSetChanged();
    }


}
