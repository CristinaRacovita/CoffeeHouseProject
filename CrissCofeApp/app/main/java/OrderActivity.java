package com.example.crisscafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String mOrder = "com.example.android.crisscafe.extra.Mesaj";
    public static final String mInfo = "com.example.android.crisscafe.extra.MESSAGE";
    private EditText mName,mAddress,mPhone,mNote;
    private String mMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        String messageOrder = getIntent().getStringExtra(MainActivity.mOrder);
        TextView txt = findViewById(R.id.textView3);
        TextView txtt = findViewById(R.id.textView);
        assert messageOrder != null;
        txt.setText(messageOrder.substring(15));
        txtt.setText(messageOrder.substring(0,15));
        Spinner spinner = findViewById(R.id.spinner2);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
        mName=findViewById(R.id.name_text);
        mAddress=findViewById(R.id.editText3);
        mPhone=findViewById(R.id.phone_text);
        mNote=findViewById(R.id.note_text);
        RadioGroup mDelivery = findViewById(R.id.radioGroup);

        if (mDelivery.getCheckedRadioButtonId() == -1){
            mDelivery.check(R.id.method1);
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void radioButtons(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.method1:
                if (checked)
                    displayToast(getString(R.string.method1));
                    mMethod=((RadioButton)view).getText().toString();
                break;
            case R.id.method2:
                if (checked)
                    displayToast(getString(R.string.method2));
                    mMethod=((RadioButton)view).getText().toString();
                break;
            case R.id.method3:
                if (checked)
                    displayToast(getString(R.string.method3));
                     mMethod=((RadioButton)view).getText().toString();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void alertOrder(View view) {
        if(mName.getText().toString().equals("")){
            mName.setError("You must complete this field!");
        }
        else if(mAddress.getText().toString().equals("")){
            mAddress.setError("You must complete this field!");
        }
        else if(mPhone.getText().toString().equals("")){
            mPhone.setError("You must complete this field!");
        }
        else {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setTitle("Attention!");
            myAlert.setMessage("Your order will be placed! You're sure?");
            myAlert.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DialogFragment newFragment = new ClockFragment();
                    newFragment.show(getSupportFragmentManager(),
                            "Pick an hour for delivery!");

                }
            });
            myAlert.setNegativeButton("Nope!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            myAlert.show();
        }
    }

    public void onClickName(View view) {
        if( TextUtils.isEmpty(mName.getText())){
            mName.setError("You must complete this field!");
        }
    }

    public void onClickAddress(View view) {
        if( TextUtils.isEmpty(mAddress.getText())){
            mAddress.setError("You must complete this field!");
        }
    }

    public void onClickPhone(View view) {
        if( TextUtils.isEmpty(mPhone.getText())){
            mPhone.setError("You must complete this field!");
        }
    }

    public void onClickNote(View view) {
        if( TextUtils.isEmpty(mNote.getText())){
            mNote.setError("You must complete this field!");
        }
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String messageOrder = getIntent().getStringExtra(MainActivity.mOrder);
        String timeMessage = (hour_string + ":" + minute_string);
        String messageInfo = "Name: "+mName.getText().toString()+"\nAddress: "+mAddress.getText().toString()+"\nPhone: "+mPhone.getText().toString()+"\nNote: "+mNote.getText().toString()+"\nDelivery Method: "+mMethod+"\n";
        messageOrder+="\n\nArrival Time: "+timeMessage;
        String s = " ";

        Intent intent = new Intent(OrderActivity.this, YourOrderActivity.class);
        intent.putExtra(mOrder, messageOrder.substring(15));
        intent.putExtra(mInfo,messageInfo);
        startActivity(intent);

    }
}
