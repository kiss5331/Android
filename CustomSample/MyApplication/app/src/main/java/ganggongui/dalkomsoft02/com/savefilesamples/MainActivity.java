package ganggongui.dalkomsoft02.com.savefilesamples;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private String filename = "장공의님입니다.txt";

    private String text = "wkdasdnasjkndkjanfajsbfksbfkasbfsbfhsdbfkhsdbfhksbdfjsdhbfs";

    // 내용을 표시할 텍스트 뷰

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button writeBtn, readBtn;


        writeBtn = (Button) findViewById(R.id.writeBtn);

        readBtn = (Button) findViewById(R.id.readBtn);

        textView = (TextView) findViewById(R.id.output_TV);

        writeBtn.setOnClickListener(this);

        readBtn.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // 파일을 생성 및 내용을 작성하는 메소드

    private void FileWrite(String FileName, String text) {

        // 파일 생성
        // getFileDir : 내부 파일 디렉토리를 반환 , FileName : 파일명
        File file = new File(getApplicationContext().getFilesDir(), FileName);

        //파일 내용 작성

        try {
            // 파일명 , 파일의 공유 모드
            // openFileOutput() 메소드는 FileName 의 파일명을
            // 가진 파일의 내용를 작성합니다.
            FileOutputStream fileOutputStream = openFileOutput(FileName, Context.MODE_PRIVATE);

            fileOutputStream.write(text.getBytes());

            fileOutputStream.close();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    // 생성한 파일을 가져오는 메소드

    private File getfile(Context context, String url) {

        // get 한 파일을 담을 파일 변수
        File file = null;

        try {

            // getLastPathSegment()
            // 해당경로의 마지막 이름을 가져오는 메소드입니다.
            String fimename = Uri.parse(url).getLastPathSegment();

            // createTempFile(String prefix, String suffix, File directory)
            // 파일명의 접두사와 접미사를 사용하여 지정된 디렉토리에 파일 캐시를 만듭니다.
            file = File.createTempFile(fimename, null, context.getCacheDir());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return file;
    }

    // 가져온 파일의 내용을 읽어오는 메소드


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.writeBtn:

                // 파일 생성 메소드 호출
                FileWrite(filename, text);

                MyToast(filename + "파일이 생성되었습니다." + "\n" + getApplicationContext().getFilesDir());


                break;

            case R.id.readBtn:

                // 생성된 파일을 가져와 해당 변수에 저장
                File file = getfile(getApplicationContext(), String.valueOf(getFilesDir()));

                MyToast("파일경로:" + file.getPath());
                break;

            default:

                break;


        }

    }

    private void MyToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

    }

}
