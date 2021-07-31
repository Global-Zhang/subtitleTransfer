package com.subtitle.transfer.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TimeWordsCombine {
    private static Date date = new Date();
    private static SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
    private static String timeStamp = sdf.format(date);
    private static String stringPro = "{\"font_size\":0.4,\"font_color\":\"#FFFFFF\",\"background_alpha\":0.5,\"background_color\":\"#9C27B0\",\"Stroke\":\"none\",\"body\":[";
    private static String stringBac = "]}";
    private static String contentTimePro = "{\"from\":";
    private static String betweenTime = ",\"to\":";
    private static String betweenTimeWord = ",\"location\":2,\"content\":\"";
    private static String subLineEnds = "\"}" ;

    public String outputDocString(List<String> wordList,List<Double> numberList) {
        List<String> fixedNumberList = new ArrayList<>();
        List<String> numberListEven = new ArrayList<>();
        List<String> numberListOdd = new ArrayList<>();


        for (int i = 0; i < numberList.toArray().length; i++) {
            char[] chars = numberList.toArray()[i].toString().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '.' && chars.length >= (j+4)){
                    fixedNumberList.add(numberList.toArray()[i].toString().substring(0,j+4));
                }
                if (chars[j] == '.' && chars.length < (j+4) ){
                    fixedNumberList.add(numberList.toArray()[i].toString());
                }
            }
        }

        for (int i = 0; i < fixedNumberList.toArray().length; i++) {
            if ((i+1)%2 == 0){
                numberListOdd.add(fixedNumberList.toArray()[i].toString());
            }else{
                numberListEven.add(fixedNumberList.toArray()[i].toString());
            }
        }

        Object[] numberAft = numberListOdd.toArray();
        Object[] numberPro = numberListEven.toArray();
        //System.out.println(numberPro.length+"====="+numberAft.length);
        Object[] word = wordList.toArray();
        //System.out.println(word.length);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringPro);
        //System.out.println(numberPro.length+"==="+numberAft.length+"===="+ word.length);
        for (int i = 0; i < word.length; i++) {
            String str;
            if (i<word.length-1){
                if (i < 1){
                    str = contentTimePro + numberPro[i].toString()
                            + betweenTime + numberAft[i].toString()
                            + betweenTimeWord + word[i].toString() +"本字幕于"+timeStamp+"生成"
                            + subLineEnds + ",";
                    stringBuilder.append(str);
                }
                str = contentTimePro + numberPro[i].toString()
                        + betweenTime + numberAft[i].toString()
                        + betweenTimeWord + word[i].toString()
                        + subLineEnds + ",";
                stringBuilder.append(str);
            }else{
                str = contentTimePro + numberPro[i].toString()
                        + betweenTime + numberAft[i].toString()
                        + betweenTimeWord + word[i].toString()+"感谢github:Global-Zhang先生的技术支持"
                        + subLineEnds ;
                stringBuilder.append(str);
            }

        }
        stringBuilder.append(stringBac);
        String finalString = stringBuilder.toString().trim().replaceAll("[\n]","");
        //System.out.println(finalString);
        return finalString;
    }
}

