package ganggongui.dalkomsoft02.com.replacefragmentwithanother;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private boolean Checking = false;

    private final String FRAG_ID_1 = "one";
    private final String FRAG_ID_2 = "twe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 첫번째 프래그먼트를 컨테이너가 얻도록 해줍니다.
        // 반드시 commit() 을 해줘야 적용 됩니다.
        getSupportFragmentManager().
                beginTransaction().
                // 프래그먼트 스텍에 프래그먼트와 이름을 지정해줍니다.
                        // 스텍 형식으로 저장됨으로 첫번째 프래그먼트가 화면에 표시 됩니다.

                        add(R.id.container, new TweFragment(), FRAG_ID_2).
                add(R.id.container, new OneFragment(), FRAG_ID_1).
                commit();


        // 프래그 먼트 교체를 위한 버튼 생성
        Button button = (Button) findViewById(R.id.replaceBtn);

        // 버튼 클릭시
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          String FragmentName = null;

                                          // 클릭할 때 마다 boolean 값을 변경
                                          if (Checking) {
                                              FragmentName = FRAG_ID_1;
                                              Checking = false;
                                          } else if (!Checking) {
                                              FragmentName = FRAG_ID_2;
                                              Checking = true;
                                          }


                                          FragmentManager fragmentManager = getSupportFragmentManager();


                                          // 프래그 먼트 변경을 위한 FragmentTransaction 선언

                                          FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                                          // 버튼 클릭시 Checking 을 통하여 매변 프래그먼트
                                          // 변수 속에 프래그먼트를 교체하여 넣어줌


                                          // 교체될 컨테이너 , 프래그먼트
                                          fragmentTransaction.replace(

                                                  R.id.container,
                                                  // 프래그먼트 매니져를 통해 프래그먼트를 가져옴
                                                  fragmentManager.findFragmentByTag(FragmentName));

                                          fragmentTransaction.addToBackStack(null);

                                          fragmentTransaction.commit();


                                      }
                                  }

        );

    }


}
