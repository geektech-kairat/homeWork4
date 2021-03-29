package com.example.lesson22.ui.form;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lesson22.databinding.FragmentFormBinding;

public class FormFragment extends Fragment {
    private @NonNull
    FragmentFormBinding binding;
    private NavController navController;
    private String s = "Поле не должен быть пустым ";


    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentFormBinding.inflate(inflater, container, false);
        getData();
        initListeners();

        return binding.getRoot();
    }

    public void initListeners() {

        binding.saveButton.setOnClickListener(v -> {
            if (binding.nameItem.getText().toString().isEmpty() && binding.numberItem.getText().toString().isEmpty()) {
                binding.nameItem.setError(s);
                binding.numberItem.setError(s);
            } else if (binding.nameItem.getText().toString().isEmpty()) {
                binding.nameItem.setError(s);
            } else if (binding.numberItem.getText().toString().isEmpty()) {
                binding.numberItem.setError(s);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("name", binding.nameItem.getText().toString());
                bundle.putString("number", binding.numberItem.getText().toString());
                bundle.putInt("id", id);
                getParentFragmentManager().setFragmentResult("key", bundle);
                close();
            }
        });


    }

    public void getData() {
        getParentFragmentManager().setFragmentResultListener("2", getViewLifecycleOwner(), new FragmentResultListener() {


            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (requestKey.equals("2") && result != null)
                    Log.e("TAG", "onFragmentResult:  " + result.getString("number1"));
                binding.numberItem.setText(result.getString("number1"));
                binding.nameItem.setText(result.getString("name1"));
                id = result.getInt("id");
            }
        });
    }

    private void close() {
        navController.navigateUp();
    }
}