package showcaseview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ganggongui.dalkomsoft02.com.myapplication.R;


/**
 * Created by ganggongui on 15. 1. 21..
 */
public class Fragment_thred extends Fragment {

    private View view;

    // 첫번째 프래그 먼트로 돌아가는
    // 버튼 입니다.
    private ImageButton backbtn;

    // 엑티비와의 통신을 위한
    // 인터페이스 변수를 선언 합니다.
    private onBackButtonListener backButtonListener;


    public interface onBackButtonListener {
        public void goBack();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // 프레그먼트는 액티비티를 상속하지 않았으므로
        // 콘텐스트 정보를 얻기위해 엑티비티를 형변환하여
        // 값으로 넣어줍니다.
        backButtonListener = (onBackButtonListener) activity;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_thred, container, false);

        return this.view;
    }


    @Override
    public void onStart() {
        super.onStart();

        backbtn = (ImageButton) view.findViewById(R.id.btn1);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 버튼 클릭시 인터페이스의 메소드를
                // 이용하여 엑티비티와 통신합니다.
                backButtonListener.goBack();
            }
        });

    }
}
