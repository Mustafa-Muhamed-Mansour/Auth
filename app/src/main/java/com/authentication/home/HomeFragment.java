package com.authentication.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.authentication.R;
import com.authentication.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment
{

    private HomeFragmentBinding binding;
    private HomeViewModel homeViewModel;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);


    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

    }
}