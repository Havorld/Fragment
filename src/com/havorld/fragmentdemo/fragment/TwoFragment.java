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
import android.widget.Toast;

public class TwoFragment extends Fragment implements OnClickListener {

	private static final String TAG = "Havorld";
	private FTwoBtnClickListener fTwoBtnClickListener;

	public interface FTwoBtnClickListener {

		void onFTwoBtnClick();
	}

	// 设置回调接口
	public void setfTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener) {
		this.fTwoBtnClickListener = fTwoBtnClickListener;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e(TAG, "TwoFragment onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "TwoFragment onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_two, null);
		Button button = (Button) inflate.findViewById(R.id.button);
		button.setOnClickListener(this);
		Log.e(TAG, "TwoFragment onCreateView");
		return inflate;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.e(TAG, "TwoFragment onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.e(TAG, "TwoFragment onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.e(TAG, "TwoFragment onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.e(TAG, "TwoFragment onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.e(TAG, "TwoFragment onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.e(TAG, "TwoFragment onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "TwoFragment onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.e(TAG, "TwoFragment onDetach");
		super.onDetach();
	}

	public void setOneFragment() {

		Toast.makeText(getActivity(), "TwoFragment", 0).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			if (fTwoBtnClickListener != null) {
				fTwoBtnClickListener.onFTwoBtnClick();
			}

			break;

		default:
			break;
		}
	}
}
