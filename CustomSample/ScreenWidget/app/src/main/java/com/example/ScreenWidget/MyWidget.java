package com.example.ScreenWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

// ScreenWidget을 위한 기본 코드가 구현되어 있는 AppWidgetProvider를 상속받아서 구현함
public class MyWidget extends AppWidgetProvider {
	
	boolean isFirst=true;
	

	// 이 Receiver가 동작하는 경우는 3가지 경우임
    // intent 발생으로 자신이 실행되었을 때 자신을 실행시킨 intent가 전달되고,
    // 전달된 intent 조건을 보고 분기 처리함함	@Override
	public void onReceive(Context context, Intent intent) {
	    if(intent.getAction().equals("com.multi.ACTION_CLICK")){
	    	// 위젯에서 클릭 이벤트가 발생해서, 내 Activity를 호출하려 함
	    	Intent newIntent = new Intent(context, Activity1.class);
	    	newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	context.startActivity(newIntent);
	    }else if(intent.getAction().equals("com.multi.ACTION_CLOCK")){
	    	Log.d("kimj-screenwidget", "receiver:"+intent.getStringExtra("current_time"));
	    	// Service에서 시간 데이터를 넘긴 경우
	    	// 내가 직접 화면 업데이트를 하지 못하므로, home app에게 의뢰해야 함
	    	// RemoteView로 의뢰함.
	    	RemoteViews remoteView=
	    			new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	    	remoteView.setTextViewText(R.id.textView, intent.getStringExtra("current_time"));
	    	
	    	// 의뢰하기
	    	AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    	manager.updateAppWidget(new ComponentName(context,MyWidget.class), remoteView);
	    }else{
	    	//ScreenWidget이 등록되었을 때,
            // 이미 상위 클래스내에 Widget을 위한 기본 코드가 구현되어 있음음
    	super.onReceive(context, intent);
	    	
	    	// Service를 구동함
	    	context.startService(new Intent("com.multi.ACTION_SERVICE"));
	    }
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {

		super.onDeleted(context, appWidgetIds);
		// 필요 없는 경우에 서비스를 제거함
		context.stopService(new Intent("com.multi.ACTION_SERVICE"));
	}
	
	// 처음 ScreenWidget을 화면에 추가하는 순간 호출되며, update 주기마다 한번씩 호출됨. min: 30분 주기
    // home app에서는 ScreenWidget이 추가되는 순간 ScreenWidget을 식별하기 위해서 id 값을 부여하며,
    // 하나의 ScreenWidget을 유저가 화면에 여라 개를 추가할 수도 있음
    @Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		if(isFirst){
			isFirst=false;
			RemoteViews remoteView = new RemoteViews(
					context.getPackageName(),R.layout.widget_layout);
			Calendar cal = Calendar.getInstance();
			remoteView.setTextViewText(R.id.textView, cal.get(Calendar.HOUR_OF_DAY)+":"+
					cal.get(Calendar.MINUTE));
			
			// 문자열에서 이벤트가 발생한 경우, 특정 Intent를 발생시켜 달라고 의뢰함
			Intent intent = new Intent("com.multi.ACTION_CLICK");
			// Intent는 우리가 준비하나, 우리가 발생시키지 못하고 의뢰하는 경우에는
            // 꼭 PendingIntent로 표현이 되어야 함
            // requestCode : activity에서 의미가 있음
            // Flag : intent를 의뢰했을 때 실제 그 Intent가 발생하지 않았는데,
            // 하나 더 의뢰가 들어가면 어떻게 할 것인가?란 의문에 대한 부분분
		PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			remoteView.setOnClickPendingIntent(R.id.textView, pIntent);
			
			appWidgetManager.updateAppWidget(appWidgetIds, remoteView);
		}
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	    

}
