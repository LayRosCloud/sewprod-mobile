package com.betrayal.atcutter.views;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.databinding.FragmentQrCodeBinding;
import com.betrayal.atcutter.scripts.QrCodeDecode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;

public class QrCodeFragment extends Fragment {
    private FragmentQrCodeBinding binding;
    private CodeScanner scanner;

    private static final String REQUEST_PERMISSION = "android.permission.CAMERA";
    private static final int REQUEST_CODE = 2_132;

    public QrCodeFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQrCodeBinding.inflate(inflater);
        View view = binding.getRoot();
        CodeScannerView scannerView = binding.scanner;
        scanner = new CodeScanner(getContext(), scannerView);

        scanner.setDecodeCallback(new QrCodeDecode(getActivity(),view));

        scannerView.setOnClickListener(v -> {
            scanner.startPreview();
        });
        binding.backToAuth.setOnClickListener(this::back);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        scanner.startPreview();
    }

    @Override
    public void onPause() {
        super.onPause();
        scanner.stopPreview();
    }

    private void checkPermissions(){
        final String[] requestPermissions = new String[]{
                REQUEST_PERMISSION
        };
        Activity activity = getActivity();

        if(activity.checkSelfPermission(REQUEST_PERMISSION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, requestPermissions, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode != REQUEST_CODE){
            checkPermissions();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void back(View v){
        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.navigate(R.id.action_qrCodeFragment_to_authFragment);
    }
}