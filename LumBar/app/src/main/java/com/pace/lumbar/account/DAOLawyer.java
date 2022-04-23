package com.pace.lumbar.account;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOLawyer {

    private DatabaseReference databaseReference;

    public DAOLawyer(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Lawyer.class.getSimpleName());
    };

    public Task<Void> add(Lawyer newLawyer){
        return databaseReference.push().setValue(newLawyer);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashmap) {
        return databaseReference.child(key).updateChildren(hashmap);
    }
    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }


}