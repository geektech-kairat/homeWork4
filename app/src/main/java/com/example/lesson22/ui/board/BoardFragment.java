package com.example.lesson22.ui.board;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson22.App;
import com.example.lesson22.R;
import com.example.lesson22.databinding.FragmentBoardBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class BoardFragment extends Fragment implements Click {

    private FragmentBoardBinding binding;
    private NavController navController;
    private BoardAdapter boardAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardAdapter = new BoardAdapter(this);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        navController = NavHostFragment.findNavController(this);
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        App.share.saveBoardShown(true);

        binding.pager.setAdapter(boardAdapter);
        binding.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    binding.getIt.setVisibility(View.VISIBLE);
                    binding.backBtn.setVisibility(View.VISIBLE);
                    binding.nextBtn.setVisibility(View.INVISIBLE);
                } else if (position == 0) {
                    binding.backBtn.setVisibility(View.INVISIBLE);
                    binding.getIt.setVisibility(View.INVISIBLE);
                } else {
                    binding.getIt.setVisibility(View.INVISIBLE);
                    binding.backBtn.setVisibility(View.VISIBLE);
                    binding.nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        clickItem();

        binding.indicator.setViewPager(binding.pager);
        boardAdapter.registerAdapterDataObserver(binding.indicator.getAdapterDataObserver());

        onBackPressedCallBack();

        return binding.getRoot();
    }

    private void onBackPressedCallBack() {
        requireActivity().getOnBackPressedDispatcher().
                addCallback(
                        getViewLifecycleOwner(),
                        new OnBackPressedCallback(true) {
                            @Override
                            public void handleOnBackPressed() {
                                alert();
                            }
                        });
    }

    @Override
    public void click() {

    }

    public void alert() {
        AlertDialog.Builder adg = new AlertDialog.Builder(binding.getRoot().getContext());
        String positive = "Да";
        String negative = "Нет";
        adg.setMessage("Вы хотите выйти ?");
        adg.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requireActivity().finish();
            }
        });
        adg.setNegativeButton(negative, null);
        adg.show();
    }

    public void clickItem() {
        binding.nextBtn.setOnClickListener(v -> {
            binding.pager.setCurrentItem(binding.pager.getCurrentItem() + 1);
        });

        binding.backBtn.setOnClickListener(v -> {
            binding.pager.setCurrentItem(binding.pager.getCurrentItem() - 1);

        });
        binding.getIt.setOnClickListener(v -> {
//            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                navController.navigate(R.id.authFragment);
//            }else {
//                navController.navigateUp();
//                Log.e("TAG", "onPageSelected:  ");
//            }
        });
    }

}