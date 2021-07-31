package com.subtitle.transfer.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
@Service
public class PickWordAndNumFromCharArray {
    private static final Character[] NOT_CHINESE = {'0','1','2','3','4','5','6','7','8','9',':','-','>',',',' '};
    private static final Character[] NOT_CHI = {'0','1','2','3','4','5','6','7','8','9',':','-','>',',',' ',13,10};
    private ArrayList noChineseList = new MyArrayList(NOT_CHINESE);
    private ArrayList noChi = new MyArrayList(NOT_CHI);
    private StringBuffer numberString = new StringBuffer();
    private StringBuffer wordString = new StringBuffer();


    public String getNumberString(char[] chars) {
        List<String> numberList = new ArrayList<>();
        char[] b1 = chars;

        for (int i = 1; i < b1.length - 1; i++) {

            if (noChineseList.contains(b1[i])) {
                numberString.append(b1[i]);
                if (b1[i + 1] == 10) {
                    numberList.add(numberString.toString());
                    numberString = new StringBuffer();
                }
            }
        }
        Iterator<String> in = numberList.stream().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while(in.hasNext()){
            stringBuilder.append(in.next());
        }

        return stringBuilder.toString();
    }
    public List<String> getChineseList(char[] chars) {
        List<String> wordList = new LinkedList<>();
        List<String> wordListAfter02 = new LinkedList<>();

        char[] b2 = chars;

        for (int i = 2; i < b2.length - 2; i++) {

            if (!noChineseList.contains(b2[i])) {
                wordString.append(b2[i]);
                if (b2[i+1] == 10 || (b2[i+2] == 10 && " ".equals(b2[i+1]))) {
                    wordList.add(wordString.toString());
                    wordString = new StringBuffer();
                }
            }
        }

        Object[] array = wordList.toArray();

        for (Object value : array) {
            //获取索引以及含有文字元素的对应关系
            if (!"\n".equals(value.toString())) {
                wordListAfter02.add((value.toString()));
            }
        }

        return wordListAfter02;
    }

    public List<String> getEnglishList(char[] chars){
        List<String> wordList = new LinkedList<>();
        List<String> wordListAfter02 = new LinkedList<>();

        char[] b2 = chars;

        for (int i = 2; i < b2.length; i++) {

            if (b2[i] == 32 || (65 <= b2[i] && 90 >= b2[i]) || (97 <= b2[i] && 122 >= b2[i])) {
                wordString.append(b2[i]);
                if (b2[i+1] == 10) {
                    wordList.add(wordString.toString());
                    wordString = new StringBuffer();
                }
            }
        }

        Object[] array = wordList.toArray();

        for (Object value : array) {
            //获取索引以及含有文字元素的对应关系
            if (!"\n".equals(value.toString())) {
                wordListAfter02.add((value.toString()));
            }
        }

        return wordListAfter02;
    }
}
