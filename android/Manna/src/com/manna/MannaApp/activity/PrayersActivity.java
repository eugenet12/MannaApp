package com.manna.MannaApp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import com.manna.MannaApp.R;
import com.manna.MannaApp.model.Prayer;

import java.util.ArrayList;
import java.util.List;

public class PrayersActivity extends Activity {

    ScrollView scrollView;
    ViewGroup prayerContainer;
    int count = 0;

    List<Prayer> prayers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);

        prayers = new ArrayList<Prayer>();

        scrollView = (ScrollView) findViewById(R.id.scrollview);
        prayerContainer = (ViewGroup) scrollView.findViewById(R.id.linear_layout);
        Button addButton = (Button) findViewById(R.id.prayers_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View item = getLayoutInflater().inflate(R.layout.item_prayer, prayerContainer, false);
                ((TextView) item.findViewById(R.id.prayer_text)).setText("" + count);
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), PrayerViewActivity.class));
                        count++;
                    }
                });
                prayerContainer.addView(item);
                count++;
            }
        });

    }

    private void drawPrayers() {
        for (Prayer prayer : prayers) {
            View item = getLayoutInflater().inflate(R.layout.item_prayer, prayerContainer, false);
            ((TextView) item.findViewById(R.id.prayer_text)).setText(prayer.getContent());
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                }
            });
            prayerContainer.addView(item);
            count++;
        }
    }

}
