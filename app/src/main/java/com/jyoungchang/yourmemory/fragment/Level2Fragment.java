package com.jyoungchang.yourmemory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.jyoungchang.yourmemory.R;
import com.jyoungchang.yourmemory.controller.YourMemoryApplication;
import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by platformstory on 2017-08-18.
 */

public class Level2Fragment extends BaseFragment implements View.OnClickListener
{
    private YourMemoryApplication mYMApp;
    private View mView;
    private int[] mQId = {R.id.tv_level2_q_1th, R.id.tv_level2_q_2th, R.id.tv_level2_q_3th, R.id.tv_level2_q_4th, R.id.tv_level2_q_5th, R.id.tv_level2_q_6th};
    private int[] mAId = {R.id.tv_level2_a_1th, R.id.tv_level2_a_2th, R.id.tv_level2_a_3th, R.id.tv_level2_a_4th, R.id.tv_level2_a_5th, R.id.tv_level2_a_6th};
    private TextView[] mTv_Q = new TextView[6];
    private TextView[] mTv_A = new TextView[6];
    private ScalableLayout mScalable_Question;
    private TextView mTv_Status;
    private ArrayList<Integer> mNumArrayList = new ArrayList<Integer>();
    private Animation mAnimDisappear;
    private int mAnswerCount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_level2, container, false);
            init();
        }
        return mView;
    }

    private void init()
    {
        mYMApp = (YourMemoryApplication) getActivity().getApplication();
        mTv_Status = (TextView) mView.findViewById(R.id.tv_level2_status);
        mAnimDisappear = AnimationUtils.loadAnimation(getActivity(), R.anim.disappear);
        mScalable_Question = (ScalableLayout) mView.findViewById(R.id.scalable_level2_question);
        for(int i = 0; i < mTv_Q.length; i++)
        {
            mTv_Q[i] = (TextView) mView.findViewById(mQId[i]);
            (mTv_A[i] = (TextView) mView.findViewById(mAId[i])).setOnClickListener(this);
            mTv_A[i].setText(String.valueOf(i+1));
            mTv_A[i].setTag(i+1);
            mNumArrayList.add(i+1);
        }

        setQuestion();
    }

    private void setQuestion()
    {
        Collections.shuffle(mNumArrayList);
        for(int i = 0; i < mTv_Q.length; i++)
        {
            mTv_Q[i].setText(String.valueOf(mNumArrayList.get(i)));
            mTv_Q[i].setTag(mNumArrayList.get(i));
        }
        mScalable_Question.clearAnimation();
        mAnimDisappear.cancel();
        mAnimDisappear.start();
        mScalable_Question.startAnimation(mAnimDisappear);
    }

    private void confirmAnswer(int answer)
    {
        if(answer == mNumArrayList.get(mAnswerCount))
        {
            mTv_Status.setText("Good");
            mAnswerCount++;
        }
        else
        {
            mTv_Status.setText("Fail");
        }

        if(mAnswerCount == 6)
        {
            setQuestion();
            mAnswerCount = 0;
            mTv_Status.setText("New Question");
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            default:
                confirmAnswer((Integer) view.getTag());
                break;
        }
    }
}
