package com.example.gevyummfb;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * @author : Harel Navon harelnavon2710@gmail.com
 * @version : 2.0
 * @since : 5.5.2022
 * In this Activity, the user can view all of the previous orders that were made.
 */

public class showOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    TextView order;
    ActionBar aBar;
    ColorDrawable cd;

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    TextView tvAPP, tvMAIN, tvSIDE, tvDES, tvDRI, tvTIME, tvDATE;

    ListView lv;
    ArrayAdapter<String> adp;
    ArrayAdapter<String> sortADP;
    Spinner sortSP;
    int sortPos;
    String[] sort;


    String tmp;
    ValueEventListener ordListener;

    Intent si;

    /**
     * Sets up all of the Widgets in the Activity.
     * Then the SortOrder function is called, and the information about all previous orders is
     * displayed.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);

        aBar = getSupportActionBar();
        cd = new ColorDrawable(getResources().getColor(R.color.order));
        aBar.setBackgroundDrawable(cd);


        order = findViewById(R.id.textView);

        tv1 = findViewById(R.id.textView4);
        tv2 = findViewById(R.id.textView5);
        tv3 = findViewById(R.id.textView6);
        tv4 = findViewById(R.id.textView7);
        tv5 = findViewById(R.id.textView8);
        tv6 = findViewById(R.id.textView3);
        tv7 = findViewById(R.id.textView15);

        tvAPP = findViewById(R.id.textView9);
        tvMAIN = findViewById(R.id.textView10);
        tvSIDE = findViewById(R.id.textView11);
        tvDES = findViewById(R.id.textView12);
        tvDRI = findViewById(R.id.textView13);
        tvTIME = findViewById(R.id.textView14);
        tvDATE = findViewById(R.id.textView16);

        sortSP = findViewById(R.id.sortSP);
        lv = findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        sort = new String[]{"Date", "Worker", "Food Company"};
        sortADP = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sort);
        sortSP.setAdapter(sortADP);
        sortSP.setOnItemSelectedListener(this);
        sortPos = 0;

        sortOrder("Name");
    }

    /**
     * Removes the active ChangedEventListeners for all Branches in the Database.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (ordListener != null) {
            FBref.refWorkers.removeEventListener(ordListener);
        }
    }


    /**
     * Sorts the information read from the Orders table in the database,
     * based on what the user has selected in the Spinner Widget.
     *
     * @param category : Determines what category will the information presented to the user  be
     *                 sorted by, based on their choice in the SortSP Spinner Widget.
     */
    public void sortOrder(String category) {
        ArrayList<String> orders = new ArrayList<String>();
        Query query;
        if (category.equals("Name")) {
            query = FBref.refOrders.orderByChild("nameWV");
        } else if (category.equals("Company")) {
            query = FBref.refOrders.orderByChild("nameFC");
        } else {
            query = FBref.refOrders.orderByChild("date");
        }
        ordListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                orders.clear();
                for (DataSnapshot data : dS.getChildren()) {
                    orders.add(data.getKey());
                }
                adp = new ArrayAdapter<String>(showOrder.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orders);
                lv.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query.addValueEventListener(ordListener);
    }


    /**
     * The listener for items selected in the SortSP Spinner Widget.
     * Reads, filters and sorts the information from the Orders table.
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sortPos = i;
        if (sortPos == 0) sortOrder("Date");
        else if (sortPos == 1) sortOrder("Name");
        else if (sortPos == 2) sortOrder("Company");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /**
     * The listener for the ListView in which the information is presented.
     * When the user clicks a cell in the list, more information about that order will be presented
     * in the bottom of the screen.
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tmp = lv.getItemAtPosition(i).toString();

        if (tv1.getVisibility() == View.INVISIBLE) {
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            tv4.setVisibility(View.VISIBLE);
            tv5.setVisibility(View.VISIBLE);
            tv6.setVisibility(View.VISIBLE);
            tv7.setVisibility(View.VISIBLE);
        }

        order.setText("Order Number " + String.valueOf(i + 1));

        ordListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                for (DataSnapshot data : dS.getChildren()) {
                    if (data.getKey().equals(tmp)) {
                        Order ord = data.getValue(Order.class);
                        Meal ml = ord.getMl();
                        tvAPP.setText(ml.getAppetizer());
                        tvMAIN.setText(ml.getMainDish());
                        tvSIDE.setText(ml.getSide());
                        tvDES.setText(ml.getDessert());
                        tvDRI.setText(ml.getDrink());
                        tvTIME.setText(ord.getTime());
                        tvDATE.setText(ord.getDate());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        FBref.refOrders.addValueEventListener(ordListener);

    }


    /**
     * On Click method of the back button. Takes the user back to the Main Activity.
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * Creates the General Options Menu (menu.xml) for this Activity.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * Sends the user over to the Activity chosen in the Options Menu.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mainhome) {
            si = new Intent(this, MainActivity.class);
        } else if (id == R.id.order) {
            si = new Intent(this, com.example.gevyummfb.GetOrder.class);
        } else if (id == R.id.infoOrder) {
            si = new Intent(this, showOrder.class);
        } else if (id == R.id.setting) {
            si = new Intent(this, com.example.gevyummfb.ShowInfo.class);
        } else {
            si = new Intent(this, credits.class);
        }
        startActivity(si);
        return true;
    }

}