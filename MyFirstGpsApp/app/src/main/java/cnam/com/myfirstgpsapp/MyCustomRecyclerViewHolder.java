package cnam.com.myfirstgpsapp;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCustomRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTv;
    private TextView detailsTv;

    public MyCustomRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        this.nameTv = itemView.findViewById(R.id.fragment_main_item_title);
        this.detailsTv = itemView.findViewById(R.id.fragment_main_item_details);
    }

    public void updateWithGpsPath(GpsPath path){
        this.nameTv.setText(path.getName());
        List<GpsPoint> data = path.getData();
        this.detailsTv.setText(data.size() + " points number");
    }
}