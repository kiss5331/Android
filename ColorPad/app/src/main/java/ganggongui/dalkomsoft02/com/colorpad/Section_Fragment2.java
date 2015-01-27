package ganggongui.dalkomsoft02.com.colorpad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import whdghks913.tistory.floatingactionbutton.FloatingActionButton;

/**
 * Created by ganggongui on 15. 1. 27..
 *
 * 색상 보기 프레그 먼트
 */
public class Section_Fragment2 extends Fragment {


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_section2, container, false);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        // 플로팅 버튼 생성
        FloatingActionButton mFloatingButton = (FloatingActionButton) view.findViewById(R.id.mFloatingActionButton);
        // 버튼 이미지
        mFloatingButton.setImageResource(R.drawable.plus);
        // 애니메이션 속도
        mFloatingButton.setDuration(500);

    }
}
