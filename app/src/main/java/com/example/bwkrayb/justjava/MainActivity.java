/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.bwkrayb.justjava;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.bwkrayb.justjava.R;

import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    int total = 0;

    double taxcost = 0;

    double tax = 0.076;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method adds 1 to the quantity
     */

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }


    /**
     * This method subtracts 1 from the quantity
     */

    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        total = quantity * 5;
        displayPrice(total);
        taxcost = total * tax;
        displayTax(taxcost);
        displayTotal(total + taxcost);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

    /**
     * This method displays the given tax on the screen.
     */
    private void displayTax(double number) {
        TextView priceTextView = (TextView) findViewById(R.id.tax_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

    /**
     * This method displays the given total on the screen.
     */
    private void displayTotal(double number) {
        TextView priceTextView = (TextView) findViewById(R.id.total_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

}