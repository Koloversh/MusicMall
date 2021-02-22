package com.example.musicmall;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderCart extends AppCompatActivity {

    String[] emails = {"ImmortalNickolas@yandex.ru"};
    String subjects = "MusicMall order";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);

        setTitle("MusicMall/Мой Заказ:");


        Intent receivedIntent = getIntent();
        String userName = receivedIntent.getStringExtra("Заказчик: ");
        String goodsName = receivedIntent.getStringExtra("Наименование: ");
        int quantity = receivedIntent.getIntExtra("Количество: ", 0);
        double price = receivedIntent.getDoubleExtra("Цена за единицу: ", 0);
        double sumOfOrder = receivedIntent.getDoubleExtra("Общая стоимость: ", 0);

        emailText = "Заказчик: " + userName + "\n" + "Наименование: " + goodsName + "\n" +
                "Количество: " + quantity + "\n" + "Цена за единицу: " + price + "\n" +
                "Общая стоимость: " + sumOfOrder;

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
    }

    public void sendOrder(View view) {
        Intent sendingIntent = new Intent(Intent.ACTION_SENDTO);
        sendingIntent.setData(Uri.parse("mailto: "));
        sendingIntent.putExtra(Intent.EXTRA_EMAIL, emails);
        sendingIntent.putExtra(Intent.EXTRA_SUBJECT, subjects);
        sendingIntent.putExtra(Intent.EXTRA_TEXT, emailText);

        try {
            if (sendingIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendingIntent);
            }
        } catch (android.content.ActivityNotFoundException e) {

        }
    }
}