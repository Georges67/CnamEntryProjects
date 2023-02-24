package com.tp.myapp1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by georges on 14/12/15.
 */
public class MyTestService extends Service
{
    private final String LOG_TAG = "MyTestService";

    int mNotificationId = 1;

    private Timer m_timer = null;
    private int m_counter = 0, incrementBy = 1;
    private static boolean isRunning = false;

    ArrayList<Messenger> m_clients = new ArrayList<>(); // Keeps track of all current registered clients.

    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_SET_INT_VALUE = 3;
    static final int MSG_SET_STRING_VALUE = 4;
    static final int MSG_RESET_COUNTER = 5;
    static final int MSG_START_COUNTER = 6;
    static final int MSG_STOP_COUNTER = 7;
    final Messenger m_messenger = new Messenger(new IncomingHandler()); // Target we publish for clients to send messages to IncomingHandler.
    private NotificationManager m_notifyMgr;


    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService", ">onBind");
        return m_messenger.getBinder();
    }

    class IncomingHandler extends Handler { // Handler of incoming messages from clients.
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    m_clients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    m_clients.remove(msg.replyTo);
                    break;
                case MSG_SET_INT_VALUE:
                    incrementBy = msg.arg1;
                    break;
                case MSG_RESET_COUNTER:
                    m_counter = 0;
                    break;
                case MSG_START_COUNTER:
                    Log.i("MyService", "Starting Timer");
                    // TODO 7) create Timer and start it here
                    // Use m_timer (already defined)
                    // And call method onTimerTick (already defined)

                    break;
                case MSG_STOP_COUNTER:
                    Log.i("MyService", "Stopping Timer");
                    if (m_timer != null) {
                        m_timer.cancel();
                        m_timer = null;
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private void sendMessageToUI(int valueToSend) {
        for (int i= m_clients.size()-1; i>=0; i--) {
            try {
                // Send data as an Integer
                m_clients.get(i).send(Message.obtain(null, MSG_SET_INT_VALUE, valueToSend, 0));

                //Send data as a String
                Bundle b = new Bundle();
                b.putString("str1", String.valueOf(valueToSend));
                Message msg = Message.obtain(null, MSG_SET_STRING_VALUE);
                msg.setData(b);
                m_clients.get(i).send(msg);
            } catch (RemoteException e) {
                // The client is dead. Remove it from the list; we are going through the list from back to front so this is safe to do inside the loop.
                m_clients.remove(i);
            }
        }
    }

    @Override
    public void onCreate()
    {
        Log.i("MyService", ">onCreate");

        super.onCreate();
        Log.i("MyService", "Service Started.");
        showNotification();

        isRunning = true;
    }

    private void showNotification() {
        Notification.Builder mBuilder =
                new Notification.Builder(this)
                        .setSmallIcon(R.drawable.moto)
                        .setContentTitle("My Service Notification")
                        .setContentText("The Service is Started");

        // Gets an instance of the NotificationManager service
        m_notifyMgr =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        m_notifyMgr.notify(mNotificationId, mBuilder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "Received start id " + startId + ": " + intent);
        return START_STICKY; // run until explicitly stopped.
    }

    public static boolean isRunning()
    {
        return isRunning;
    }


    private void onTimerTick() {
        Log.i("TimerTick", "Timer doing work." + m_counter);
        try {
            m_counter += incrementBy;
            // TODO 8) send message to Handler Activity to refresh display
            // Use sendMessageToUI with m_counter parameter (already defined)
        } catch (Throwable t) {
            //you should always ultimately catch all exceptions in m_timer tasks.
            Log.e("TimerTick", "Timer Tick Failed.", t);
        }
    }

    @Override
    public void onDestroy() {
        Log.i("MyService", ">onDestroy");

        super.onDestroy();
        if (m_timer != null) {
            m_timer.cancel();
        }
        m_counter = 0;
        m_notifyMgr.cancel(mNotificationId); // Cancel the persistent notification.
        Log.i("MyService", "Service Stopped.");
        isRunning = false;
    }
}
