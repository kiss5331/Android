package ganggongui.dalkomsoft02.com.colorpad;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import it.neokree.googlenavigationdrawer.GAccount;
import it.neokree.googlenavigationdrawer.GAccountListener;
import it.neokree.googlenavigationdrawer.GoogleNavigationDrawer;


public class MainActivity extends GoogleNavigationDrawer implements GAccountListener {


    @Override
    public void init(Bundle bundle) {


        //슬라이딩 메뉴 최상단 설정
        GAccount account = new GAccount(getString(R.string.menu_title),
                getString(R.string.menu_app) + String.valueOf(counterApp()), this.getResources().
                getDrawable(R.drawable.menu_icon), this.getResources().
                getDrawable(R.drawable.menu));
        this.addAccount(account);

        // set listener
        this.setAccountListener(this);

        this.addSection(this.newSection(getString(R.string.main_sestion), this.getResources().getDrawable(R.drawable.menu_program), new Section_Fragment1())
                .setSectionColor(Color.parseColor("#2196f3"))); // material blue 500


        this.addSection(this.newSection(getString(R.string.main_colorview), this.getResources().getDrawable(R.drawable.menu_pattelt), new Section_Fragment2())
                .setSectionColor(Color.parseColor("#00BCD4"))); // material red 500

        this.addDivisor();

        this.addSection(this.newSection("Section 1", new Section_Fragment1()));

        this.addSection(this.newSection("Section 2", new Fragment()));

        this.addDivisor();


    }


    // 다운 받은 어플의 숫자 카운터
    private int counterApp() {

        PackageManager packageManager = (PackageManager) getApplicationContext().getPackageManager();

        List<ApplicationInfo> AppList = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);

        List<ApplicationInfo> DownList = new ArrayList<>();

        for (ApplicationInfo info : AppList) {

            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

                DownList.add(info);

            }
        }

        return DownList.size() + 1;

    }

    @Override
    public void onAccountOpening(GAccount gAccount) {

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("Main_onResume", "!!!!!!!!!!!!!!");


    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("Main_onPause", "!!!!!!!!!!!!!!");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.e("Main_onStop", "!!!!!!!!!!!!!!");
    }
}
