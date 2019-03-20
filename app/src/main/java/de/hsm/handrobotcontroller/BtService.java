package de.hsm.handrobotcontroller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import static android.support.v4.app.ActivityCompat.startActivityForResult;


public class BtService {
    private final static int REQUEST_ENABLE_BT = 4;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private ArrayAdapter<String> BTArrayAdapter;
    private OutputStream outputStream;
    private InputStream inStream;

    private final Activity activity;

    public BtService(final Activity activity) {
        this.activity = activity;
    }

    public boolean activate() throws IOException {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            return false;
        }

        if (!bluetoothAdapter.isEnabled()) {
            final Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(this.activity, enableBtIntent, REQUEST_ENABLE_BT, null);
        }


        this.pairedDevices = bluetoothAdapter.getBondedDevices();

        if (!this.pairedDevices.isEmpty()) {
            final BluetoothDevice device = this.pairedDevices.iterator().next();

            final UUID uuid = UUID.randomUUID();
            final BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
            outputStream = socket.getOutputStream();
            inStream = socket.getInputStream();
        }
        return true;
    }

    public void write(String s) throws IOException {
        outputStream.write(s.getBytes());
    }

    public void disconnect() {
        bluetoothAdapter.disable();
    }
}
