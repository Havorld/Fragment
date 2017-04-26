package com.havorld.fragmentdemo.activity;

import com.havorld.fragmentdemo.R;
import com.havorld.fragmentdemo.fragment.OneFragment;
import com.havorld.fragmentdemo.fragment.OneFragment.FOneBtnClickListener;
import com.havorld.fragmentdemo.fragment.ThreeFragment;
import com.havorld.fragmentdemo.fragment.TwoFragment;
import com.havorld.fragmentdemo.fragment.TwoFragment.FTwoBtnClickListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, FOneBtnClickListener, FTwoBtnClickListener {

	private FragmentManager fragmentManager;
	private OneFragment oneFragment;
	private TwoFragment twoFragment;
	private ThreeFragment threeFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.one).setOnClickListener(this);
		findViewById(R.id.two).setOnClickListener(this);
		findViewById(R.id.three).setOnClickListener(this);

		/**
		 * 当你的应用被至于后台(如用户点击了home键),长时间没有返回的时候，你的应用也会被重新启动。
		 * 假设你的界面在twoFragment页面，然后处于后台状态，长时间后当你再次通过最近打开记录打开时，
		 * 上面twoFragment与oneFragment叠加在一起，这就是因为你的Activity重新启动，
		 * 在原来的twoFragment上又绘制了一个oneFragment。
		 * 所以可以通过savedInstanceState来判断Activity是否有重建。
		 */

		// oneFragment进行空判断是因为当Activity因为配置发生改变（屏幕旋转）或者内存不足被系统杀死，
		// 造成重新创建时，我们的fragment会被保存下来，但是会创建新的FragmentManager，新的FragmentManager
		// 会首先会去获取保存下来的fragment队列，重建fragment队列，从而恢复之前的状态。
		if (savedInstanceState == null && oneFragment == null) {

			oneFragment = new OneFragment();
			Bundle bundle = new Bundle();
			bundle.putString("str", "你是OneFragment");
			// 传递数据，不使用Intent传递有利于解耦(如果使用Intent则需要在Fragment
			// 中getActivity().getIntent().getStringExtra(String name),
			// 此时Fragment就与getActivity()中的Activity绑定了)
			oneFragment.setArguments(bundle); //在add方法之前调用

			fragmentManager = getFragmentManager();
			FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
			beginTransaction.add(R.id.frameLayout, oneFragment, "OneFragment");
			// 添加到返回栈。不添加时点击返回按钮直接退出应用,添加后点击返回按钮先退出返回栈的Fragment,再此点击返回按钮才退出应用
			// beginTransaction.addToBackStack(null);
			beginTransaction.commit();
		}
	}

	@Override
	protected void onStart() {

		// 可以通过findFragmentByTag获取Fragment实例,然后调用fragment中的方法
		OneFragment findOneFragment = (OneFragment) fragmentManager.findFragmentByTag("OneFragment");
		findOneFragment.setOneFragment();
		super.onStart();
	}

	public void setMainActivity() {

		Toast.makeText(this, "MainActivity", 0).show();
	}

	@Override
	public void onFOneBtnClick() {

		Toast.makeText(this, "OneFragment 调用了 MainActivity中的方法", 0).show();
	}

	@Override
	public void onFTwoBtnClick() {

		Toast.makeText(this, "TwoFragment 调用了 MainActivity中的方法", 0).show();
	}

	@Override
	public void onClick(View v) {

		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
		hideFragment(beginTransaction);// 隐藏Fragment
		switch (v.getId()) {
		case R.id.one:
			if (oneFragment == null) {
				oneFragment = new OneFragment();
				beginTransaction.add(R.id.frameLayout, oneFragment, "OneFragment");
			} else {
				beginTransaction.show(oneFragment);
			}

			break;
		case R.id.two:
			if (twoFragment == null) {
				twoFragment = new TwoFragment();
				twoFragment.setfTwoBtnClickListener(this);
				// 在上面hideFragment()隐藏原来的Fragment(不销毁),添加现在的Fragment
				beginTransaction.add(R.id.frameLayout, twoFragment, "TwoFragment");
			} else {
				beginTransaction.show(twoFragment);
			}

			break;
		case R.id.three:
			if (threeFragment == null) {
				threeFragment = new ThreeFragment();
				beginTransaction.add(R.id.frameLayout, threeFragment, "ThreeFragment");
				// replace =
				// remove(原先的Fragment)+add(现在的Fragment)。onDestroy原来的Fragment,再onCreate现在的Fragment。
				beginTransaction.replace(R.id.frameLayout, threeFragment, "ThreeFragment");
				// 添加到返回栈。即便使用replace替换,原来的Fragment也不会onDestroy,但会onDestroyView。
				beginTransaction.addToBackStack(null);
				oneFragment = null;
			} else {
				beginTransaction.show(threeFragment);
			}

			break;

		default:
			break;
		}
		beginTransaction.commit();
	}

	public void hideFragment(FragmentTransaction beginTransaction) {
		// 隐藏Fragment,如果不隐藏就会有多个Fragment合在一起重叠显示
		if (oneFragment != null) {
			beginTransaction.hide(oneFragment);
		}
		if (twoFragment != null) {
			beginTransaction.hide(twoFragment);
		}
		if (threeFragment != null) {
			beginTransaction.hide(threeFragment);
		}
	}
}
