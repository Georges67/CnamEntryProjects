package com.tp.myapp1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    private TextView m_serviceInfoView = null;
    private SeekBar m_mySeekBar;
    private ProgressBar m_myProgressBar;
    private TextView m_myProgressTextView;
    private ToggleButton m_startServiceButton= null;
    private ToggleButton m_bindButton;
    private ToggleButton m_startButton= null;
    private boolean m_isBound;
    private Messenger m_service;
    final Messenger m_messenger = new Messenger(new IncomingHandler());
    private Context m_context;

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyTestService.MSG_SET_INT_VALUE:
//                    m_serviceInfoView.setText("Int Message: " + msg.arg1);
                    m_myProgressBar.setProgress(msg.arg1);
                    break;
                case MyTestService.MSG_SET_STRING_VALUE:
                    String str1 = msg.getData().getString("str1");
                    m_myProgressTextView.setText("Str Message: " + str1);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private ServiceConnection m_connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            m_service = new Messenger(service);

            // TODO 5) Show message "Connected" in Activity Screen

            sendMsgToService(MyTestService.MSG_REGISTER_CLIENT);
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been unexpectedly disconnected - process crashed.
            m_service = null;
            // TODO 6) Show message "Disconnected" in Activity Screen
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_context = getApplicationContext();

        m_startServiceButton = findViewById(R.id.start_service);
//        m_startButton = findViewById(R.id.start_button);
//        m_bindButton = findViewById(R.id.bind);

        m_myProgressBar = findViewById(R.id.myProgressBar);
        m_serviceInfoView = findViewById(R.id.service_info);
        m_myProgressTextView = findViewById(R.id.progressValueTextView);
        m_mySeekBar = findViewById(R.id.mySeekBar);

        m_mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                m_myProgressBar.setProgress(i);
                m_myProgressTextView.setText(String.format("Progress ; %d", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onClickStartService(View view) {
        // TODO 2) Fill StartService ToggleButton behavior
        // Use method startService and stopService
    }

    // TODO 3)  add method onCLick for bind ToggleButton
    // Use methods doBindService and doUnbindService (already defined)

    // TODO 4)  add method onCLick for bind and Start ToggleButtons
    // Use method: sendMsgToService(MyTestService.MSG_START_COUNTER) to start counting (already defined)
    // and sendMsgToService(MyTestService.MSG_STOP_COUNTER) to stop counting (already defined)

    private View.OnClickListener m_resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            sendMsgToService(MyTestService.MSG_RESET_COUNTER);
        }
    };

    private void sendMsgToService(int msgId) {
        try {
            Message msg = Message.obtain(null, msgId);
            msg.replyTo = m_messenger;
            m_service.send(msg);
        } catch (RemoteException e) {
            // In this case the service has crashed before we could even do anything with it
        }
    }

    void doBindService() {
        bindService(new Intent(this, MyTestService.class), m_connection, Context.BIND_AUTO_CREATE);
        m_isBound = true;
        m_serviceInfoView.setText("Binding");
    }

    void doUnbindService() {
        if (m_isBound) {
            // If we have received the service, and hence registered with it, then now is the time to unregister.
            if (m_service != null) {
                try {
                    Message msg = Message.obtain(null, MyTestService.MSG_UNREGISTER_CLIENT);
                    msg.replyTo = m_messenger;
                    m_service.send(msg);
                } catch (RemoteException e) {
                    // There is nothing special we need to do if the service has crashed.
                }
            }
            // Detach our existing connection.
            unbindService(m_connection);
            m_isBound = false;
            m_serviceInfoView.setText("Disconnected.");
        }
    }
}
