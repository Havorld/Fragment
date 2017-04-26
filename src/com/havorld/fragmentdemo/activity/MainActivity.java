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
		 * �����Ӧ�ñ����ں�̨(���û������home��),��ʱ��û�з��ص�ʱ�����Ӧ��Ҳ�ᱻ����������
		 * ������Ľ�����twoFragmentҳ�棬Ȼ���ں�̨״̬����ʱ������ٴ�ͨ������򿪼�¼��ʱ��
		 * ����twoFragment��oneFragment������һ���������Ϊ���Activity����������
		 * ��ԭ����twoFragment���ֻ�����һ��oneFragment��
		 * ���Կ���ͨ��savedInstanceState���ж�Activity�Ƿ����ؽ���
		 */

		// oneFragment���п��ж�����Ϊ��Activity��Ϊ���÷����ı䣨��Ļ��ת�������ڴ治�㱻ϵͳɱ����
		// ������´���ʱ�����ǵ�fragment�ᱻ�������������ǻᴴ���µ�FragmentManager���µ�FragmentManager
		// �����Ȼ�ȥ��ȡ����������fragment���У��ؽ�fragment���У��Ӷ��ָ�֮ǰ��״̬��
		if (savedInstanceState == null && oneFragment == null) {

			oneFragment = new OneFragment();
			Bundle bundle = new Bundle();
			bundle.putString("str", "����OneFragment");
			// �������ݣ���ʹ��Intent���������ڽ���(���ʹ��Intent����Ҫ��Fragment
			// ��getActivity().getIntent().getStringExtra(String name),
			// ��ʱFragment����getActivity()�е�Activity����)
			oneFragment.setArguments(bundle); //��add����֮ǰ����

			fragmentManager = getFragmentManager();
			FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
			beginTransaction.add(R.id.frameLayout, oneFragment, "OneFragment");
			// ��ӵ�����ջ�������ʱ������ذ�ťֱ���˳�Ӧ��,��Ӻ������ذ�ť���˳�����ջ��Fragment,�ٴ˵�����ذ�ť���˳�Ӧ��
			// beginTransaction.addToBackStack(null);
			beginTransaction.commit();
		}
	}

	@Override
	protected void onStart() {

		// ����ͨ��findFragmentByTag��ȡFragmentʵ��,Ȼ�����fragment�еķ���
		OneFragment findOneFragment = (OneFragment) fragmentManager.findFragmentByTag("OneFragment");
		findOneFragment.setOneFragment();
		super.onStart();
	}

	public void setMainActivity() {

		Toast.makeText(this, "MainActivity", 0).show();
	}

	@Override
	public void onFOneBtnClick() {

		Toast.makeText(this, "OneFragment ������ MainActivity�еķ���", 0).show();
	}

	@Override
	public void onFTwoBtnClick() {

		Toast.makeText(this, "TwoFragment ������ MainActivity�еķ���", 0).show();
	}

	@Override
	public void onClick(View v) {

		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
		hideFragment(beginTransaction);// ����Fragment
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
				// ������hideFragment()����ԭ����Fragment(������),������ڵ�Fragment
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
				// remove(ԭ�ȵ�Fragment)+add(���ڵ�Fragment)��onDestroyԭ����Fragment,��onCreate���ڵ�Fragment��
				beginTransaction.replace(R.id.frameLayout, threeFragment, "ThreeFragment");
				// ��ӵ�����ջ������ʹ��replace�滻,ԭ����FragmentҲ����onDestroy,����onDestroyView��
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
		// ����Fragment,��������ؾͻ��ж��Fragment����һ���ص���ʾ
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
