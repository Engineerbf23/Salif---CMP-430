package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements TextWatcher,SeekBar.OnSeekBarChangeListener{
    //declare your variables for the widgets
    private EditText editTextBillAmount;
    private TextView textViewBillAmount;
    private TextView Percentage_display;
    private TextView Tip;
    private SeekBar seekBar ;
    private TextView total ;


    public static final String TAG ="com.example.tipcalculator.Main Activity";

    //declare the variables for the calculations
    private double billAmount = 0;
    private double percent = .10;

    //set the number formats to be used for the $ amounts , and % amounts
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add Listeners to Widgets
        Tip = findViewById(R.id.tip_display);
        total = findViewById(R.id.total_display);
        SeekBar seekBar = findViewById(R.id.seekbar_display);
        editTextBillAmount = (EditText)findViewById(R.id.editText_BillAmount);
        editTextBillAmount.addTextChangedListener((TextWatcher) this);
        textViewBillAmount = (TextView)findViewById(R.id.textView_BillAmount);
        Percentage_display = findViewById(R.id.percent_display);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setProgress(i);
                percent = (double) i/100;
                String percent = "" + i + "%";
                Percentage_display.setText(percent);
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }


    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textViewBillAmount.setText(charSequence);
        if (charSequence.toString().contentEquals("")){
            billAmount =0;
        }
        else billAmount= Double.parseDouble(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {
        textViewBillAmount.setText(editable.toString());

    }


    // calculate and display tip and total amounts
    private void calculate() {
        Log.d("MainActivity", "inside calculate method");

      Percentage_display.setText(percentFormat.format(percent));

       // calculate the tip and total
       double tip = billAmount * percent;
       double total = billAmount + tip;
       if (billAmount==0){

       }
       Tip.setText(currencyFormat.format(tip));
       this.total.setText(currencyFormat.format(total));

       // display tip and total formatted as currency
       //user currencyFormat instead of percentFormat to set the textViewTip
       // tipTextView.setText(currencyFormat.format(tip));
       //use the tip example to do the same for the Total

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
