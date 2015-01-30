package ganggongui.dalkomsoft02.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class FakeStudyActivity extends ActionBarActivity implements View.OnClickListener {

    private ImageButton nextBtn;

    private ImageButton pauseBtn;

    private ImageButton backBtn;

    private ImageButton exitBtn;

    private TextView Word_title;

    private TextView Word_sum;

    private TextView Title_num;

    private int NUMBER_COUNTER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_study);


        nextBtn = (ImageButton) findViewById(R.id.next_Btn);

        pauseBtn = (ImageButton) findViewById(R.id.pause_Btn);

        backBtn = (ImageButton) findViewById(R.id.back_Btn);

        exitBtn = (ImageButton) findViewById(R.id.exitBtn);

        Word_title = (TextView) findViewById(R.id.Title_word);

        Word_sum = (TextView) findViewById(R.id.Title_sub_word);

        Title_num = (TextView)findViewById(R.id.Title_number);

        nextBtn.setOnClickListener(this);

        pauseBtn.setOnClickListener(this);

        backBtn.setOnClickListener(this);

        exitBtn.setOnClickListener(this);

        setText(NUMBER_COUNTER);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.exitBtn:

                finish();

                break;

            case R.id.next_Btn:

                if (NUMBER_COUNTER != 9) {

                    NUMBER_COUNTER++;
                }
                setText(NUMBER_COUNTER);

                break;

            case R.id.pause_Btn:

                break;

            case R.id.back_Btn:

                if (NUMBER_COUNTER != 0) {

                    NUMBER_COUNTER--;
                }

                setText(NUMBER_COUNTER);

                break;

            default:

                break;


        }

    }

    private void setText(int NUMBER_COUNTER) {

        Word_title.setText(Dummydata[NUMBER_COUNTER]);

        Word_sum.setText(DummySum[NUMBER_COUNTER]);

        Title_num.setText(NUMBER_COUNTER+1+"/10");

    }

    private final String Dummydata[] = {"null", "er·ror", "frag·ment", "ab·surd·i·ty", "fraud", "wid·owed", "unrequited", "fool·ish", "log·ic", "ad·dict"};

    private final String DummySum[] = {"1.\n" +
            "가치 없는, 효과 없는, 중요하지 않은(insignificant).\n" +
            "2.\n" +
            "없는, 존재하지 않는(nil, nonexistent); 제로의, 제로와 같은; [수학] (집합에서) 공(空)의(empty, void).\n" +
            "3.\n" +
            "특징[표정]이 없는, 무표정한(expressionless)", "1.\n" +
            "[말·행위 등의] 잘못, 실수, 오류, 과실[in, of ‥] cf. MISTAKE [유의어]", "1.\n" +
            "(물건의) 파편, 조각; 단편\n" +
            "in fragments\n" +
            "산산조각으로; 단편적으로\n" +
            "burst into fragments\n" +
            "산산조각으로 부서지다.\n" +
            "2.\n" +
            "미완성의[불완전한, 잔존해 있는] 부분; 미완 유고(遺稿); (문장·시 등의) 단편, 단장(斷章).\n" +
            "3.\n" +
            "소량, 조금\n" +
            "her last fragment of sanity\n" +
            "그녀의 마지막 순간의 제정신.", "1.\n" +
            "[불] 불합리, 어리석음, 논리적 모순; (문학·연극상의) 부조리\n" +
            "the height of absurdity발음 듣기\n" +
            "어리석기 짝이 없음\n" +
            "the colossal absurdity발음 듣기\n" +
            "지독한 불합리.\n" +
            "2.\n" +
            "(종종 -ties) 어리석은 일[것, 언행]\n" +
            "say[utter] an absurdity발음 듣기\n" +
            "바보스러운 말을 하다.", "1.\n" +
            "[불] 사기, 기만, 협잡, 책략, 배신\n" +
            "a pious fraud발음 듣기\n" +
            "선의의 속임수, 방편상의 거짓말 (특히 신앙심을 강화하기 위한 방편)\n" +
            "practice fraud발음 듣기\n" +
            "사기치다.\n" +
            "2.\n" +
            "사기 행위, 부정 수단\n" +
            "a real estate fraud발음 듣기\n" +
            "부동산 사기.\n" +
            "3.\n" +
            "가짜; 사기[협잡]꾼.\n" +
            "4.\n" +
            "가장.\n" +
            "5.\n" +
            "(고어) 부정직, 교활, 부실.", "미망인이[홀아비가] 된; 외톨이가 된.", "[사랑이] 보답 없는, 일방적인; 무보수의\n" +
            "\"I'm in an unrequited love with my teacher.\" \"Really?\"발음 듣기\n" +
            "「난 우리 선생님을 짝사랑하고 있어.」 「정말?」", "1.\n" +
            "어리석은, 지각[분별] 없는\n" +
            "a foolish fellow발음 듣기\n" +
            "바보 같은 녀석\n" +
            "Don't be foolish!발음 듣기\n" +
            "어리석은 짓[말] 하지마\n" +
            "It was foolish of him[＝He was foolish] to waste his money on such trifles.발음 듣기\n" +
            "그런 시시한 것에 돈을 낭비하다니 그는 어리석었다.\n" +
            "2.\n" +
            "바보 같은; 사소한, 시시한, 하찮은\n" +
            "유의어 silly, stupid\n" +
            "어법 1. foolish 상식의 결여, 때로는 지능 내지는 판단력이 .. 더보기\n" +
            "a foolish action발음 듣기\n" +
            "바보 같은 짓.", "1.\n" +
            "논리학; [가] 논리학 책; 논리학 논문\n" +
            "deductive[inductive] logic발음 듣기\n" +
            "연역[귀납] 논리학\n" +
            "formal[pure] logic발음 듣기\n" +
            "형식[순수] 논리학\n" +
            "symbolic logic발음 듣기\n" +
            "기호 논리학.\n" +
            "2.\n" +
            "논법, 추리[추론]법\n" +
            "I couldn't follow his logic.발음 듣기\n" +
            "그의 논법을 따를 수 없었다.\n" +
            "3.\n" +
            "논리, 이론; [컴퓨터] 논리 회로(컴퓨터의 연산을 실행하기 위한 상호 접속)\n" +
            "logic symbols발음 듣기\n" +
            "논리 기호\n" +
            "the logic of art발음 듣기\n" +
            "미술 이론.\n" +
            "4.\n" +
            "(비격식) (발언·행동 등의) 도리, 조리, 올바른 판단; 강력한 설득력\n" +
            "He argues with admirable logic.발음 듣기\n" +
            "그는 아주 조리있게 논한다\n" +
            "What is the logic of doing this?발음 듣기\n" +
            "도대체 왜 이렇게 하는 거야?", "(마약 등의) 상용자, 중독자; (비격식) (어떤 습관에) 빠진 사람\n" +
            "a drug[an opium] addict발음 듣기\n" +
            "마약[아편] 중독자\n" +
            "a baseball addict발음 듣기\n" +
            "야구광."};


}
