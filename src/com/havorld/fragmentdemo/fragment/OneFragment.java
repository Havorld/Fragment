package com.havorld.fragmentdemo.fragment;

import com.havorld.fragmentdemo.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OneFragment extends Fragment implements OnClickListener {

	private static final String TAG = "Havorld";
	private FOneBtnClickListener fOneBtnClickListener;
	private String str;
	
	/**
	 * 设置按钮点击的回调
	 * 
	 */
	public interface FOneBtnClickListener {
		
		void onFOneBtnClick();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e(TAG, "OneFragment onAttach");
		//宿主activity必须要实现FOneBtnClickListener接口
		if (activity instanceof FOneBtnClickListener) {
			
			fOneBtnClickListener = (FOneBtnClickListener) activity;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "OneFragment onCreate");
		//获取Activity传递过来的数据
		if (getArguments() != null) {
			str = getArguments().getString("str","");
		}
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_one, null);
		EditText editText = (EditText) inflate.findViewById(R.id.editText);
		editText.setText(str);
		Button button = (Button) inflate.findViewById(R.id.button);
		button.setOnClickListener(this);
		Log.e(TAG, "OneFragment onCreateView");
		return inflate;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.e(TAG, "OneFragment onActivityCreated");
		//通过获取宿主Activity直接调用其中的方法
		// ((MainActivity)getActivity()).setMainActivity();
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		Log.e(TAG, "OneFragment onStart");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.e(TAG, "OneFragment onResume");
		super.onResume();
	}
 
	@Override
	public void onPause() {
		Log.e(TAG, "OneFragment onPause");
		super.onPause();
	}
	
	@Override
	public void onStop() {
		Log.e(TAG, "OneFragment onStop");
		super.onStop();
	}
	
	@Override
	public void onDestroyView() {
		Log.e(TAG, "OneFragment onDestroyView");
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		Log.e(TAG, "OneFragment onDestroy");
		super.onDestroy();
	}
	
	@Override
	public void onDetach() {
		Log.e(TAG, "OneFragment onDetach");
		super.onDetach();
	}
	public void setOneFragment() {

		Toast.makeText(getActivity(), "OneFragment", 0).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:

			fOneBtnClickListener.onFOneBtnClick();
			break;

		default:
			break;
		}
	}
}
