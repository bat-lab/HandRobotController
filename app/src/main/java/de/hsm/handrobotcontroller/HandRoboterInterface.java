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
    public void onConnectBt() throws IOException {
        this.btService.activate();
    }

    @JavascriptInterface
    public void handOpen() {
        System.out.println("Hand open");
    }

    @JavascriptInterface
    public void onFingerClick(int fingerNr) throws IOException {
        this.btService.write(this.write(fingerNr));
        System.out.println(fingerNr);
    }

    private String write(int fingerNr) {
        switch (fingerNr) {
            case 1:
                return "a";
            case 2:
                return "b";
            case 3:
                return "c";
            case 4:
                return "d";
            case 5:
                return "e";
            default:
                return "";
        }
    }
}
