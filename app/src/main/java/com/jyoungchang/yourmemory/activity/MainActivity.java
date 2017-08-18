package com.jyoungchang.yourmemory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jyoungchang.yourmemory.R;
import com.jyoungchang.yourmemory.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener
{
    private Handler mHandler;
    private TextView mTv_Title;
    private TextView mTv_TouchScreen;
    private RelativeLayout mRelativeLayout;

    private Animation mAnim_Alpha;
    private Animation mAnim_Blink;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessage();
        init();
    }

    private void init()
    {
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_main);
        mTv_Title = (TextView) findViewById(R.id.tv_main_title);
        mTv_TouchScreen = (TextView) findViewById(R.id.tv_main_touch_screen);
        mAnim_Alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mTv_Title.startAnimation(mAnim_Alpha);
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    private void sendMessage()
    {
        mHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case 0:
                        mRelativeLayout.setOnClickListener(MainActivity.this);
                        mAnim_Blink = AnimationUtils.loadAnimation(MainActivity.this, R.anim.blink);
                        mTv_TouchScreen.setVisibility(View.VISIBLE);
                        mTv_TouchScreen.startAnimation(mAnim_Blink);
                        break;
                }
            }
        };
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_main:
                mTv_TouchScreen.setAnimation(null);
                mTv_Title.setAnimation(null);
                startActivity(new Intent(this, GameActivity.class));
                break;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
