package ganggongui.dalkomsoft02.com.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import showcaseview.FragmentAdapter;
import showcaseview.Fragment_thred;


public class MainActivity extends ActionBarActivity implements Fragment_thred.onBackButtonListener {


    private ViewPager viewPager;

    private FragmentAdapter fragmentAdapter;

    private TextView TV_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setID();

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);

        // 페이져가 가지고 있을 수 있는 페이지
        // 메모리 갯수를 5개로 설정합니다.

        viewPager.setOffscreenPageLimit(5);


        // 개발자 정보 클릭 시

        TV_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
            }
        });


    }


    // 각 View의 id 요소를 지정 합니다.

    private void setID() {

        TV_info = (TextView) findViewById(R.id.infoBtn);

        viewPager = (ViewPager) findViewById(R.id.pager);

    }

    @Override
    public void goBack() {

        // 마지막 프래그 먼트에서 버튼클릭시
        // 해당 메소드가 호출되어
        // 뷰페이져를 첫번째 위치로 돌립니다.

        viewPager.setCurrentItem(0);
    }
}
