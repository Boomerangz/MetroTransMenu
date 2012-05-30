package com.boomerang.metromenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.boomerang.metrotransmenu.*;

public class MetromenuActivity extends Activity
{

	List<TextImageSwitcher> switchers = new ArrayList<TextImageSwitcher>();
	int currButton = -1;
	Handler mHandler = new Handler();
	boolean continueUpdating = true;
	static final int UPDATE_DELAY = 1000 + TextImageSwitcher.durationTime;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metromenu);

		switchers.add((TextImageSwitcher) findViewById(R.id.switcher1));
		switchers.add((TextImageSwitcher) findViewById(R.id.switcher2));
		switchers.add((TextImageSwitcher) findViewById(R.id.switcher3));
		switchers.add((TextImageSwitcher) findViewById(R.id.switcher4));
		switchers.add((TextImageSwitcher) findViewById(R.id.switcher5));
		switchers.add((TextImageSwitcher) findViewById(R.id.switcher6));
		switchers.add((TextImageSwitcher) findViewById(R.id.switcher7));
		switchers.add((TextImageSwitcher) findViewById(R.id.switcher8));

		SwitcherDesc[] swRest = {
				new SwitcherDesc(R.drawable.rest0,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_LEFT),
				new SwitcherDesc(R.drawable.rest1,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_RIGHT),
				new SwitcherDesc(R.drawable.rest2,
						RelativeLayout.ALIGN_PARENT_TOP,
						RelativeLayout.ALIGN_PARENT_RIGHT) };
		
		SwitcherDesc[] swCinema = {
				new SwitcherDesc(R.drawable.cinema1,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_LEFT),
				new SwitcherDesc(R.drawable.cinema2,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_RIGHT),
				new SwitcherDesc(R.drawable.cinema3,
						RelativeLayout.ALIGN_PARENT_TOP,
						RelativeLayout.ALIGN_PARENT_RIGHT) };
		
		SwitcherDesc[] swShopping = {
				new SwitcherDesc(R.drawable.shopping1,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_LEFT),
				new SwitcherDesc(R.drawable.shopping2,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_RIGHT),
				new SwitcherDesc(R.drawable.shopping3,
						RelativeLayout.ALIGN_PARENT_TOP,
						RelativeLayout.ALIGN_PARENT_RIGHT) };
		
		SwitcherDesc[] swHotel = {
				new SwitcherDesc(R.drawable.hotel1,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_LEFT),
				new SwitcherDesc(R.drawable.hotel2,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_RIGHT),
				new SwitcherDesc(R.drawable.hotel3,
						RelativeLayout.ALIGN_PARENT_TOP,
						RelativeLayout.ALIGN_PARENT_RIGHT) };
		SwitcherDesc[] a = {
				new SwitcherDesc(R.drawable.red,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_LEFT),
				new SwitcherDesc(R.drawable.blue,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_RIGHT)};
		SwitcherDesc[] b = {
				new SwitcherDesc(R.drawable.blue,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_LEFT),
				new SwitcherDesc(R.drawable.red,
						RelativeLayout.ALIGN_PARENT_BOTTOM,
						RelativeLayout.ALIGN_PARENT_RIGHT)};

		switchers.get(0).ImageSource = Arrays.asList(swRest);
		switchers.get(1).ImageSource = Arrays.asList(swCinema);
		switchers.get(2).ImageSource = Arrays.asList(swShopping);
		switchers.get(3).ImageSource = Arrays.asList(swHotel);
		for (int i = 0; i < switchers.size(); i++)
		{
			TextImageSwitcher switcher;
			switcher = switchers.get(i);

			if (i > 3)
			{
				if (i % 2 == 0)
					switcher.ImageSource = (Arrays.asList(a));
				else
					switcher.ImageSource = (Arrays.asList(b));
			}
			switcher.updateImage();
			switcher.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					Toast toast = Toast.makeText(getBaseContext(),
							"Coming soon", 100);
					toast.show();
				}
			});
		}
		switchers.get(0).setText("���������");
		switchers.get(1).setText("����");
		switchers.get(3).setText("�����");
		switchers.get(2).setText("��������");
		switchers.get(4).setText("CheckIn NOW");
		switchers.get(5).setText("Jam.KZ");
		switchers.get(6).setText("�������");
		switchers.get(7).setText("���������");
		
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		continueUpdating = true;
		mHandler.removeCallbacks(mUpdateTimeTask);
		mHandler.postDelayed(mUpdateTimeTask, UPDATE_DELAY);
	}

	private void beginUpdating()
	{

	}

	private void updateNext()
	{
		if (continueUpdating)
		{
			currButton = (int) Math
					.round((Math.random() * switchers.size() - 1));
			if (currButton >= switchers.size())
				currButton = 0;
			if (currButton < 0)
				currButton = switchers.size() - 1;
			switchers.get(currButton).updateImage();
			mHandler.removeCallbacks(mUpdateTimeTask);
			mHandler.postDelayed(mUpdateTimeTask, UPDATE_DELAY);
		}
	}

	private Runnable mUpdateTimeTask = new Runnable()
	{
		public void run()
		{
			updateNext();
		}
	};


}