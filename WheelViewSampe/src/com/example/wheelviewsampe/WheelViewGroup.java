package com.example.wheelviewsampe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WheelViewGroup extends ViewGroup {
	WheelView wheelView;
	Context context;
	RelativeLayout layout;
	TextView tv;

	public WheelViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
		// TODO Auto-generated constructor stub
	}

	public WheelViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
		// TODO Auto-generated constructor stub
	}

	public WheelViewGroup(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public void init() {

		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		wheelView = new WheelView(context);
		wheelView.setLayoutParams(lp);

		wheelView.setPosition(0);

		addView(wheelView, lp);

	

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		int size = getChildCount();
		for (int i = 0; i < size; ++i) {
			final View child = getChildAt(i);
			child.layout(0,0, 500, 500);
			
		}
		
		

	}
	
	

}
