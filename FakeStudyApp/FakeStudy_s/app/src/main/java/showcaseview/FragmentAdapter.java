package showcaseview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganggongui on 15. 1. 21..
 */
public class FragmentAdapter extends FragmentPagerAdapter {


    // 페이져에 들어갈 프레그먼트를 담는
    // 리스트 배열입니다.
    private List<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);


        // 인스턴스 변수 생성 및
        // 리스트에 프래그먼트 요소를 추가
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new Main_Fragment_one());
        fragments.add(new Fragment_twe());
        fragments.add(new Fragment_fore());
        fragments.add(new Fragment_thred());


    }


    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
