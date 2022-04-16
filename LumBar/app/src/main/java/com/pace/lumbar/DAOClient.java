package com.pace.lumbar;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOClient {

    private DatabaseReference databaseReference;

    public DAOClient(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Client.class.getSimpleName());
    };

    public Task<Void> add(Client newClient){
        return databaseReference.push().setValue(newClient);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashmap) {
        return databaseReference.child(key).updateChildren(hashmap);
    }
    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }


}
