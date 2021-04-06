package com.example.lesson22.ui.profile;

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

import com.example.lesson22.R;
import com.example.lesson22.databinding.FragmentProfileBinding;


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
        imageView = binding.image;
        binding.image.setOnClickListener(v -> {

            openGallery();
        });


//        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == requireActivity().RESULT_OK) {
//
//                            uri = result.getData();
//                            Log.e("TAG", "onActivityResult: " + result.getData());
//                        }
//                    }
//                });
        String a ;
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        imageView.setImageURI(uri);
                    }
                });

        return binding.getRoot();
    }


    private void openGallery() {
        mGetContent.launch("image/*");

    }
}