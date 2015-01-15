package com.example.ScreenWidget;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;

public class WidgetService extends Service {

	// thread를 돌려서 시간 데이터를 발생시킬 것인데,
    // 의미없는 순간에 멈추게 하려고 선언하는 변수임
	boolean isStarted=false;
	Calendar cal=null;
	
	// Screen Off시 Service가 동작할 필요는 없는 부분을 제어함
    // 시스템에서 Screen On/Off시 broadcast intent를 띄워줌
    // 그것에 반응할 receiver를 만들면 됨
	BroadcastReceiver brOn = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			isStarted=true;
			ClockThread ct=new ClockThread();
			ct.start();			
		}		
	};
	
	BroadcastReceiver brOff = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			isStarted=false;			
		}		
	};
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		// onStart 함수는 반복 호출 가능성이 있음
		if(!isStarted){
			// BroadcastReceiver는 아주 자주 코드 내부에서 직접 등록하며
			// manifest에 등록되어 있지 않아도 됨
			registerReceiver(brOn, new IntentFilter(Intent.ACTION_SCREEN_ON));
			registerReceiver(brOff, new IntentFilter(Intent.ACTION_SCREEN_OFF));
			isStarted=true;
			ClockThread ct = new ClockThread();
			ct.start();
		}
		return super.onStartCommand(intent, flags, startId);
	};
	
	// Service 종료 시점에 호출
	@Override
	public void onDestroy() {
		super.onDestroy();
		// 코드에서 동적으로 등록시킨 receiver는 꼭 필요 없는 순간 코드에서 등록 해제를 해주어야 함
		unregisterReceiver(brOn);
		unregisterReceiver(brOff);
		isStarted=false;
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {

		return null;
	}
	
	class ClockThread extends Thread{
		
		public void run(){
			Log.d("kimj-screenwidget", "service run...");
			Calendar current;
			while(isStarted){
				current=Calendar.getInstance();
				
				// 시간이 분단위 변경이 된 경우에만, receiver에게 알려줘서
				// widget이 update하게 할 것임
				if(cal == null || 
						cal.get(Calendar.HOUR_OF_DAY)!=current.get(Calendar.HOUR_OF_DAY)||
						cal.get(Calendar.MINUTE) != current.get(Calendar.MINUTE)){
							// 데이터 receiver에게 알려줄 것임
							// intent를 띄워서 extra 데이터를 활용함함
						Intent intent = new Intent();
						intent.setAction("com.multi.ACTION_CLOCK");
						intent.putExtra("current_time", 
								current.get(Calendar.HOUR_OF_DAY)+":"+
								current.get(Calendar.MINUTE));
						sendBroadcast(intent);
						
						// 현재 시간을 다음번과 비교하기 위해 멤버 변수에 저장함
						cal=current;
						Log.d("kimj-screenwidget", "service send");
				}else{
					SystemClock.sleep(1000);
				}
			}
		}
	}
	

}
