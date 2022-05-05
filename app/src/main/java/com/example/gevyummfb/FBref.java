package com.example.gevyummfb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author : Harel Navon harelnavon2710@gmail.com
 * @version : 2.0
 * @since : 5.5.2022
 * This is the Java class that contains the references for each main Branch in the database .
 */


public class FBref {

    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();

    public static DatabaseReference refWorkers = FBDB.getReference("Workers");
    public static DatabaseReference refFCs = FBDB.getReference("Food Companies");
    public static DatabaseReference refOrders = FBDB.getReference("Orders");
}
