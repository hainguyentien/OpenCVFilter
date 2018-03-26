package com.rambo.opencvfilter;

/**
 * This code is based on OpenCV source code
 * Use CameraBridgeViewBase of OpenCV instead of manual SurfaceView
 * But all of this had a problem
 * This apps will not work if you don't have OpenCV manager apps on your device
 * So I don't think this is the best way to solve your thread
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

public class OpenCVActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "OpenCVCamera";
    private CameraBridgeViewBase camView;

    static {
        System.loadLibrary("native-lib");
    }

    /**
     * This Callback same to SurfaceHolder.Callback
     */
    private BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    camView.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_open_cv);
        camView = findViewById(R.id.cameraView);
        camView.setVisibility(SurfaceView.VISIBLE);
        camView.setCvCameraViewListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (camView != null)
            camView.disableView();
    }

    @Override
    public void onResume(){
        super.onResume();

        /*
        All you must do is call OpenCV_VER_3_1_0 in this void
         */

        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, baseLoaderCallback);
        } else {
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (camView != null)
            camView.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        /**
         * if return gray() you will get the grayscale on camView based on OpenCV
         */
        return  inputFrame.gray();

        /**
         * This below code is for EdgeDetection Filter with Native code and OpenCV
         * But file cpp is not working
         */
//        Mat edges = inputFrame.gray();
//        detectEdges(edges.getNativeObjAddr());
//        return edges;
    }

    /**
     * This code call native code online
     */

    // public native void detectEdges(long matGray);

}
