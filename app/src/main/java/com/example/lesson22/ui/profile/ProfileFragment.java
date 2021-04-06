package com.example.lesson22.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lesson22.App;
import com.example.lesson22.MainActivity;
import com.example.lesson22.R;
import com.example.lesson22.databinding.FragmentProfileBinding;
import com.google.android.material.snackbar.Snackbar;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private NavController navController;
    private ActivityResultLauncher<String> mGetContent;
    private Intent uri;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.editText.setText(App.share.getForName());
        click();
        return binding.getRoot();
    }


    @SuppressLint("WrongConstant")
    private void click() {
        getImage();
        binding.saveBtn.setOnClickListener(v -> {
//            Snackbar.make(v, "Вы сохранили", Snackbar.LENGTH_SHORT).show();

            View CustomToast = requireActivity().getLayoutInflater().inflate(R.layout.toast, null);
            Toast toast = new Toast(requireContext());
            toast.setView(CustomToast);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.getGravity();
            toast.show();

            App.share.setForName(binding.editText.getText().toString());
        });
    }

    private void getImage() {
        imageView = binding.image;
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment.this.openGallery();
            }
        });
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> imageView.setImageURI(uri));
    }

    private void openGallery() {
        mGetContent.launch("image/*");

    }

}