package com.example.adavi.myip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My IP address");
        setContentView(R.layout.activity_main);
        final TextView tv= (TextView)findViewById(R.id.tv_1);
        Button bt = (Button)findViewById(R.id.button1);
        utils = new Utils();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress;
                ipAddress = utils.getIPAddress(true);
                if(ipAddress.length()>0){
                    tv.setText(ipAddress);
                }
                else {
                    tv.setText("No IP available");
                }

            }
        });
//        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        if(utils.getIPAddress(true).length() > 0){
            tv.setText(utils.getIPAddress(true));
        }
        else{
            tv.setText("No IP available");
        }


    }
}
