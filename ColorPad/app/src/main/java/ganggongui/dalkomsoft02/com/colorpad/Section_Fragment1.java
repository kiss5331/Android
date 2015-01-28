package ganggongui.dalkomsoft02.com.colorpad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import whdghks913.tistory.floatingactionbutton.FloatingActionButton;


/**
 * Created by ganggongui on 15. 1. 27..
 * <p/>
 * 메인 화면 프래그먼트
 */
public class Section_Fragment1 extends Fragment {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_section1, container, false);

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

        // 버튼 클릭시
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               startActivity(new Intent(getActivity(), Setting_Activity.class));
                // 애니메이션
                //  getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


    }




}
