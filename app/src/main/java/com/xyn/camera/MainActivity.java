package com.xyn.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import com.xyn.camera.view.CameraTextureView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CameraTextureView mCameraTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCameraTextureView = findViewById(R.id.camera_text_view);
        mCameraTextureView.setPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                mCameraTextureView.addCallbackBuffer();

            }
        });
        mCameraTextureView.startPreview();

        findViewById(R.id.open_camera_btn).setOnClickListener(this);
        findViewById(R.id.close_camera_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.open_camera_btn:
                openCamera();
                break;
            case R.id.close_camera_btn:
                closeCamera();
                break;
            default:
                break;
        }
    }

    private void openCamera() {

        mCameraTextureView.setVisibility(View.VISIBLE);
        mCameraTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Log.i("Main", "onSurfaceTextureAvailable");
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Log.i("Main", "onSurfaceTextureSizeChanged");
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Log.i("Main", "onSurfaceTextureDestroyed");
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Log.i("Main", "onSurfaceTextureUpdated");
            }
        });

    }

    private void closeCamera() {
        mCameraTextureView.releaseCamera();
        mCameraTextureView.setVisibility(View.GONE);
    }
}
