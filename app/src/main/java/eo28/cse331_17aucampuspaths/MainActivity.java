package eo28.cse331_17aucampuspaths;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

import hw5.*;
import hw8.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button pressMeButton = (Button) findViewById(R.id.searchButton);
        pressMeButton.setOnClickListener(pressMeButtonClick);
    }

    private View.OnClickListener pressMeButtonClick = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), ":)", Toast.LENGTH_SHORT).show();
        }
    };
}
