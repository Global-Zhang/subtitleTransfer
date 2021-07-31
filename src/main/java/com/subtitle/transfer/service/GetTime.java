package com.subtitle.transfer.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
*   已完成 2021.7.29
*   将从  处获得的字符串和正则表达式计算处理，获得毫秒数的时间
*   也就是含有00:00:00,620的 .srt文件中提取出时间，之后将 00:00:00,620 的时间格式改为 毫秒
* 
*  */
@Service
public class GetTime {
    //"8\n" +"00:00:30,480 --> 00:00:36,960\n" +"[音乐] 这种反常的天气事件在德国是不寻常的，";
    //public String contentResource;
    private static final String regexResource = "[0]{2}[:][0-9]{2}[:][0-9]{2}[,][0-9]{3}";


    public List<Double> changeTimeToMM(String contentResource){

        //通过正则表达式获取String格式的 00:00:00,620 的List
        Pattern pattern = Pattern.compile(regexResource);
        Matcher matcher = pattern.matcher(contentResource);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }

        //将00:00:00,620  改为毫秒，放入list中
        String colon = "[:]";
        String period = ",";
        ArrayList<Double> milliSeconds = new ArrayList<>();

        //开始处理 00:00:00,620
        Iterator<String> iterator01 = list.stream().iterator();
        while(iterator01.hasNext()){
            
            //除去冒号和逗号
            String str01 = iterator01.next().replaceAll(colon,"");
            String str02 = str01.replace(period,"");
            
            //将格式为 000000000 字符串跟改为 double格式的毫秒数
            char[] chars = str02.toCharArray();
            double preDoubleTime = 0;
            for (int i = chars.length - 1; i >= 0; i--) {

                //chars[i]现在是 ASC II 对应的字符而不是数字，需要减去48（‘0’）
                int j = chars[i] - '0';

                switch (i){
                    case 8:
                        preDoubleTime += (double) j /1000;
                        break;
                    case 7:
                        preDoubleTime += (double) j /100;
                        break;
                    case 6:
                        preDoubleTime += (double) j /10;
                        break;
                    case 5:
                        preDoubleTime += j;
                        break;
                    case 4:
                        preDoubleTime += (double) j *10;
                        break;
                    case 3:
                        preDoubleTime += (double) j *60;
                        break;
                    case 2:
                        preDoubleTime += (double) j *600;
                        break;
                    case 1:
                        preDoubleTime += (double) j *3600;
                        break;
                    case 0:
                        preDoubleTime += (double) j *36000;
                        break;
                }

            }
            //System.out.println(preDoubleTime);
            milliSeconds.add(preDoubleTime);
        }

        return milliSeconds;
    }
}
