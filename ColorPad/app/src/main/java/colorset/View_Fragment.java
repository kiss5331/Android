package colorset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.john.waveview.WaveView;

import ganggongui.dalkomsoft02.com.colorpad.R;

/**
 * Created by ganggongui on 15. 1. 28..
 */
public class View_Fragment extends Fragment {

    private final String ERROR_CODE = View_Fragment.this.getClass().getName();

    private View view;

    private ImageView check_Btn;

    private WaveView colorwave_1;

    // 인터페이스 변수
    private onChangeBtn changeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_pview, container, false);


        changeBtn = (onChangeBtn) getActivity();


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        setToolBar();

        check_Btn = (ImageView) view.findViewById(R.id.checkBtn);

        colorwave_1 = (WaveView) view.findViewById(R.id.color_wave_1);

        check_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeBtn.onclick();
            }
        });


    }

    private void setToolBar() {

        try {


            Toolbar toolbar;

            toolbar = (Toolbar) view.findViewById(R.id.toolbar);

            toolbar.setTitleTextColor(getResources().getColor(R.color.DeepWhite));

            toolbar.setSubtitleTextColor(getResources().getColor(R.color.DeepWhite));

            toolbar.setTitle(getString(R.string.Toolbar_Title));

            toolbar.setSubtitle(getString(R.string.Toolbar_subTitle));

        } catch (Exception e) {

            Log.e(ERROR_CODE, e + "");
        }

    }

    // 통신을 위한 인터페이스
    public interface onChangeBtn {
        public void onclick();
    }
}
