package com.example.gevyummfb;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
 * In this Activity, the user completes their food order, by choosing a food company and entering
 * their Identification Number.
 */

public class FinishOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner FC;
    EditText getCard;
    String idWork;
    Meal food;
    int spinPos;
    ArrayAdapter<String> adp;
    ValueEventListener compListener, workListener;
    Company fc;
    Worker wv;
    Order ord;
    ArrayList<Company> compValues;
    boolean tost;
    Intent si;
    Intent gi;


    /**
     * Sets up the necessary information to complete a food order. Sets up a Spinner Widget with
     * all of the active Food Companies.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_order);

        gi = getIntent();
        food = (Meal) gi.getSerializableExtra("food");
        getCard = findViewById(R.id.getCard);
        FC = findViewById(R.id.chooseFC);
        Query query = FBref.refFCs.orderByChild("active").equalTo("0");

        ArrayList<String> compList = new ArrayList<String>();
        compValues = new ArrayList<Company>();
        compListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                compList.clear();
                compList.add("Choose a Food Company");
                compValues.clear();
                for (DataSnapshot data : dS.getChildren()) {
                    fc = data.getValue(Company.class);
                    compValues.add(fc);
                    compList.add(fc.getName());
                }
                adp = new ArrayAdapter<>(FinishOrder.this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, compList);
                FC.setAdapter(adp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        query.addValueEventListener(compListener);
        FC.setOnItemSelectedListener(this);


    }

    /**
     * Removes the active ChangedEventListeners for all Branches in the Database.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (workListener != null) {
            FBref.refWorkers.removeEventListener(workListener);
        }
        if (compListener != null) {
            FBref.refFCs.removeEventListener(compListener);
        }

    }

    /**
     * The On-Click method of the SUBMIT Button Widget.
     * Checks if the user has selected a Food Company and has entered a card number of an active worker.
     * If so, completes the order and inserts new rows into the Orders and Meals tables.
     *
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void submit(View view) {
        tost = false;
        if (spinPos == 0)
            Toast.makeText(this, "Please Choose A Food Company", Toast.LENGTH_LONG).show();
        else {
            idWork = getCard.getText().toString();
            ord = new Order();
            workListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        if (data.getValue(Worker.class).getActive().equals("0")) {
                            if (data.getValue(Worker.class).getId().equals(idWork)) {
                                tost = true;
                                wv = data.getValue(Worker.class);
                                ord.setWv(wv);
                                ord.setNameFC(wv.getFirstName() + wv.getLastName());
                                ord.setFc(fc);
                                ord.setNameFC(fc.getName());
                                ord.setDate(java.time.LocalDate.now().toString());
                                ord.setMl(food);
                                ord.setTime(String.valueOf(java.time.LocalTime.now()).substring(0, 8));
                                String title = ord.getWv().getFirstName();
                                title += " " + ord.getWv().getLastName();
                                title += " - " + ord.getWv().getId();
                                title += ": " + ord.getFc().getName();
                                title += " | " + ord.getDate();


                                FBref.refOrders.child(title).setValue(ord);
                                Toast.makeText(FinishOrder.this, "Order Made Successfully!", Toast.LENGTH_LONG).show();
                                si = new Intent(FinishOrder.this, MainActivity.class);
                                startActivity(si);
                                break;
                            }
                        }

                    }
                    if (!tost)
                        Toast.makeText(FinishOrder.this, "Card Number Does Not Match Any Active Worker", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            FBref.refWorkers.addListenerForSingleValueEvent(workListener);
        }
    }


    /**
     * The onItemSelected Listener for the food companies Spinner Widget.
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinPos = i;
        if (i != 0) {
            fc = compValues.get(i - 1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
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