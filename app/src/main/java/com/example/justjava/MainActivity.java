package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_text_input);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(price,name,hasWhippedCream,hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     * @param includeCream indicates if the user added whipped cream
     * @param includeChocolate  indicates if the user added chocolate
     * @return the total calculated price
     */
    private int calculatePrice(boolean includeCream, boolean includeChocolate){
        /* Price of one cup of coffee */
        int basePrice = 5;

        if(includeCream){
            basePrice = basePrice + 1;
        }

        if(includeChocolate){
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    /**
     * Creates a summary of the order.
     * @param name name of the customer
     * @param price is the total price of the order
     * @param cream is whether or not the user wants whipped cream as a topping
     * @param chocolate os whether or not the user wants chocolate as a topping
     * @return the summary of the order
     */
    private String createOrderSummary(int price, String name, boolean cream, boolean chocolate){
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + cream;
        priceMessage += "\nAdd chocolate? " + chocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity < 100){
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(getApplicationContext(),"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity > 1){
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(getApplicationContext(),"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
