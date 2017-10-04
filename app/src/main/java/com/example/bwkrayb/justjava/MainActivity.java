/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.bwkrayb.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    String emailMessage = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method adds 1 to the quantity
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
        } else {
            Toast.makeText(getApplicationContext(), "Please order less than 100 coffees...",
                    Toast.LENGTH_LONG).show();
        }

        displayQuantity(quantity);
    }

    /**
     * This method subtracts 1 from the quantity
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
        }  else {
            Toast.makeText(getApplicationContext(), "You must order at least 1 coffee.",
                    Toast.LENGTH_LONG).show();
        }

        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Finds the status of the whipped cream checkbox
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Finds the status of the chocolate checkbox
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Finds the value of the name EditText
        EditText nameEntered = (EditText) findViewById(R.id.name_text_edit);
        String hasName = nameEntered.getText().toString();

        // Calculates the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Sends these values to the createOrderSummary method
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, hasName);
        displayMessage(priceMessage);

        emailMessage = priceMessage;

        /**
         * Intent for going to a location on Google maps.
         */


    }


    public void mapLocation(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:38.778370, -89.988526"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void emailOrder(View view) {
        /**
         * Intent used to email the order summary.
         */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "katrinareed93@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order");
        intent.putExtra(Intent.EXTRA_TEXT, emailMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
            orderSummaryTextView.setText(null);
        }
    }

    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int coffeePrice = 5;

        int toppingPrice = 0;

        if (addWhippedCream) {
            if (addChocolate) {
                toppingPrice = 3;
            } else
                toppingPrice = 1;
        } else if (addChocolate) {
            toppingPrice = 2;
        } else {
            toppingPrice = 0;
        }
        return quantity * (coffeePrice + toppingPrice);
    }

    /**
     * Create summary of the order.
     *
     * @param price
     * @return
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String userName) {
        String priceMessage = "Name: " + userName;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }




}