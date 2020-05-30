/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import java.lang.String;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
   int n=0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int a = price(n, 10);
        CheckBox hasWhhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean whippedCream = hasWhhippedCream.isChecked();
        if (whippedCream) {
            a = a + (n * 1);
        }
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.Chocolate);
        boolean chocolate = hasChocolate.isChecked();
        if (chocolate) {
            a = a + (n * 2);
        }
        TextView Name = (TextView) findViewById(R.id.name);
        String customerName = Name.getText().toString();
        String str = orderSummary(n, a, whippedCream, chocolate, customerName);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this

            intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order by "+customerName);
        intent.putExtra(Intent.EXTRA_TEXT, str);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        //displaySummary(str);
    }
//    private void displaySummary(String str)
//    {
//        TextView ordersummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        ordersummaryTextView.setText(str);
//    }
    public void Increase(View view) {
        if (n < 100) {
            n++;
            display(n);
        }
    }
    public void Decrease(View view)
    {
        if(n>0) {
            n--;
            display(n);
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private String orderSummary(int quantity, int price,boolean addWhippedCream,boolean addChocolate,String customerName) {
       String str=getString(R.string.Name)+":"+customerName;
       str+="\n"+getString(R.string.quantity)+":"+quantity;
       str+= "\n"+getString(R.string.addWhippedCream)+"?" ;
       str+=addWhippedCream;
       str+="\n"+getString(R.string.Chocolate)+"?"+addChocolate;
       str+="\n"+getString(R.string.totalPrice)+":$"+price;
       str+="\n"+getString(R.string.thankyou)+"!!";
       return str;
    }
    private int price(int number,int pricee) {
        number=number*pricee;
        return number;
    }
}