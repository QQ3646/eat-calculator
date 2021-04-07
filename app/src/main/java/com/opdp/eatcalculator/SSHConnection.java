package com.opdp.eatcalculator;

import android.os.AsyncTask;
import android.speech.tts.Voice;

import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnection extends AsyncTask<String, Integer, Void> {
    public static final String IP = "192.168.1.39";
    public static final int PORT = 22;
    public static final String USER = "dreamIIx";
    public static final String PASSWORD = "";

    @Override
    protected Void doInBackground(String... strings) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(USER, IP, PORT);
            session.setPassword(PASSWORD);

            // Avoid asking for key confirmation
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);

//            session.setConfig("PreferredAuthentications", );

            session.connect();

            // exec 'scp -t rfile' remotely
//            String command="scp " + (ptimestamp ? "-p" :"") +" -t "+rfile;
//            Channel channel=session.openChannel("exec");
//            ((ChannelExec)channel).setCommand(command);
//
//            // get I/O streams for remote scp
//            InputStream in=channel.getInputStream();
//            OutputStream out=channel.getOutputStream();
            session.disconnect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
