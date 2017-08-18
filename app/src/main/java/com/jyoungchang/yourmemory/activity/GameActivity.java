package com.jyoungchang.yourmemory.activity;

import android.os.Bundle;

import com.jyoungchang.yourmemory.R;
import com.jyoungchang.yourmemory.base.BaseActivity;
import com.jyoungchang.yourmemory.fragment.Level1Fragment;

/**
 * Created by platformstory on 2017-08-18.
 */

public class GameActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
    }

    private void init()
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_game, new Level1Fragment()).commit();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
