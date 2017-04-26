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

public class ThreeFragment extends Fragment implements OnClickListener {

	private static final String TAG = "Havorld";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e(TAG, "ThreeFragment onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "ThreeFragment onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_three, null);
		Button button = (Button) inflate.findViewById(R.id.button);
		button.setOnClickListener(this);
		Log.e(TAG, "ThreeFragment onCreateView");
		return inflate;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.e(TAG, "ThreeFragment onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.e(TAG, "ThreeFragment onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.e(TAG, "ThreeFragment onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.e(TAG, "ThreeFragment onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.e(TAG, "ThreeFragment onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.e(TAG, "ThreeFragment onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "ThreeFragment onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.e(TAG, "ThreeFragment onDetach");
		super.onDetach();
	}

	public void setOneFragment() {

		Toast.makeText(getActivity(), "ThreeFragment", 0).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:

			break;

		default:
			break;
		}
	}
}
