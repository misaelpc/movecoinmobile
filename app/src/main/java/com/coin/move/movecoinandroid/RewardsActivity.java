package com.coin.move.movecoinandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misaelperezchamorro on 6/28/15.
 */
public class RewardsActivity extends Activity {
    private ListView mRedwardsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        mRedwardsList = (ListView) findViewById(R.id.redwards_list);
        List redwards = new ArrayList<String>();

        redwards.add("Misael");
        redwards.add("Misael");
        redwards.add("Misael");
        mRedwardsList.setAdapter(new RedwardsAdapter(getApplicationContext(),R.layout.redward_item,redwards));

    }
}
