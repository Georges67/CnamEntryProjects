package cnam.com.myfirstgpsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyCustomRecyclerAdapter extends RecyclerView.Adapter<MyCustomRecyclerViewHolder> {

    private final Context context;
    // FOR DATA
    private List<GpsPath> gpsPathList;

    // CONSTRUCTOR
    public MyCustomRecyclerAdapter(Context context, List<GpsPath> githubUsers) {
        this.context = context;
        this.gpsPathList = githubUsers;
    }

    @Override
    public MyCustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.text_row_item, parent, false);

        return new MyCustomRecyclerViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GPSPATH
    @Override
    public void onBindViewHolder(MyCustomRecyclerViewHolder viewHolder, int position) {
        viewHolder.updateWithGpsPath(this.gpsPathList.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.gpsPathList.size();
    }
}