package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ganggongui.dalkomsoft02.com.fibonachickenapp.R;

/**
 * Created by ganggongui on 15. 1. 23..
 */
public class Fragment_Main extends Fragment {

    private OnMainBtnClickListener mainBtnClickListener;

    private View view;


    /**
     * Called when a fragment is first attached to its activity.
     * {@link #onCreate(android.os.Bundle)} will be called after this.
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {

        mainBtnClickListener = (OnMainBtnClickListener) activity;

        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_main_layout, container, false);

        return this.view;
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();

        // 프래그먼트 변경을 위한 버튼
        Button MoveBtn = (Button) view.findViewById(R.id.btn_move_main);

        MoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainBtnClickListener.MainBtnClickListener();

            }
        });

    }

    public interface OnMainBtnClickListener {
        public void MainBtnClickListener();
    }


}
