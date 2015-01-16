package com.dalkomsoft02.ganggongui.seouldustapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ganggongui on 15. 1. 15..
 */
public class JSONPaser {


    // 서비스명
    final private String SERVICE_NAME                           = "ForecastWarningUltrafineParticleOfDustService";

    // 배열의 키값
    final private String JSON_ROW                               = "row";

    final private String NAME_CODE                              = JSONPaser.class.getName();

    // 정제된 데이터를 담을 ArrayList
    private List<String> DataList                               = new ArrayList<String>();

    // GET 방식으로 가져온 JSON 데이터
    private String JSONdata                                     = null;





    // 생성자 메소드 GET 방식으로 가져온 JSON 데이터를 인자 값으로 받는다.

    public JSONPaser(String JSONdata) {

        this.JSONdata = JSONdata;

        // JSON 데이터 파싱
        PaserJSONdata();
    }

    private void PaserJSONdata() {

        try {

            JSONObject jsonObject = new JSONObject(JSONdata);

            jsonObject = jsonObject.getJSONObject(SERVICE_NAME);

            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROW);


            jsonObject = jsonArray.getJSONObject(0);

            Iterator<String> iterator = jsonObject.keys();


            do {

                String a = jsonObject.getString(iterator.next());

                DataList.add(a);

            } while (iterator.hasNext());



        } catch (Exception e) {

            Log.e(NAME_CODE, String.valueOf(e));

        }


    }

    // JSON 데이터를 담은 ArrayList를 돌려준다
    public List<String> getJsonData() {

        return DataList;
    }


}
