package com.havorld.fragmentdemo.activity;

import com.havorld.fragmentdemo.R;
import com.havorld.fragmentdemo.fragment.OneFragment;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		// ʹ��findFragmentById��ȡFragmentʵ��
		OneFragment oneFragment = (OneFragment) getFragmentManager().findFragmentById(R.id.fragment);
		oneFragment.setOneFragment();
	}
}
