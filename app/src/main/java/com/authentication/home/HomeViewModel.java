package com.authentication.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.authentication.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel
{

    private ArrayList<UserModel> userModels = new ArrayList<>();
    private DatabaseReference retriveRef = FirebaseDatabase.getInstance().getReference();
    private MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<UserModel>> userModelMutableLiveData = new MutableLiveData<>();

    public void retriveData()
    {
        retriveRef
                .child("Users")
                .addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        userModels.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            UserModel userModel = dataSnapshot.getValue(UserModel.class);
                            userModels.add(userModel);
                        }
                        userModelMutableLiveData.setValue(userModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        stringMutableLiveData.setValue(error.getMessage());
                    }
                });
    }

}