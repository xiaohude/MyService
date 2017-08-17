package com.smarttiger.myservice;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.smarttiger.message.MessageManager;

public class MainActivity extends AppCompatActivity {

    final static String TAG = MainActivity.class.getSimpleName();

    private Context mContext;
    private View mServiceLayout;
    private SwitchButton mSwitchButton;
    private TextView mTimeText;
    private View mExpirationLayout;
    private View mIMEILayout;
    private TextView mIMEIText;
    private View mMesssagesBar;
    private TextView mMessagesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        if(ExpirationUtil.isExceedTime(mContext))
            finish();

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSwitchButton.setChecked(AccessibilityUtils.isAccessibilityEnabled(mContext));

        mMessagesText.setText(MessageManager.getInstance().getMessages());
    }

    private void initView(){
        mServiceLayout = findViewById(R.id.service_layout);
        mServiceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        mSwitchButton = (SwitchButton) findViewById(R.id.switch_button);
        mSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessibilityUtils.startAccessibilityActivity(mContext);
            }
        });

        mTimeText = (TextView) findViewById(R.id.time_text);
        mTimeText.setText(ExpirationUtil.expirationTime);

        mExpirationLayout = findViewById(R.id.expiration_layout);
        mIMEIText = (TextView) findViewById(R.id.imei_text);
        mIMEIText.setText(ExpirationUtil.getIMEI(mContext));
        mIMEILayout = findViewById(R.id.imei_layout);
        mIMEILayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(mIMEIText.getText());
                Toast.makeText(mContext, "已将IMEI号复制进剪贴板", Toast.LENGTH_SHORT).show();
            }
        });

        if(ExpirationUtil.isRightIMEI(mContext)){
            mExpirationLayout.setVisibility(View.GONE);
            mIMEILayout.setVisibility(View.GONE);
        }

        mMesssagesBar = findViewById(R.id.messages_bar);
        mMessagesText = (TextView) findViewById(R.id.messages_text);
        mMesssagesBar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MessageManager.getInstance().cleanMessages();
                Toast.makeText(mContext, "已清空最新消息缓存", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mMesssagesBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessagesText.setText(MessageManager.getInstance().getMessages());
            }
        });
    }

}
