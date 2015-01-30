package showcaseview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import ganggongui.dalkomsoft02.com.myapplication.R;
import ganggongui.dalkomsoft02.com.myapplication.WindowTouchService;

/**
 * Created by ganggongui on 15. 1. 21..
 */
public class Main_Fragment_one extends Fragment implements View.OnClickListener {

    private final String EARR_CODE = Main_Fragment_one.class.getName();

    private View view;

    // 최상위 뷰 활성화 버튼

    private Button StartBtn;

    // 최상위 뷰 비활성화 버튼

    private Button EndBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.main_fragment_one, container, false);

        return this.view;
    }


    @Override
    public void onStart() {
        super.onStart();

        setID();


    }

    // 각 뷰의 ID 요소를 지정합니다.

    private void setID() {

        StartBtn = (Button) view.findViewById(R.id.Btnstart);

        EndBtn = (Button) view.findViewById(R.id.BtnEnd);

        StartBtn.setOnClickListener(this);

        EndBtn.setOnClickListener(this);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Btnstart:

                // 서비스를 시작합니다.

                try {
                    WindowTouchService.mManager.removeView(WindowTouchService.mView);
                    getActivity().stopService(
                            new Intent(getActivity(), WindowTouchService.class)
                    );
                } catch (Exception e) {
                    Log.e("teg", "aaaaaaáa");
                }

                Toast.makeText(getActivity(), getString(R.string.start_text), Toast.LENGTH_SHORT).show();

                getActivity().startService(
                        new Intent(getActivity(), WindowTouchService.class)
                );

                getActivity().finish();

                break;


            case R.id.BtnEnd:

                // 서비스가 실행되지 않은 상태에서의
                // 중지시 에러가 발생하므로 예외처리를 해줍니다

                try {
                    WindowTouchService.mManager.removeView(WindowTouchService.mView);
                    Toast.makeText(getActivity(), getString(R.string.exit_text), Toast.LENGTH_SHORT).show();
                    getActivity().stopService(
                            new Intent(getActivity(), WindowTouchService.class)
                    );


                } catch (Exception e) {
                    Log.e(EARR_CODE, e.getMessage());
                }


                break;

            default:

                break;

        }

    }
}
