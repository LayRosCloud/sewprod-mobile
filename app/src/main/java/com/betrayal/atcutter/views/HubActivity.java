package com.betrayal.atcutter.views;

import android.os.Bundle;
import android.util.Log;

import com.betrayal.atcutter.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.betrayal.atcutter.databinding.ActivityHubBinding;

import java.util.HashMap;
import java.util.Map;

public class HubActivity extends AppCompatActivity {
    private ActivityHubBinding binding;
    private final Map<Integer, Fragment> fragmentMap;

    public HubActivity(){
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.navigation_cutter, new PackageFragment());
        fragmentMap.put(R.id.navigation_profile, new ProfileFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView view = binding.bottomNavView;

        view.setOnNavigationItemSelectedListener(item -> {
            //Log.d("bottom_nav_log", String.valueOf(item.getItemId()));
            Fragment fragment = fragmentMap.get(item.getItemId());
            loadFragment(fragment);
            return true;
        });
    }
    private void loadFragment(Fragment fragment){
        loadFragment(fragment, true);
    }

    private void loadFragment(Fragment fragment, boolean applicationInitialized){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_hub,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}