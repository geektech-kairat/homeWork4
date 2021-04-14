package com.example.lesson22.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lesson22.App;
import com.example.lesson22.R;
import com.example.lesson22.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private NavController navController;
    private ActivityResultLauncher<String> mGetContent;
    private Intent uri;
    private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

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

            View CustomToast = requireActivity().getLayoutInflater().inflate(R.layout.toast, null);
            Toast toast = new Toast(requireContext());
            toast.setView(CustomToast);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.getGravity();
            toast.show();

            App.share.setForName(binding.editText.getText().toString().trim());
        });
    }

    private void getImage() {
        imageView = binding.image;
        binding.image.setOnClickListener(v ->
                ProfileFragment.this.openGallery());
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        imageView.setImageURI(uri);
                    }
                });
    }

    private void openGallery() {
        mGetContent.launch("image/*");

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fiil, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.app_bar_switch){
            Toast.makeText(requireContext(), "asdsadsadsadsadsa", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}