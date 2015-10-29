package ru.susu.itschool.simplemap;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private final ArrayList<String> placeNames = new ArrayList<>();
    private final ArrayList<LatLng> placeLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        MyLocationListener.SetUpLocationListener(this);

        ListView list = (ListView) findViewById(R.id.list);

        Location l = MyLocationListener.imHere;
        placeNames.add("Я");
        placeLocations.add(new LatLng(l.getLatitude(), l.getAccuracy()));

        placeNames.add("Test");
        placeLocations.add(new LatLng(200, 50));

        //placeNames.add("Москва"); // если вам не лень -- дозаполните
        //placeNames.add("Ярославль");
        //placeNames.add("Уфа");
        //placeNames.add("Америкосия");

        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, placeNames);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MapsActivity.addMarker(
                        new MarkerOptions()
                                .draggable(false)
                                .flat(true)
                                .position(placeLocations.get(position))
                                .title(placeNames.get(position))
                );
                setContentView(R.layout.activity_maps);
            }
        });
    }
}
