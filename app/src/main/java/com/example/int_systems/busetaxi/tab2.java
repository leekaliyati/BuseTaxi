package com.example.int_systems.busetaxi;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.int_systems.busetaxi.Services.taxiVehicle;
import com.example.int_systems.busetaxi.Services.vehicleAdapter;
import com.google.android.gms.common.api.Response;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Int-Systems on 3/20/2018.
 */
public class tab2 extends Fragment {
    View view;
    Context globalContext;
    List<taxiVehicle> productList;
    //Response response =new Response();
    ArrayList<String> listItems = new ArrayList<String>();
    //a list to store all the products


    //the recyclerview
    RecyclerView recyclerView;
    private static final String URL_PRODUCTS = "http://192.168.101.1/MyApi/Api.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        globalContext = this.getActivity();
        productList = new ArrayList<>();
        view = inflater.inflate(R.layout.tab2,container,false);
        getService();

        return view;


    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Double latitude = Double.valueOf(intent.getStringExtra("latutide"));
            Double  longitude = Double.valueOf(intent.getStringExtra("longitude"));

            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("latitude", latitude);
            params.put("longitude", longitude);
            client.post("http://10.0.2.2/BuseTaxi/getTaxi.php", params, new TextHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    try {
                        JSONArray array = new JSONArray(responseString);
                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject product = array.getJSONObject(i);

                            //adding the product to product list
                            productList.add(new taxiVehicle(

                                    product.getString("address"),
                                    product.getString("parkingSlot")

                            ));
                        }

                        //creating adapter object and setting it to recyclerview
                        vehicleAdapter adapter = new vehicleAdapter(globalContext, productList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });



        };


    };

    private void getService() {

        globalContext.registerReceiver(broadcastReceiver, new IntentFilter(GoogleServices.str_receiver));
    }
}
