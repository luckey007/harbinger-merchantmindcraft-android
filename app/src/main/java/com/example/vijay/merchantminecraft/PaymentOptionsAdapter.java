package com.example.vijay.merchantminecraft;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Vijay on 28/11/2015.
 */
public class PaymentOptionsAdapter extends BaseAdapter {

    int[] index = {-1};
    RadioButton[] lastCheckedRadioButton = new RadioButton[1];
    private Context context;

    public PaymentOptionsAdapter(Context context, ArrayList<PaymentOptionPOJO> paymentOptionItems) {
        this.context = context;
        this.paymentOptionItems = paymentOptionItems;
    }

    private ArrayList<PaymentOptionPOJO> paymentOptionItems;
    @Override
    public int getCount() {
        return paymentOptionItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.payment_list_item,null);
        }
        final RadioButton paymentOptionRadioButton=(RadioButton)convertView.findViewById(R.id.paymentOptionRadioButton);
        TextView paymentOptionName=(TextView)convertView.findViewById(R.id.paymentOptionName);
        ImageView paymentOptionLogo=(ImageView)convertView.findViewById(R.id.paymentOptionLogo);

        paymentOptionName.setText(paymentOptionItems.get(position).getPaymentOptionName());

        paymentOptionRadioButton.setChecked(false);
        paymentOptionRadioButton.setFocusable(false);
        paymentOptionRadioButton.setFocusableInTouchMode(false);
        paymentOptionRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index[0]!=-1) {
                    lastCheckedRadioButton[0].setChecked(false);
                }
                index[0] =position;
                paymentOptionRadioButton.setChecked(true);
                lastCheckedRadioButton[0] =paymentOptionRadioButton;


                Log.i("LAST CHECKED","CURRENT = "+paymentOptionRadioButton+" LAST = "+lastCheckedRadioButton[0]+" INDEX = "+index[0]);

            }
        });

        return convertView;
    }


}
