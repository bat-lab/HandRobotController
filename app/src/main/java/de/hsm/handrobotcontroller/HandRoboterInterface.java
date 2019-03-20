package de.hsm.handrobotcontroller;

import android.content.Context;
import android.webkit.JavascriptInterface;

import java.io.IOException;

public class HandRoboterInterface {
    Context context;

    private final BtService btService;

    public HandRoboterInterface(final BtService btService) {
        this.btService = btService;
    }

    @JavascriptInterface
    public void handClose() {
        System.out.println("Hand close");
    }

    @JavascriptInterface
    public void handOpen() {
        System.out.println("Hand open");
    }

    @JavascriptInterface
    public void onFingerClick(int fingerNr) {
        System.out.println(fingerNr);
    }

    @JavascriptInterface
    public void goLeft() {
        System.out.println("Go Left");
    }

    @JavascriptInterface
    public void onConnectBt() throws IOException {
        this.btService.activate();
    }

    @JavascriptInterface
    public void goRight() {
        System.out.println("Go Right");
    }

    @JavascriptInterface
    public void goTop() {
        System.out.println("go Top");
    }

    @JavascriptInterface
    public void goBottom() {
        System.out.println("Go Bottom");
    }
}
