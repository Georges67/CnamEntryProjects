package cnam.com.myfirstgpsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.List;

public class MyCustomListAdapter extends BaseAdapter {

    private final MyGpsPathDbHelper m_gpsPathDbHelper;

    private Context context;
    private List<GpsPath> gpsPathList;

    public MyCustomListAdapter(Context context, List<GpsPath> gpsPathList) {
        this.context = context;
        this.gpsPathList = gpsPathList;

        m_gpsPathDbHelper = new MyGpsPathDbHelper(this.context);
    }

    @Override
    public int getCount() {
        return gpsPathList.size();
    }

    @Override
    public Object getItem(int position) {
        return gpsPathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        GpsPath gpsPath = this.gpsPathList.get(position);

        text1.setText(gpsPath.getName());

        // Get Points count :
        List<GpsPoint> gpsPointList = m_gpsPathDbHelper.getAllGpsPoint(gpsPathList.get(position).getGpsPointTableName());
        if (gpsPointList != null) text2.setText(String.valueOf(gpsPointList.size())+" points");
        else text2.setText("No point available");

        return twoLineListItem;
    }
}