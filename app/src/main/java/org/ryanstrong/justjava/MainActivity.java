package org.ryanstrong.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    int basePrice = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkBox =(CheckBox) findViewById(R.id.whip);
        boolean hasWhippedCream = checkBox.isChecked();
        CheckBox chocCheckBox =(CheckBox) findViewById(R.id.choc);
        boolean hasChocolate = chocCheckBox.isChecked();
        int price = calculatePrice(basePrice, hasWhippedCream,hasChocolate);
        String name = setNameView();
        String displayMessage =(createOrderSummary(price, hasWhippedCream, hasChocolate, name));
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, displayMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }}
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name){
        return getString(R.string.order_name) + name + "\n" + getString(R.string.whipped) + " " + hasWhippedCream + "\n"
               + getString(R.string.chocolate) + " " + hasChocolate +
                "\n" + getString(R.string.quantity) + quantity + "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(price))
                + "\n" + getString(R.string.thanks);
    }
    /**
     * Calculates the total price of the order.
     *
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int basePrice, boolean hasWhippedCream, boolean hasChocolate) {
        if (hasWhippedCream){
            basePrice += 1;
        }
        if(hasChocolate){
            basePrice += 2;
        }
        return quantity * basePrice;
            }
    public void increment(View view ){
        if(quantity==100){
            Toast.makeText(this, "You can't  have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity ++;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if(quantity==1){
            Toast.makeText(this, "You can't  have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;}
        quantity --;
        displayQuantity(quantity);
    }
    private void displayQuantity(int numb){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numb);
    }
    private String setNameView(){
        EditText editText = (EditText) findViewById(R.id.name_field);
        String name = editText.getText().toString();
        return name;
    }
}