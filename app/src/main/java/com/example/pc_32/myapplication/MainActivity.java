package com.example.pc_32.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button btnencender, btnapagar;
    BluetoothAdapter adaptadorBT = null;
    BluetoothSocket socketBT = null;
    OutputStream apolo13 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            btnencender = (Button) findViewById(R.id.btnencender);
            btnapagar = (Button) findViewById(R.id.btnapagar);
            adaptadorBT = BluetoothAdapter.getDefaultAdapter();

            BluetoothDevice dispositivoExteBlue = adaptadorBT.getRemoteDevice("00:1B:35:88:0C:58"); //mAC DEL BLUE EXTERNO

            socketBT = dispositivoExteBlue.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            socketBT.connect();

            //Encender
            btnencender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    datoEnviar("1");

                }
            });
            //Apagar
            btnapagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    datoEnviar("0");

                }
            });

        }catch (Exception e){
            Log.e("Error",""+e.getMessage());
        }

    }
        private void datoEnviar(String mensaje){
        byte[] bufferMensaje = mensaje.getBytes();
        try{
            apolo13.write(bufferMensaje);
        }catch (Exception e){
            Log.e("Error",""+e.getMessage());
        }
        }


}
