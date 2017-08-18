package com.jyoungchang.yourmemory.fragment;

import android.support.v4.app.Fragment;

import com.jyoungchang.yourmemory.R;

/**
 * Created by platformstory on 2017-08-18.
 */

public class BaseFragment extends Fragment
{
    protected void startFragment(Class<? extends BaseFragment> fragmentClass)
    {
        BaseFragment fragment = null;
        try
        {
            fragment = fragmentClass.newInstance();
        }
        catch (java.lang.InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_game, fragment).addToBackStack(null).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout_game, fragment).commit();
    }

    protected void finishFragment()
    {
        getFragmentManager().popBackStack();
    }
}
