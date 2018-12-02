package org.cptjmg.consultaprocesso.ui.busca;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.camerakit.CameraKitView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.ui.ProcessoViewModel;
import org.cptjmg.consultaprocesso.util.CommonUtils;

import java.util.Timer;
import java.util.TimerTask;

public class CameraFragment extends Fragment {

    private CameraKitView cameraKitView;
    private ProcessoViewModel processoViewModel;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    TimerTask timerTask;
    private boolean textDetectionInitialized = false;

    public CameraFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processoViewModel = ViewModelProviders.of(getActivity()).get(ProcessoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        cameraKitView = view.findViewById(R.id.camView);

        timerTask = new TimerTask(){
            @Override
            public void run(){

                if(textDetectionInitialized) return;

                cameraKitView.captureImage(new  CameraKitView.ImageCallback() {
                    @Override
                    public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {

                        textDetectionInitialized = true;

                        callFirebaseRecognition(capturedImage);
                    }
                });

            }
        };

        cameraKitView.setPreviewListener(new CameraKitView.PreviewListener() {
            @Override
            public void onStart() {
                new Timer().scheduleAtFixedRate(timerTask,0,1500);
            }

            @Override
            public void onStop() {

            }
        });

        cameraKitView.setErrorListener(new CameraKitView.ErrorListener() {
            @Override
            public void onError(CameraKitView cameraKitView, CameraKitView.CameraException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERRO DE CAMERA", e.getMessage());
            }
        });

        return view;
    }

    private void callFirebaseRecognition(byte[] capturedImage) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(capturedImage, 0, capturedImage.length);

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);

        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();

        textRecognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText result) {
                        String text = result.getText().trim().replaceAll("[^\\d]", "");

                        if(text.length() == 17 || text.length() == 20) {
                            CommonUtils.vibrate(getContext(), 150);
                            processoViewModel.numProcesso.set(text);
                            ((AppCompatActivity) getActivity()).getSupportFragmentManager().popBackStack();
                        } else {
                            textDetectionInitialized = false;
                        }
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                textDetectionInitialized = false;
                            }
                        });
    }

    @Override
    public void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraKitView.onResume();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onPause() {
        cameraKitView.onPause();
        super.onPause();

        timerTask.cancel();
    }

    @Override
    public void onStop() {
        cameraKitView.onStop();
        super.onStop();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        timerTask.cancel();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
