package org.ryanstrong.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int price = calculatePrice();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        String thanks= " Thank you";
        int price = calculatePrice();
        String priceMessage = "Total = $" + price ;
        priceMessage = priceMessage +"\nThank you!";
        displayMessage(priceMessage);
        }
    /**
     * Calculates the total price of the order.
     *
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }
    public void increment(View view ){
        quantity ++;
        displayQuantity (quantity);
    }
    public void decrement(View view){
        quantity -= 1;
        displayQuantity (quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numb) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numb);
    }
    private void displayPrice(int number){
        TextView priceTextView=(TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}