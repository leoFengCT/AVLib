package com.snicesoft.avlib.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.pluginmgr.Proxy;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.avlib.view.ViewFinder;

/**
 * 
 * @author zhe
 *
 * @param <H>
 * @param <D>
 */
public abstract class AvActivity<H extends IHolder, D extends IData> extends
		Activity implements IAv<H, D>, OnClickListener {
	private ViewFinder finder;
	protected D _data;
	protected H _holder;

	@Override
	public final D getData() {
		return _data;
	}

	@Override
	public final H getHolder() {
		return _holder;
	}

	@Override
	public final void dataBindAll() {
		AVLib.dataBind(_data, finder);
	}

	@Override
	public final void dataBindTo(String fieldName) {
		AVLib.dataBindTo(_data, finder, fieldName);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_holder = newHolder();
		_data = newData();
		setContentView(LayoutUtils.getLayoutId(getThisClass()));
		finder = new ViewFinder(this);
		AvUtils.initHolder(_holder, this);
		if (_holder != null)
			_holder.initViewParams();
		dataBindAll();
	}

	public Class<?> getThisClass() {
		Class<?> clazz = getClass();
		if (Proxy.PROXY_ACTIVITY.equals(clazz.getName())) {
			clazz = getClass().getSuperclass();
		}
		return clazz;
	}

	@Override
	public void onClick(View v) {
		if (v == null)
			return;
	}
}