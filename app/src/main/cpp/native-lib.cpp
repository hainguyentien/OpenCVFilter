#include <jni.h>
#include <string>
//#include <opencv2/core/core.hpp>
//#include <opencv2/imgproc/imgproc.hpp>
//#include <opencv2/features2d/features2d.hpp>

extern "C"
JNIEXPORT void JNICALL Java_com_rambo_opencvfilter_OpenCVActivity_detectEdges(
        JNIEnv *env,
        jobject /* this */, jlong gray) {

    /**
     * This code based on an instruction of OpenCV
     * But I don't know how to make that code (cv::) work
     */

//        cv::Mat& edges = *(cv::Mat *) gray;
//        cv::Canny(edges, edges, 50, 250);

}
