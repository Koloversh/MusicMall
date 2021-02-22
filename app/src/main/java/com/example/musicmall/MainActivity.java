package com.example.musicmall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;

    Spinner spinner;

    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;

    String goodsName;
    double price;

    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.editTextTextPersonName);

        createSpinner();
        createMap();
    }

    void  createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("horns");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void  createMap(){
        goodsMap = new HashMap();
        goodsMap.put("guitar", 700.0);
        goodsMap.put("drums", 1000.0);
        goodsMap.put("horns", 800.0);
    }

    public void increase(View view) {
        quantity = quantity + 1;
        TextView quantityText = findViewById(R.id.quantityTextView);
        quantityText.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    public void decrease(View view) {
        quantity = quantity - 1;
        if(quantity < 0) {
            quantity = 0;
        }
        TextView quantityText1 = findViewById(R.id.quantityTextView);
        quantityText1.setText(" " + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch (goodsName) {
            case "guitar":goodsImageView.setImageResource(R.drawable.guitar);
                break;

            case "drums":goodsImageView.setImageResource(R.drawable.drums);
                break;

            case "horns":goodsImageView.setImageResource(R.drawable.horns);
                break;

            default:goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goodsToCart(View view) {

        Order order = new Order();
        
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.sumOfOrder = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderCart.class);
        orderIntent.putExtra("Заказчик: ", order.userName);
        orderIntent.putExtra("Наименование: ", order.goodsName);
        orderIntent.putExtra("Количество: ", order.quantity);
        orderIntent.putExtra("Цена за еденицу", order.price);
        orderIntent.putExtra("Общая стоимость: ", order.sumOfOrder);
        startActivity(orderIntent);
    }
}