package colormixer;

import android.util.Log;

/**
 * Created by ganggongui on 15. 1. 30..
 */
public class ColorMixer {

    // 혼합된 색상
    private String RGB_COLOR;

    private final String ERROR_CODE = ColorMixer.this.getClass().getName();


    public ColorMixer(String COLOR_CODE, String COLOR_CODE1) {

        RGB_COLOR = colormixing(COLOR_CODE, COLOR_CODE1).toLowerCase();

    }

    // 빌어먹을 10진수로 바꾸기 위한 작업이다.

    private String colormixing(String color1, String color2) {


        color1 = color1.replace("#", "");

        color2 = color2.replace("#", "");
        final int R = 0;

        final int G = 2;

        final int B = 4;

        //  합칠 색상들 10진수 변환

        String Red = getHexToDec(color1.substring(R, G));

        String Red2 = getHexToDec(color2.substring(R, G));

        String Green = getHexToDec(color1.substring(G, B));

        String Green2 = getHexToDec(color2.substring(G, B));

        String Blue = getHexToDec(color1.substring(B, color1.length()));

        String Blue2 = getHexToDec(color2.substring(B, color1.length()));


        String mixing_R = mixing(Red, Red2);

        String mixing_G = mixing(Green, Green2);

        String mixing_B = mixing(Blue, Blue2);


        Log.e("R", mixing_R + "");

        Log.e("G", mixing_G + "");

        Log.e("B", mixing_B + "");


        String mix_RGB = "#" + check_leath(getDecToHex(mixing_R)) + check_leath(getDecToHex(mixing_G)) + check_leath(getDecToHex(mixing_B));

        return mix_RGB;
    }


    //16진수  - > 10진수로 변환

    private String HaxCodeChanger(String RGB) {

        // 16진 수 알파벳
        final String[] HAX = {"a", "b", "c", "d", "e", "f"};

        // R , G , B값은 각각 2자리 이므로
        // 1자리 씩 분리
        final String[] RGB_SP = {RGB.substring(0, 1), RGB.substring(1, 2)};

        // 16진수 변환을 위해 아스키코드값으로
        // 변환후 다시 10진수 변환을 위해 아
        // 스키 코드 - 16진수 만큼을 뺴준다.
        final int ASCLL_SIMBLE = 87;


        // 10진수로 변환된 RGB를 담을 변수

        int DEC_1;

        int DEC_2;


        int k = 0;

        for (String CODE : RGB_SP) {

            int i = 0;

            for (String Hax : HAX) {

                if (CODE.equals(Hax) && k == 0) {

                    RGB_SP[0] = String.valueOf(Hax.charAt(0) - ASCLL_SIMBLE);

                } else if (CODE.equals(Hax) && k == 1) {

                    RGB_SP[1] = String.valueOf(Hax.charAt(0) - ASCLL_SIMBLE);

                }


                i++;

            }

            k++;
        }

        DEC_1 = Integer.valueOf(RGB_SP[0]);

        DEC_2 = Integer.valueOf(RGB_SP[1]);


        // 최종 16진 수 변환
        DEC_1 = DEC_1 * 16;


        //R,G,B 값 변환
        return String.valueOf(DEC_1 + DEC_2);
    }

    private String getHexToDec(String hex) {
        long v = Long.parseLong(hex, 16);
        return String.valueOf(v);
    }

    private String mixing(String RGBs, String RGB1s) {

        int RGB = Integer.valueOf(RGBs);

        int RGB1 = Integer.valueOf(RGB1s);


        int MIXING_RGB = RGB;

        if (RGB > RGB1) {

            MIXING_RGB = RGB - RGB1;

        } else if (RGB < RGB1) {

            MIXING_RGB = RGB1 - RGB;
        }

        return String.valueOf(MIXING_RGB);
    }


    private String getDecToHex(String dec) {

        Long intDec = Long.parseLong(dec);
        return Long.toHexString(intDec).toLowerCase();
    }

    public String getMixColor() {

        Log.i(ERROR_CODE, RGB_COLOR);

        return RGB_COLOR.toUpperCase();


    }


    private String check_leath(String color) {
        if (color.length() != 2) {

            if (color.length() == 0) {
                color = "00";
            } else {
                color = "0" + color;
            }

        }


        return color;
    }

}
