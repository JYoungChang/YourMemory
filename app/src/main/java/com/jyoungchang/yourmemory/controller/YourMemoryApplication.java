package com.jyoungchang.yourmemory.controller;

import android.app.Application;

/**
 * Created by platformstory on 2017-08-18.
 */

public class YourMemoryApplication extends Application
{
    private int answerCount;
    private int level;

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
