package com.authentication.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterViewModel extends ViewModel
{
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();


}