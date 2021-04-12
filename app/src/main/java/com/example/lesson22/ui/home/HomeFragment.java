package com.example.lesson22.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lesson22.App;
import com.example.lesson22.MainActivity;
import com.example.lesson22.R;
import com.example.lesson22.databinding.FragmentHomeBinding;
import com.example.lesson22.ui.home.HomeAdapter.HomeAdapter;
import com.example.lesson22.ui.home.HomeAdapter.HomeModel;
import com.example.lesson22.ui.home.HomeAdapter.Listen;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Listen {


    private NavController navController;
    private FragmentHomeBinding binding;
    private HomeAdapter homeAdapter;
    private List<HomeModel> list = new ArrayList<>();
    private int id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        App.fillDatabase.fillDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(List<HomeModel> homeModelList) {
                homeAdapter.addList(homeModelList);
            }
        });




        binding.rv.setAdapter(homeAdapter);
        click();
        getDataInForm();


        requireActivity().getOnBackPressedDispatcher().
                addCallback(
                        getViewLifecycleOwner(),
                        new OnBackPressedCallback(true) {
                            @Override
                            public void handleOnBackPressed() {
                                alert();
                            }
                        });
        return binding.getRoot();
    }

    private void getDataInForm() {
        //добавление
        getParentFragmentManager().setFragmentResultListener("key",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String a = result.getString("name");
                        String b = result.getString("number");
                        id = result.getInt("id");

                        HomeModel model = homeAdapter.getModelToId(id);
                        if (model != null) {
                            model.setName(a);
                            model.setNumber(b);
                            App.fillDatabase.fillDao().update(model);
                        } else {
                            App.fillDatabase.fillDao().insert(new HomeModel(a, b));
                        }
                    }
                });
    }

    @Override
    //ОТПРАВЛЕНИЕ ДАННЫХ
    public void setDataForForm(HomeModel homeModel, int position) {
        Bundle bundle = new Bundle();

        bundle.putString("name1", homeModel.getName());
        bundle.putString("number1", homeModel.getNumber());
        bundle.putInt("id", homeModel.getId());
        getParentFragmentManager().setFragmentResult("2", bundle);
        navController.navigate(R.id.action_navigation_home_to_profileFragment);
    }

    @Override
    public void del(int position) {

        AlertDialog.Builder adg = new AlertDialog.Builder(binding.getRoot().getContext());
        String positive = "Да";
        String negative = "Нет";
        adg.setMessage("Вы хотите удалить ?");
        adg.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HomeModel model = homeAdapter.getHomeByPosition(position);
                App.fillDatabase.fillDao().delete(model);
                homeAdapter.notifyItemChanged(position);


            }
        });
        adg.setNegativeButton(negative, null);
        adg.show();

    }


    public void click() {
        binding.fabAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_home_to_profileFragment);
        });


//
//        binding.sort.setOnClickListener(v -> {
//            homeAdapter.addList(App.fillDatabase.fillDao().getAllBySort());
//            binding.rv.setAdapter(homeAdapter);
//            App.share.saveSort(true);
//        });
//        if (App.share.getSaveSort()){
//            Log.e("TAG", "click: " + App.share.getSaveSort() );
//            homeAdapter.addList(App.fillDatabase.fillDao().getAllBySort());
//            binding.rv.setAdapter(homeAdapter);
//        }
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
}