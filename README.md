# Fragment
Android3.0开始引入了fragment，主要用来实现一个多任务的界面。
fragment是activity的子界面了，运行在activity上面的。
为了能够向下兼容低版本的API，让所有与fragment相关的API都从support.v**包中导入
Fragment经常和FrameLayout一块使用
## Fragment中的一些方法和API
### 方法
- onAttach(Activity)
当Fragment与Activity发生关联时调用。

- onCreateView(LayoutInflater, ViewGroup,Bundle)
创建该Fragment的视图

- onActivityCreated(Bundle)
当Activity的onCreate方法返回时调用

- onDestoryView()
与onCreateView想对应，当该Fragment的视图被移除时调用

- onDetach()
与onAttach相对应，当Fragment与Activity关联被取消时调用

- 注意：除了onCreateView，其他的所有方法如果你重写了，必须调用父类对于该方法的实现，

### API



- 获取FragmentManage的方式：
		getFragmentManager() 
		//v4中
		getSupportFragmentManager

- 主要的操作都是FragmentTransaction的方法

		//开启一个事务
		FragmentTransaction transaction = fragmentManager.benginTransatcion();
		
		//往Activity中添加一个Fragment  
		transaction.add(); 
		
		//从Activity中移除一个Fragment，如果被移除的Fragment没有添加到回退栈（回退栈后面会详细说），这个Fragment实例将会被销毁。
		transaction.remove() 
		
		//使用另一个Fragment替换当前的，实际上就是remove()然后add()的合体~
		transaction.replace()
		
		//隐藏当前的Fragment，仅仅是设为不可见，并不会销毁
		transaction.hide()
		
		//显示之前隐藏的Fragment
		transaction.show()
		
		//会将view从UI中移除,和remove()不同,此时fragment的状态依然由FragmentManager维护。
		transaction.detach()
		
		//重建view视图，附加到UI上并显示。
		transaction.attach()
		
		//提交一个事务
		transatcion.commit()
注意：常用Fragment的哥们，可能会经常遇到这样Activity状态不一致：State loss这样的错误。主要是因为：commit方法一定要在Activity.onSaveInstance()之前调用。
上述，基本是操作Fragment的所有的方式了，在一个事务开启到提交可以进行多个的添加、移除、替换等操作。
值得注意的是：如果你喜欢使用Fragment，一定要清楚这些方法，哪个会销毁视图，哪个会销毁实例，哪个仅仅只是隐藏，这样才能更好的使用它们。


## 生命周期
![](https://github.com/Havorld/AndroidSummaryPic/blob/master/Fragment%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.png?raw=true)
- 用事务replace(替换)Fragment,不添加addToBackStack(String str)

		FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
		beginTransaction.replace(int containerViewId, FragmentB, String tag)
		beginTransaction.commit();
	
**开启一个fragment  A调用的方法依次为:**
onAttach、onCreate、onCreateView、onActivityCreated、onStart、onResume

**切换到另外的fragment B调用的方法依次为：**
fragment A调用: onPause、onStop、onDestoryView、onDestory、onDetach
fragment B调用: onAttach、onCreate、onCreateView、onActivityCreated、onStart、onResume
(既切换时是先销毁fragment再创建另一个)

- 用事务replace(替换)Fragment,添加到(回退栈)addToBackStack(null)

		FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
		beginTransaction.replace(int containerViewId, FragmentB, String tag)
		beginTransaction.addToBackStack(null);
		beginTransaction.commit();

**开启一个fragment  A调用的方法依次为:**
onAttach、onCreate、onCreateView、onActivityCreated、onStart、onResume

**切换到另外的fragment B调用的方法依次为：**
fragment A调用: onPause、onStop、onDestoryView、onDetach(没有调用onDestory)
fragment B调用: onAttach、onCreate、onCreateView、onActivityCreated、onStart、onResume

**再从fragment B切换到fragment A时调用的方法依次为**
fragment A调用: onAttach、onCreateView、onActivityCreated、onStart、onResume(不再掉用onCreate)

- 用add(添加)Fragment

**用事务添加add(添加)Fragment**
从Fragment A切换到Fragment B时，原来的Fragment A不会销毁，按返回键返回到时Fragment A也不重建。

**用FragmentStatePagerAdapter切换Fragment**
注意：此时需要在Fragment里重写setMenuVisibility(boolean menuVisible)方法当menuVisible为true时View.VISIBLE(显示界面),menuVisible为false时View.VISIBLE(隐藏界面)。


## 传递数据


- 在Activity中调用Fragment的方法

可以使用下面的方法获取然后强转为想要得到的fragment
		getSupportFragmentManager().findFragmentById(int id)
		getSupportFragmentManager().findFragmentByTag(String tag)

- 在Fragment中调用Activity中的方法

使用getActivity()获取Fragment所在Activity再强转为宿主Activity，然后调用宿主Activity中的方法 

		((MainActivity)getActivity()).Method();

- Fragment与Fragment之间传递数据


1. 可以通过在Fragment定义接口让Activity实现这个借口通过回调的方法
2. 可以通过使用以下方法获取并强转为目标fragment


		getActivity.getSupportFragmentManager().findFragmentById(int id)
		getActivity().getSupportFragmentManager().findFragmentByTag(String tag)
- 使用setArguments和getArguments

要传递的代码:

			Bundle bundle = new Bundle();
			bundle.putString("str", "你是OneFragment");
			// 传递数据，不使用Intent传递有利于解耦(如果使用Intent则需要在Fragment
			// 中getActivity().getIntent().getStringExtra(String name),
			// 此时Fragment就与getActivity()中的Activity绑定了)
			fragmentInstance.setArguments(bundle); //在add方法之前调用
要接收的代码

			if (getArguments() != null) {
				String str = getArguments().getString("str","");
			}
**注意：**

	调用getActivity()时，fragment必须和activity关联，否则将会返回一个null。
	在add或replace时可以使用三个参数的方法为Fragment添加tag ：
	getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment, tag)
	getSupportFragmentManager().beginTransaction().add(containerViewId, fragment, tag)

## FragmentPagerAdapter与FragmentStatePagerAdapter

### FragmentPagerAdapter：
对于不再需要的fragment，FragmentPagerAdapter会选择调用detach方法仅销毁视图(onDestroyView)，并不会销毁fragment实例(onDestroy)。

### FragmentStatePagerAdapter：
会销毁不再需要的fragment，在当前事务提交以后，会彻底的将fragmeng从当前Activity的FragmentManager中移除，state表明销毁时会将其onSaveInstanceState(Bundle outState)中的bundle信息保存下来，当用户切换回来，可以通过该bundle恢复生成新的fragment，也就是说，你可以在onSaveInstanceState(Bundle outState)方法中保存一些数据，在onCreate中进行恢复创建。

**建议**
使用FragmentStatePagerAdapter当然更省内存，但是销毁新建也是需要时间的。一般情况下，如果你是制作主页面，就3、4个Tab，那么可以选择使用FragmentPagerAdapter，如果你是用于ViewPager展示数量特别多的条目时，那么建议使用FragmentStatePagerAdapter。






