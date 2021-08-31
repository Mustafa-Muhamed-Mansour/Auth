package com.authentication.register;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.authentication.R;
import com.authentication.databinding.RegisterFragmentBinding;

public class RegisterFragment extends Fragment
{

    private RegisterFragmentBinding binding;
    private NavController navController;
    private RegisterViewModel registerViewModel;


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


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

    }
}