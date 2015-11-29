package com.example.vijay.merchantminecraft;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PaymentCreditCard extends ActionBarActivity {

    private ArrayList<String> months;
    private ArrayList<String> years;
    private Spinner monthSpinner;
    private Spinner yearSpinner;
    EditText creditCVV;
    EditText cardNo;
    EditText cardholderName;
    Button submitCC;
    CreditCard cc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_cc);
        monthSpinner=(Spinner)findViewById(R.id.spinnerMonth);
        yearSpinner=(Spinner)findViewById(R.id.spinnerYear);
        cardNo=(EditText)findViewById(R.id.cardNumber);
        creditCVV=(EditText)findViewById(R.id.creditCVV);
        cardholderName=(EditText)findViewById(R.id.cardholderName);
        submitCC=(Button)findViewById(R.id.submitCC);
        initialize();

        ArrayAdapter<String> monthAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,months);
        ArrayAdapter<String> yearAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,years);

        monthSpinner.setAdapter(monthAdapter);
        yearSpinner.setAdapter(yearAdapter);


        submitCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOnCard=cardholderName.getText().toString();
                int cardNumber=Integer.parseInt(cardNo.getText().toString());
                int monthExpiry=Integer.parseInt(monthSpinner.getSelectedItem().toString());
                int yearExpiry=Integer.parseInt(yearSpinner.getSelectedItem().toString());
                int cvvNo=Integer.parseInt(creditCVV.getText().toString());

                cc=new CreditCard(cvvNo,monthExpiry,yearExpiry,nameOnCard,cardNumber);
            }
        });
    }

    public void initialize()
    {
        months=new ArrayList<String>();
        years=new ArrayList<String>();
        months.add("01");
        months.add("02");
        months.add("03");
        months.add("04");
        months.add("05");
        months.add("06");
        months.add("07");
        months.add("08");
        months.add("09");
        months.add("10");
        months.add("11");
        months.add("12");

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        for(int i=0;i<20;i++)
        {
            years.add(year+++"");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_credit_card, menu);
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


    class ReachPayU extends AsyncTask<Object,Object,Object>
    {

        @Override
        protected Object doInBackground(Object... params) {

            String url="https://test.payu.in?mc_cid=4f8597f9d4&mc_eid=[UNIQID]";
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(url);

            List<NameValuePair> nameValuePairList=new ArrayList<NameValuePair>();


            nameValuePairList.add(new BasicNameValuePair("key","C0Dr8m"));
            nameValuePairList.add(new BasicNameValuePair("txnid","123"));
            nameValuePairList.add(new BasicNameValuePair("productinfo","Stuff."));
            nameValuePairList.add(new BasicNameValuePair("amount","9999"));
            nameValuePairList.add(new BasicNameValuePair("pg","CC"));
            nameValuePairList.add(new BasicNameValuePair("bankcode","ICIB"));
            nameValuePairList.add(new BasicNameValuePair("ccnum",cc.getCardNumber()+""));
            nameValuePairList.add(new BasicNameValuePair("ccname",cc.getName()));
            nameValuePairList.add(new BasicNameValuePair("ccvv",cc.getCvv()+""));
            nameValuePairList.add(new BasicNameValuePair("ccexpmon",cc.getMonthExpiry()+""));
            nameValuePairList.add(new BasicNameValuePair("ccexpyr",cc.getYearExpiry()+""));


            try {
                HttpResponse response = httpClient.execute(httpPost);
                // write response to log
                Log.d("Http Post Response:", response.toString());
            } catch (ClientProtocolException e) {
                // Log exception
                e.printStackTrace();
            } catch (IOException e) {
                // Log exception
                e.printStackTrace();
            }


            return null;
        }
    }
}
