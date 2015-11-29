package com.example.vijay.merchantminecraft;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


public class PaymentOptions extends ActionBarActivity {

    private ListView paymentOptionsListView;
    private ArrayList<PaymentOptionPOJO> paymentOptionsList;
    private Button proceedToPayButton;
    ArrayList<PaymentOptionPOJO> demoList;
    private View selectedMode=null;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        paymentOptionsListView=(ListView)findViewById(R.id.paymentOptionListView);
        proceedToPayButton=(Button)findViewById(R.id.p2pButton);
        demoList=new ArrayList<PaymentOptionPOJO>();













        demoList.add(new PaymentOptionPOJO("Debit Card",false,-1,0));
        demoList.add(new PaymentOptionPOJO("Credit Card",false,-1,4));
        demoList.add(new PaymentOptionPOJO("PayU Money",false,-1,3));
        demoList.add(new PaymentOptionPOJO("Citrus",false,-1,1));
        demoList.add(new PaymentOptionPOJO("PayTM",false,-1,2));
        sortPaymentOptions(demoList);
        PaymentOptionsAdapter adapter=new PaymentOptionsAdapter(this,demoList);


        paymentOptionsListView.setAdapter(adapter);

        proceedToPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkIfNoOptionSelected()) {
                    Intent in = new Intent(PaymentOptions.this, PaymentCreditCard.class);
                    startActivity(in);
                }else{
                    Toast.makeText(PaymentOptions.this,"Please select a payment option in order to proceed.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }


    }

    public boolean checkIfNoOptionSelected()
    {
        boolean flag=true;
        for(int i=0;i<demoList.size();i++)
        {
            View v=getViewByPosition(i,paymentOptionsListView);
            RadioButton rb= (RadioButton) v.findViewById(R.id.paymentOptionRadioButton);
            if(rb.isChecked()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_options, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void sortPaymentOptions(ArrayList<PaymentOptionPOJO> list)
    {
        Collections.sort(list,new Comparator<PaymentOptionPOJO>() {
            @Override
            public int compare(PaymentOptionPOJO lhs, PaymentOptionPOJO rhs) {

                String lhsorder=lhs.getOrder()+"";
                String rhsorder=rhs.getOrder()+"";
                return lhsorder.compareTo(rhsorder);
 /*               if(lhs.getOrder()==rhs.getOrder())
                    return 0;
                else if(lhs.getOrder()<rhs.getOrder())
                    return -1;
                else return 1;*/
            }
        });
    }



    private class PaymentOptionFetcher extends AsyncTask<Object,Object,Object>
    {

        @Override
        protected Object doInBackground(Object... params) {

            String url="";//service link
            String result="";
            HttpClient httpClient=new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(url);

            try {
                HttpResponse httpResponse=httpClient.execute(httpGet);
                HttpEntity httpEntity=httpResponse.getEntity();
                BufferedReader br=new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                StringBuilder sb=new StringBuilder();
                String line=null;
                while((line=br.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
                result=sb.toString();

                JSONArray jsonArray=new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    //make POJO and insert into array list
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            sortPaymentOptions(paymentOptionsList);
        }


    }





}
