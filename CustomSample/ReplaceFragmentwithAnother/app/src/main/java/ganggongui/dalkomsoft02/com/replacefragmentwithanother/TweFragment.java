package ganggongui.dalkomsoft02.com.replacefragmentwithanother;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ganggongui on 15. 1. 22..
 */
public class TweFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 해당 레이아웃을 객체화하여 메모리에 올려줍니다.
        this.view = inflater.inflate(R.layout.fragment_twe, container, false);

        //반환
        return this.view;
    }
}
