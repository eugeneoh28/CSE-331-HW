package eo28.cse331_17aucampuspaths;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hw5.*;
import hw8.*;

public class MainActivity extends AppCompatActivity {

    DrawView view;
    ListView startbuildingsList;
    ListView endbuildingsList;
    TextView startText;
    TextView endText;
    List<CampusPoint> nodes;
    List<Double> weights;
    String start;
    String end;
    Graph <CampusPoint, Double> network;
    Map<String, CampusPoint> buildings;
    Map<String, String> shortToLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (DrawView) findViewById(R.id.imageView);
        network = new Graph<CampusPoint, Double>();
        buildings = new HashMap<String, CampusPoint>();
        shortToLong = new HashMap<String, String>();
        startText = (TextView) findViewById(R.id.start);
        endText = (TextView) findViewById(R.id.end);
        start = "";
        end = "";
        weights = new ArrayList<Double>();
        nodes = new ArrayList<CampusPoint>();


        InputStream pathsInputStream = this.getResources().openRawResource(R.raw.campus_paths);
        InputStream buildingInputStream = this.getResources().openRawResource(R.raw.campus_buildings);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);


        try {
            InputStream buildingsStream = this.getResources().openRawResource(R.raw.campus_buildings);
            InputStream pathsStream = this.getResources().openRawResource(R.raw.campus_paths);
            CampusPaths.loadGraph(buildingsStream, pathsStream, buildings, shortToLong, network );
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "this error: " + e, Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<String>());
        adapter.addAll(shortToLong.keySet());

        startbuildingsList = (ListView) findViewById(R.id.BuildingsStart);
        startbuildingsList.setAdapter(adapter);
        startbuildingsList.setOnItemClickListener(startListViewItemClick);

        endbuildingsList = (ListView) findViewById(R.id.BuildingsEnd);
        endbuildingsList.setAdapter(adapter);
        endbuildingsList.setOnItemClickListener(endListViewItemClick);

        searchButton.setOnClickListener(searchButtonClick);
        resetButton.setOnClickListener(resetButtonClick);
    }

    private View.OnClickListener searchButtonClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (start.isEmpty() || end.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Choose both a start and end destination", Toast.LENGTH_SHORT).show();
            } else {
                nodes.clear();
                weights.clear();
                CampusPaths.findShortestPath(buildings.get(start), buildings.get(end), nodes, weights, network);
                nodes.add(0, buildings.get(start));
                view.drawPath(nodes);
                start = "";
                end = "";
            }
        }
    };

    private View.OnClickListener resetButtonClick = new View.OnClickListener() {
        public void onClick (View v) {
            start = "";
            end = "";
            startText.setText("Start Building: (Red)\n" + start);
            endText.setText("End Building: (Blue)\n" + end);
            view.resetMap();

        }
    };

    private ListView.OnItemClickListener startListViewItemClick = new ListView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
          start = (String) startbuildingsList.getItemAtPosition(position);
          startText.setText("Start Building: (Red)\n" + start);
          view.drawStart(Double.valueOf(buildings.get(start).getX()).floatValue(), Double.valueOf(buildings.get(start).getY()).floatValue());

      }
    };

    private ListView.OnItemClickListener endListViewItemClick = new ListView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
            end = (String) startbuildingsList.getItemAtPosition(position);
            endText.setText("End Building: (Blue)\n" + end);
            view.drawEnd(Double.valueOf(buildings.get(end).getX()).floatValue(), Double.valueOf(buildings.get(end).getY()).floatValue());
        }
    };
}
