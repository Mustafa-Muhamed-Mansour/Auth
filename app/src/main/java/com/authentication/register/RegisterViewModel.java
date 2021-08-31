package com.authentication.register;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.authentication.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterViewModel extends ViewModel
{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();
    private StorageReference userImageRef = FirebaseStorage.getInstance().getReference().child("Images").child("randomImage");
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();


    public void addUser(String userImage, String userName, String userEmail, String userPassword, String userPhone)
    {

        firebaseAuth
                .createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            stringMutableLiveData.setValue("Done Register");

                            String userID = firebaseAuth.getCurrentUser().getUid();
                            userImageRef.putFile(Uri.parse(userImage)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                            {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    userImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                    {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            UserModel userModel = new UserModel(userID, uri.toString(), userName, userEmail, userPhone);
                                            userRef.child("Users").child(userID).setValue(userModel);
                                        }
                                    });
                                }
                            });
                        }

                        else
                        {
                            stringMutableLiveData.setValue("Failure Register");
                        }
                    }
                });
    }
}