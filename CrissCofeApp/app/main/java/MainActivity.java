package com.example.crisscafe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   // private Drawable drawable;


    public static final String mOrder = "com.example.android.crisscafe.extra.MESSAGE";
    public static final String mCart = "com.example.android.crisscafe.extra.MESSAGE";
    private String orderMessage = "Your order is: \n";
    private int mDonut=0,mFroyo=0,mIceCream=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.shopping_cart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        OrderActivity.class);
                orderMessage ="Your order is: \nDonut: "+mDonut+"pcs....."+mDonut*4+"$\nIce Cream: "+mIceCream+"pcs....."+mIceCream*5+"$\nCoffee: "+mFroyo+"pcs....."+mFroyo*8+"$\nTotal: "+(mDonut*4+mIceCream*5+mFroyo*8)+"$";
                intent.putExtra(mOrder, orderMessage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_order:
                Intent intent = new Intent(this,OrderActivity.class);
                orderMessage ="Your order is: \nDonut: "+mDonut+"pcs....."+mDonut*4+"$\nIce Cream: "+mIceCream+"pcs....."+mIceCream*5+"$\nCoffee: "+mFroyo+"pcs....."+mFroyo*8+"$\nTotal: "+(mDonut*4+mIceCream*5+mFroyo*8)+"$";
                intent.putExtra(mOrder,orderMessage);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                Intent intent1 = new Intent(this,ContactActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    public void showDonut(View view){
        displayToast(getString(R.string.donut_order_message));
        mDonut++;
    }
    public void showFroyo(View view){
        displayToast(getString(R.string.froyo_order_message));
        mFroyo++;

    }
    public void showIceCream(View view){
        displayToast(getString(R.string.ice_cream_order_message));
        mIceCream++;
    }

    public void emptyCart(View view) {
        mFroyo = 0;
        mIceCream = 0;
        mDonut = 0;
        displayToast("Your cart is empty now.");
    }

}
