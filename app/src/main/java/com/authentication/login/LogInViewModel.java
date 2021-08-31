package com.authentication.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public void logIn(String email, String password)
    {
        firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            stringMutableLiveData.setValue("Done Login");
                        }
                        else
                        {
                            stringMutableLiveData.setValue("Failure Login");
                        }
                    }
                });
    }
}