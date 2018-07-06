package com.susy.clientsocket.Main;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.susy.clientsocket.Preferences;
import com.susy.clientsocket.R;
import com.susy.clientsocket.Sender;
import com.susy.clientsocket.Tools;

public class MainActivity extends AppCompatActivity  implements MainView{

    EditText ipEditText;
    EditText portEditText;
    EditText instructionsEditText;
    TextView responseText;
    Button btSend;

    Context context;
    MainView mainView;

    public void setUI(){
        ipEditText = (EditText) findViewById(R.id.main_ip_text);
        portEditText = (EditText) findViewById(R.id.main_port_text);
        btSend = (Button) findViewById(R.id.main_send_button);
        instructionsEditText = (EditText) findViewById(R.id.main_intruction_text);
        responseText = (TextView) findViewById(R.id.main_response_label);

        getSupportActionBar().setElevation(0);

        if(Preferences.getIpAddr(context)!=null){
            ipEditText.setText(Preferences.getIpAddr(context).toString());
        }

        if(Preferences.getPort(context)!=null){
            portEditText.setText(Preferences.getPort(context).toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                Tools.message("Save");
                Preferences.setIpAddr(context,ipEditText.getText().toString());
                Preferences.setPort(context,portEditText.getText().toString());

                Toast.makeText(context, "Address saved", Toast.LENGTH_SHORT).show();
                
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init components
        context = this;
        mainView = this;
        setUI();

        //Init main
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sender sender = new Sender(mainView);

                String ip = ipEditText.getText().toString();
                String port = portEditText.getText().toString();

                if ( !Tools.isNumeric(port)) {
                    Toast.makeText(MainActivity.this, "Introduce un puerto numerico", Toast.LENGTH_SHORT).show();
                }else{
                    sender.execute(ip,port,instructionsEditText.getText().toString() + "enviado desde : "+ Tools.getWifiIpAddress(context));

                }
            }
        });

    }

    @Override
    public void response(final String response) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Tools.message("Recibido");
                responseText.setText(response);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        responseText.setText("Waiting response ...");
                    }
                },3000);

            }
        });


    }
}
