package com.susy.clientsocket;

import android.os.AsyncTask;
import android.util.Log;

import com.susy.clientsocket.Main.MainView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender extends AsyncTask<String,String,String> {

    Socket s;
    PrintWriter pw;
    BufferedReader br;

    MainView mainView;

    public Sender(MainView mainView) {
        this.mainView = mainView;
    }

    protected String doInBackground(String... strings) {

        String message = strings[2];
        String IP_SERVER = strings[0];
        int PORT = Integer.parseInt(strings[1]);

        String response = null;

        try {
            s = new Socket(IP_SERVER, PORT);
            pw = new PrintWriter(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            pw.write(message);
            pw.flush();


            response  = br.readLine();
            Tools.message(response);

            pw.close();
            br.close();
            s.close();


        } catch (Exception e) {
            Log.i("ERROR", e.getMessage());
            response = "Error, check connectivity";
        }

        return response;

    }

    @Override
    protected void onPostExecute(String s) {
        mainView.response(s);
    }

}