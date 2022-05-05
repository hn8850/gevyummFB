package com.example.gevyummfb;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : Harel Navon harelnavon2710@gmail.com
 * @version : 2.0
 * @since : 5.5.2022
 * This is the credits Activity.
 */

public class credits extends AppCompatActivity {

    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
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
            si = new Intent(this, com.example.gevyummfb.MainActivity.class);
        } else if (id == R.id.order) {
            si = new Intent(this, com.example.gevyummfb.GetOrder.class);
        } else if (id == R.id.infoOrder) {
            si = new Intent(this, com.example.gevyummfb.showOrder.class);
        } else if (id == R.id.setting) {
            si = new Intent(this, com.example.gevyummfb.ShowInfo.class);
        } else {
            si = new Intent(this, credits.class);
        }
        startActivity(si);
        return true;
    }


}