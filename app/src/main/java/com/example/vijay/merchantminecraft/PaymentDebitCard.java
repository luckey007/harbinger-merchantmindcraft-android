package com.example.vijay.merchantminecraft;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class PaymentDebitCard extends ActionBarActivity {

    private Spinner bankSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_dc);
        bankSpinner=(Spinner)findViewById(R.id.spinnerBank);

        ArrayList<String> bankList=new ArrayList<String>();
        bankList.add("HDFC");
        bankList.add("ICICI");
        bankList.add("BARCLAYS");
        bankList.add("CITI");


        ArrayAdapter<String> bankAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

        bankSpinner.setAdapter(bankAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_debit_card, menu);
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
}
