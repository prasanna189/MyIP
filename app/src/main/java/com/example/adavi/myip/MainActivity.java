package com.example.adavi.myip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {


    public static String getIP() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My IP address");
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.ipaddr_tv);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        Button bt = (Button) findViewById(R.id.refresh_button);

        tv.setText(R.string.no_ip);

        String IP;
        IP = getIP();

        if (IP != null) {
            if (IP.length() > 0) {
                tv.setText(IP);
            }
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //toast to alert user that it will take sometime for device to update its ip address
                Toast toast = Toast.makeText(MainActivity.this, "It may take some time for the device to update IP address", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 400);
                toast.show();

                tv.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                String IP = getIP();
                tv.setText(R.string.no_ip);
                if (IP != null) {
                    if (IP.length() > 0) {
                        tv.setText(IP);
                    }
                }
                tv.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

    }
}
