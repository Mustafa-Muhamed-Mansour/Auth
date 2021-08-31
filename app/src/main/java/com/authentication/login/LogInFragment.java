package com.authentication.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.authentication.databinding.LogInFragmentBinding;

public class LogInFragment extends Fragment
{

    private LogInFragmentBinding binding;
    private NavController navController;
    private LogInViewModel logInViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = LogInFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        logInViewModel = new ViewModelProvider(this).get(LogInViewModel.class);

        binding.btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = binding.editUserEmailLogin.getText().toString();
                String password = binding.editUserPasswordLogin.getText().toString();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    binding.editUserEmailLogin.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(getContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                    binding.editUserPasswordLogin.requestFocus();
                    return;
                }

                else
                {
                    logInViewModel.logIn(email, password);
                }
            }
        });

        logInViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Done Login"))
                {
                    Toast.makeText(getContext(), "Sucessfully in Login", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_logInFragment_to_homeFragment);
                }
                else
                {
                    Toast.makeText(getContext(), "Failure in Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        logInViewModel.stringMutableLiveData.removeObservers(getViewLifecycleOwner());
    }
}