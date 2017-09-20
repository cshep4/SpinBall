package com.spinball.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.spinball.game.SpinBall;

public class AndroidLauncher extends AndroidApplication implements AppInterface{

	SpinBall spinBall;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		spinBall = new SpinBall();
		spinBall.setInterface(this);

		initialize(spinBall, config);
	}

	@Override
	public void onStart() {
		super.onStart();
	}
}
