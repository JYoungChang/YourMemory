package com.jyoungchang.yourmemory.fragment;

import android.os.Bundle;
import android.util.Log;
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

public class Level1Fragment extends BaseFragment implements View.OnClickListener
{
    private YourMemoryApplication mYMApp;
    private int[] mQId = {R.id.tv_level1_q_1th, R.id.tv_level1_q_2th, R.id.tv_level1_q_3th, R.id.tv_level1_q_4th};
    private int[] mAId = {R.id.tv_level1_a_1th, R.id.tv_level1_a_2th, R.id.tv_level1_a_3th, R.id.tv_level1_a_4th};
    private TextView[] mTv_Q = new TextView[4];
    private TextView[] mTv_A = new TextView[4];
    private TextView mTv_Status;
    private ScalableLayout mScalable_Question;
    private Animation mAnimDisappear;

    private ArrayList<Integer> mNumArrayList = new ArrayList<Integer>();
    private int mAnswerCount = 0;

    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_level1, container, false);
            init();
        }
        return mView;
    }

    private void init()
    {
        mYMApp = (YourMemoryApplication) getActivity().getApplication();
        mYMApp.setAnswerCount(0);
        mYMApp.setLevel(1);
        mScalable_Question = (ScalableLayout) mView.findViewById(R.id.scalable_question);
        mAnimDisappear = AnimationUtils.loadAnimation(getActivity(), R.anim.disappear);
        mAnimDisappear.setDuration(3000);
        mTv_Status = (TextView) mView.findViewById(R.id.tv_status);

        for(int i = 0; i < mQId.length; i++)
        {
            mTv_Q[i] = (TextView) mView.findViewById(mQId[i]);
            (mTv_A[i] = (TextView) mView.findViewById(mAId[i])).setOnClickListener(this);
            mNumArrayList.add(i+1);
        }
        setQuestion();
    }

    /**
     * 문제 셋팅
     */
    private void setQuestion()
    {
        Collections.shuffle(mNumArrayList);
        Log.d("jtest", "array : " + mNumArrayList);
        for(int i = 0; i < mTv_Q.length; i++)
        {
            mTv_Q[i].setText(String.valueOf(mNumArrayList.get(i)));
            mTv_A[i].setText(String.valueOf(i+1));
        }
        mScalable_Question.clearAnimation();
        mAnimDisappear.cancel();
        mAnimDisappear.start();
        mScalable_Question.setAnimation(mAnimDisappear);
    }

    /**
     * 정답 확인
     * @param answer 제출한 정답.
     */
    private void confirmAnswer(int answer)
    {
        if(answer == mNumArrayList.get(mAnswerCount)) // 맞췄을 때
        {
            mTv_Status.setText("Good");
            mAnswerCount++;
        }
        else // 틀렸을 때
        {
            mTv_Status.setText("Fail");
        }

        if(mAnswerCount == 4)
        {
            if(mYMApp.getAnswerCount() == 5)
            {
                goToTheNextLevel();
            }
            else
            {
                mYMApp.setAnswerCount(mYMApp.getAnswerCount()+1);
                setQuestion();
                mAnswerCount = 0;
                mTv_Status.setText("New Question");
            }
        }
    }

    private void goToTheNextLevel()
    {
        mAnimDisappear.cancel();
        startFragment(Level2Fragment.class);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_level1_a_1th:
                confirmAnswer(Integer.parseInt(mTv_A[0].getText().toString()));
                break;
            case R.id.tv_level1_a_2th:
                confirmAnswer(Integer.parseInt(mTv_A[1].getText().toString()));
                break;
            case R.id.tv_level1_a_3th:
                confirmAnswer(Integer.parseInt(mTv_A[2].getText().toString()));
                break;
            case R.id.tv_level1_a_4th:
                confirmAnswer(Integer.parseInt(mTv_A[3].getText().toString()));
                break;
        }
    }
}
