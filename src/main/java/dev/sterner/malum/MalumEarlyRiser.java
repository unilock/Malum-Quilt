package dev.sterner.malum;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;

public class MalumEarlyRiser implements Runnable{
	@Override
	public void run() {
		MixinExtrasBootstrap.init();
	}
}
