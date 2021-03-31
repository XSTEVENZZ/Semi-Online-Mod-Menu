#include <sys/types.h>
#include <pthread.h>
#include <jni.h>
#include <unistd.h>
#include "Logger.h"
#include <ESP.h>
//#include "test.h"

using namespace std;

ESP espOverlay;

bool loggedin = false;
bool esp2 = true;


void DrawESP(ESP esp, int screenWidth, int screenHeight) {

    esp.DrawCrosshair(Color(0, 0, 0, 255), Vector2(screenWidth / 2, screenHeight / 2), 42);

}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_loginform2_Auth_Title(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(("https://wtprovip.com/hyupai/serverpronto/login.php"));
}
extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_loginform2_MainActivity_Dialog(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(("Tela de Login by Hyupai Mods"));
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_example_loginform2_MainActivity_YTURL(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(("https://youtube.com/c/s4muu"));
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_loginform2_Loader_DrawOl(JNIEnv *env, jclass type, jobject espView, jobject canvas) {
    espOverlay = ESP(env, espView, canvas);
    if (espOverlay.isValid()){
     //   DrawESP(espOverlay, espOverlay.getWidth(), espOverlay.getHeight());
    }
}



extern "C"
JNIEXPORT void JNICALL
Java_com_example_loginform2_ESPView_DrawESP(JNIEnv *env, jobject thiz) {
    // TODO: implement DrawESP()
    //DrawESP(espOverlay, espOverlay.getWidth(), espOverlay.getHeight());
    
}
