package com.example.demo;

import java.util.Timer;
import java.util.TimerTask;


public class TestData {

	public static void main(String[] args) {
		System.out.println();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("PPPPPPPPPPPPPPPPP");
			}
		};
		Timer timer = new Timer();
		timer.schedule(timerTask, 3000);
	}
}


