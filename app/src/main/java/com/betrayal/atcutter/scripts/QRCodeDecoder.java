package com.betrayal.atcutter.scripts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.betrayal.atcutter.views.QrCodeFragment;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class QRCodeDecoder implements ImageAnalysis.Analyzer {

    private final BarcodeScannerOptions options;
    private final BarcodeScanner scanner;
    private final QrCodeFragment context;

    public QRCodeDecoder(QrCodeFragment context) {
        this.context = context;
        options = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build();
        scanner = BarcodeScanning.getClient();
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {
        @SuppressLint("UnsafeOptInUsageError")
        Image mediaImage = image.getImage();

        if(mediaImage == null){
            return;
        }

        int rotationDeg = image.getImageInfo().getRotationDegrees();
        InputImage inputImage = InputImage.fromMediaImage(mediaImage, rotationDeg);

        Task<List<Barcode>> result = scanner.process(inputImage);
        result.addOnSuccessListener(barcodes -> {
            if(barcodes.size() > 0){
                final int idElement = 0;

                Barcode.UrlBookmark urlBookmark = barcodes.get(idElement).getUrl();

                String url = null;

                try{
                    url = urlBookmark.getUrl();
                }
                catch (Exception ex){
                    url = barcodes.get(idElement).getDisplayValue();
                }

                if( !context.isProcess && url != null){
                    context.isProcess = true;
                    context.handleQrCode(url);
                }

            }

            image.close();
        });
        result.addOnFailureListener(e -> image.close());
    }
}
