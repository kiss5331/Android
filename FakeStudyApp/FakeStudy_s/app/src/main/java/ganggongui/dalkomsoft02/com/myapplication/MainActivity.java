package ganggongui.dalkomsoft02.com.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.daum.adam.publisher.AdView;
import net.daum.adam.publisher.impl.AdError;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import showcaseview.FragmentAdapter;
import showcaseview.Fragment_thred;


public class MainActivity extends ActionBarActivity implements Fragment_thred.onBackButtonListener {

    private static final String LOGTAG = "BannerTypeXML1";
    private AdView adView = null;

    private ViewPager viewPager;

    private FragmentAdapter fragmentAdapter;

    private TextView TV_info;

    private ImageView facebookBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setID();

        initAdam();

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);

        // 페이져가 가지고 있을 수 있는 페이지
        // 메모리 갯수를 5개로 설정합니다.

        viewPager.setOffscreenPageLimit(5);


        // 개발자 정보 클릭 시

        TV_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetLog();
            }
        });

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=ganggongui.dalkomsoft02.com.myapplication");
                PackageManager pm = v.getContext().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                for (final ResolveInfo app : activityList) {
                    if ((app.activityInfo.name).contains("facebook")) {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareIntent.setComponent(name);
                        v.getContext().startActivity(shareIntent);
                        break;
                    }
                }
            }
        });


    }

    private void SweetLog() {

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(getString(R.string.Titleinfo))
                .setContentText(getString(R.string.Titltsub))
                .show();

    }


    // 각 View의 id 요소를 지정 합니다.

    private void setID() {

        TV_info = (TextView) findViewById(R.id.infoBtn);

        viewPager = (ViewPager) findViewById(R.id.pager);

        facebookBtn = (ImageView) findViewById(R.id.facebook);

    }

    @Override
    public void goBack() {

        // 마지막 프래그 먼트에서 버튼클릭시
        // 해당 메소드가 호출되어
        // 뷰페이져를 첫번째 위치로 돌립니다.

        viewPager.setCurrentItem(0);
    }

    private void initAdam() {
        // AdFit(Ad@m) sdk 초기화 시작
        adView = (AdView) findViewById(R.id.adview);

        // 광고 리스너 설정

        // 1. 광고 클릭시 실행할 리스너
        adView.setOnAdClickedListener(new AdView.OnAdClickedListener() {
            @Override
            public void OnAdClicked() {
                Log.i(LOGTAG, "광고를 클릭했습니다.");
            }
        });

        // 2. 광고 내려받기 실패했을 경우에 실행할 리스너
        adView.setOnAdFailedListener(new AdView.OnAdFailedListener() {
            @Override
            public void OnAdFailed(AdError adError, String s) {
                Log.w(LOGTAG, s);
            }

        });

        // 3. 광고를 정상적으로 내려받았을 경우에 실행할 리스너
        adView.setOnAdLoadedListener(new AdView.OnAdLoadedListener() {
            @Override
            public void OnAdLoaded() {
                Log.i(LOGTAG, "광고가 정상적으로 로딩되었습니다.");
            }
        });

        // 4. 광고를 불러올때 실행할 리스너
        adView.setOnAdWillLoadListener(new AdView.OnAdWillLoadListener() {
            @Override
            public void OnAdWillLoad(String s) {
                Log.i(LOGTAG, "광고를 불러옵니다. : " + s);
            }
        });


        // 5. 전면형 광고를 닫았을때 실행할 리스너
        adView.setOnAdClosedListener(new AdView.OnAdClosedListener() {
            @Override
            public void OnAdClosed() {

            }
        });


        // 할당 받은 clientId 설정
        adView.setClientId("DAN-toddstn4vcxk");


        // 광고 갱신 주기를 12초로 설정
        // adView.setRequestInterval(12);


        // 광고 영역에 캐시 사용 여부 : 기본 값은 true
        adView.setAdCache(false);

        // Animation 효과 : 기본 값은 AnimationType.NONE
        adView.setAnimationType(AdView.AnimationType.FLIP_HORIZONTAL);
        adView.setVisibility(View.VISIBLE);
    }
}
