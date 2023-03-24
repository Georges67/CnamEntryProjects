package cnam.com.myfirstgpsapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GpsPathListActivity extends AppCompatActivity {

    private MyGpsPathDbHelper m_gpsPathDbHelper;

    private List<GpsPath> m_gpsPathList;

    private RecyclerView recyclerView;
    private MyCustomRecyclerAdapter m_listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_path_list);

        // TODO 18) instanciate MyGpsPathDbHelper and get All path from dB into m_gpsPathList


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("List of GPS Path saved");
        setSupportActionBar(toolbar);

        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        this.configureRecyclerView();
    }

    private void configureRecyclerView() {

        // Get ListView object from xml
        this.recyclerView = findViewById(R.id.listPath);


        // TODO 19) instanciate m_listAdapter with MyCustomRecyclerAdapter ang give it m_gpsPathList


        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new UnderlayButton(
                        "Delete",
                        0,
                        Color.parseColor("#FF3C30"),
                        new UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                //  DELETE ACTION
                                // TODO 20) delete current path et update list content
                            }
                        }
                ));
                underlayButtons.add(new UnderlayButton(
                        "Show",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                //  SHOW ACTION
                                // TODO 21) show this path on the MainActivity
                            }
                        }
                ));
            }
        };
    }


    private void addFakeCnamPath() {
        String pathName = "fake Path";
        long pathId = m_gpsPathDbHelper.addPathEntry(pathName);
        m_currentPath = new GpsPath(pathId, pathName);
        String gpsPointTableName = m_currentPath.getGpsPointTableName();
        m_gpsPathDbHelper.createTablePoint(gpsPointTableName);

        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590194, 7.684751));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590420, 7.684023));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590694, 7.682710));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590950, 7.680993));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590858, 7.680414));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590489, 7.680049));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590261, 7.679781));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.589885, 7.679416));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.589289, 7.678783));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.588920, 7.678397));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.588494, 7.678697));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.587894, 7.680035));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.587417, 7.681197));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.586003, 7.684259));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.584909, 7.686693));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.585252, 7.687202));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.585908, 7.687439));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.586660, 7.687442));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.586969, 7.687441));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.587079, 7.687069));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.587155, 7.685175));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.587442, 7.684349));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.588163, 7.682700));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.588399, 7.682753));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.589059, 7.683287));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.589968, 7.683955));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590270, 7.684367));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590047, 7.685033));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590289, 7.685168));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.590776, 7.685397));
        m_gpsPathDbHelper.addPathGpsPoint(gpsPointTableName, new GpsPoint(48.591037, 7.684954));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}