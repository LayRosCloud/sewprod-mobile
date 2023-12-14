package com.betrayal.atcutter.views;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.extensions.HdrImageCaptureExtender;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.databinding.FragmentQrCodeBinding;
import com.betrayal.atcutter.scripts.QRCodeDecoder;
import com.google.common.util.concurrent.ListenableFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class QrCodeFragment extends Fragment {
    private FragmentQrCodeBinding binding;
    private final int SUSPENSION_TIME = 2000;
    private final int REQUEST_CODE_PERMISSIONS = 5555;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA",};
    private PreviewView view;
    public boolean isProcess;
    public QrCodeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQrCodeBinding.inflate(inflater);
        View view = binding.getRoot();
        this.view = binding.camera;
        binding.backToAuth.setOnClickListener(this::back);
        return view;
    }

    private boolean allPermissionsGranted(){
        for (String permission: REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_PERMISSIONS){
            if(allPermissionsGranted()){
                startCamera();
            }else{
                getActivity().finish();
            }
        }
    }

    private void back(View v){
        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.navigate(R.id.action_qrCodeFragment_to_authFragment);
    }

    public void handleQrCode(String qrCodeText){
        getActivity().runOnUiThread(()->{
            handleResponse(qrCodeText);
        });

        startCoolDownCheckQrCode();
    }

    private void handleResponse(String response){
        Context context = getContext();

        final String keyEmail = "email";
        final String keyPassword = "password";
        final String jsonErrorMessage = "Ошибка! Недействительный формат QR-кода";

        try {
            JSONObject jsonObject = new JSONObject(response);
            NavController navController = Navigation.findNavController(binding.getRoot());
            Bundle bundle = new Bundle();
            bundle.putString(keyEmail, jsonObject.getString(keyEmail));
            bundle.putString(keyPassword, jsonObject.getString(keyPassword));
            navController.navigate(R.id.action_qrCodeFragment_to_authFragment, bundle);
        } catch (JSONException e) {
            Toast.makeText(context, jsonErrorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private void startCoolDownCheckQrCode(){
        new Thread(()->{
            try{
                Thread.sleep(SUSPENSION_TIME);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            isProcess = false;
        }).start();
    }

    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider){
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().build();
        imageAnalysis.setAnalyzer(Executors.newFixedThreadPool(1), new QRCodeDecoder(this));

        ImageCapture.Builder builder = new ImageCapture.Builder();

        HdrImageCaptureExtender hdrImageCaptureExtender = HdrImageCaptureExtender.create(builder);

        if(hdrImageCaptureExtender.isExtensionAvailable(cameraSelector)){
            hdrImageCaptureExtender.enableExtension(cameraSelector);
        }

        Preview preview = new Preview.Builder().build();

        ImageCapture capture = builder
                .setTargetRotation(getActivity().getWindowManager().getDefaultDisplay().getRotation())
                .build();

        preview.setSurfaceProvider(view.createSurfaceProvider());

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis, capture);

    }

    private void startCamera(){
        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture
                = ProcessCameraProvider.getInstance(getContext());

        cameraProviderFuture.addListener(()->{
            bindCameraPreview(cameraProviderFuture);
        }, ContextCompat.getMainExecutor(getContext()));
    }

    private void bindCameraPreview(ListenableFuture<ProcessCameraProvider> cameraProviderFuture){
        try{
            ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
            bindPreview(cameraProvider);
        }catch (ExecutionException | InterruptedException ex){
            ex.printStackTrace();
        }
    }
}