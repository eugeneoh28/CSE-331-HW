package eo28.cse331_17aucampuspaths;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;

import hw5.*;
import hw8.*;

public class MainActivity extends AppCompatActivity {

    DrawView view;

    ListView buildingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream pathsInputStream = this.getResources().openRawResource(R.raw.campus_paths);
        InputStream buildingInputStream = this.getResources().openRawResource(R.raw.campus_buildings);

        Button pressMeButton = (Button) findViewById(R.id.searchButton);

        view = (DrawView) findViewById(R.id.imageView);

        buildingsList = (ListView) findViewById(R.id.Buildings);

        pressMeButton.setOnClickListener(pressMeButtonClick);
    }

    private View.OnClickListener pressMeButtonClick = new View.OnClickListener() {
        public void onClick(View v) {
            view.toggleDrawCircle();
        }
    };
}
