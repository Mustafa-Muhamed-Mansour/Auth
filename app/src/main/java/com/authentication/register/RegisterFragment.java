package com.authentication.register;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.authentication.R;
import com.authentication.databinding.RegisterFragmentBinding;
import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class RegisterFragment extends Fragment
{

    private RegisterFragmentBinding binding;
    private NavController navController;
    private RegisterViewModel registerViewModel;
    private String userImage;
    private Uri imagetUri;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = binding.editUserNameRegister.getText().toString();
                String email = binding.editUserEmailRegister.getText().toString();
                String password = binding.editUserPasswordRegister.getText().toString();
                String phone = binding.editUserPhoneRegister.getText().toString();

                if (TextUtils.isEmpty(name))
                {
                    Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    binding.editUserNameRegister.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    binding.editUserEmailRegister.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(getContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                    binding.editUserPasswordRegister.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phone))
                {
                    Toast.makeText(getContext(), "Please enter your phone", Toast.LENGTH_SHORT).show();
                    binding.editUserPhoneRegister.requestFocus();
                    return;
                }

                else
                {
                    registerViewModel.addUser(userImage, name, email, password, phone);
                }
            }
        });

        registerViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Done Register"))
                {
                    Toast.makeText(getContext(), "Sucessfully in Register", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_registerFragment_to_logInFragment);
                }
                else
                {
                    Toast.makeText(getContext(), "Failure in Register", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.imgUserProfileRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openGallery();
            }
        });

    }

    private void openGallery()
    {
        CropImage
                .activity()
                .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                .start(getActivity(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        CropImage.ActivityResult result = CropImage.getActivityResult(data);

        if (resultCode == RESULT_OK && requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && data != null)
        {
            imagetUri = result.getUri();
            userImage = imagetUri.toString();
        }

        else
        {
            Toast.makeText(getContext(), result.getError().getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        Glide
                .with(getActivity())
                .load(imagetUri)
                .into(binding.imgUserProfileRegister);
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        registerViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}