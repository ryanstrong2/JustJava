package org.ryanstrong.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        CheckBox checkBox =(CheckBox) findViewById(R.id.whip);
        boolean hasWhippedCream = checkBox.isChecked();
        CheckBox chocCheckBox =(CheckBox) findViewById(R.id.choc);
        boolean hasChocolate = chocCheckBox.isChecked();
        int price = calculatePrice();
        String name = setNameView();
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, name));


        }
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name){

        return "Name: " + name + "\nAdd Whipped cream? " + hasWhippedCream + "\nAdd chocolate? "
                + hasChocolate +
                "\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank you!";
    }
    /**
     * Calculates the total price of the order.
     *
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        return quantity * 5;
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
        /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_sum);
        orderSummaryTextView.setText(message);
    }
    private String setNameView(){
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
//        return editText;
        return name;
    }

}