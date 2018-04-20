package com.example.int_systems.busetaxi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Int-Systems on 3/20/2018.
 */
public class tab1 extends Fragment  {
    private Context globalContext = null;
    public View view;
    Double latitude,longitude;
    TextView tv_latitude, tv_longitude, tv_address,tv_area,tv_locality;
    Button btn_start;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.tab1,container,false);


        tv_latitude = (TextView) view.  findViewById(R.id.tv_latitude);
        tv_longitude = (TextView) view.  findViewById(R.id.tv_longitude);

        globalContext = this.getActivity();



        // Zoom in, animating the camera.



        //   registerReceiver(broadcastReceiver, new IntentFilter(GoogleService.str_receiver));

        getService();
        return view;

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            latitude = Double.valueOf(intent.getStringExtra("latutide"));
            longitude = Double.valueOf(intent.getStringExtra("longitude"));


            tv_latitude.setText(latitude+"");
            tv_longitude.setText(longitude+"");


        }


    };

    private void getService() {

        globalContext.registerReceiver(broadcastReceiver, new IntentFilter(GoogleServices.str_receiver));
    }


}
