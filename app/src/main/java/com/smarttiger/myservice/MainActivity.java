package com.smarttiger.myservice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kyleduo.switchbutton.SwitchButton;

public class MainActivity extends AppCompatActivity {

    final static String TAG = MainActivity.class.getSimpleName();

    private Context mContext;
    private SwitchButton mSwitchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        //调起卸载app的界面
//        Uri packageUri = Uri.parse("package:"+MainActivity.this.getPackageName());
//        Intent intent = new Intent(Intent.ACTION_DELETE,packageUri);
//        startActivity(intent);

        mSwitchButton = (SwitchButton) findViewById(R.id.switch_button);
        mSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessibilityUtils.startAccessibilityActivity(mContext);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSwitchButton.setChecked(AccessibilityUtils.isAccessibilityEnabled(mContext));
    }

}
