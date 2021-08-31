package com.authentication.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.authentication.R;
import com.authentication.adapter.UserAdapter;
import com.authentication.databinding.HomeFragmentBinding;
import com.authentication.model.UserModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{

    private HomeFragmentBinding binding;
    private HomeViewModel homeViewModel;
    private UserAdapter userAdapter;

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

        homeViewModel.userModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<UserModel>>()
        {
            @Override
            public void onChanged(ArrayList<UserModel> userModels)
            {
                userAdapter = new UserAdapter(userModels);
                binding.rV.setAdapter(userAdapter);
                binding.rV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.rV.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();

        homeViewModel.retriveData();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        homeViewModel.userModelMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}