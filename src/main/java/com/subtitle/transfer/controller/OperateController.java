package com.subtitle.transfer.controller;

import com.subtitle.transfer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/start")
public class OperateController {
    @Autowired
    GetFileToCharArray getFileToCharArray;
    @Autowired
    PickWordAndNumFromCharArray pick;
    @Autowired
    GetTime getTime;
    @Autowired
    TimeWordsCombine timeWordsCombine;
    @Autowired
    OutputFile outputFile;
    /*
        GetFileToCharArray getFileToCharArray = new GetFileToCharArray("D:\\Download\\GitHub WalkThrough - 英语 (自动生成).txt");
        PickWordAndNumFromCharArray pick= new PickWordAndNumFromCharArray(getFileToCharArray.fileToStream());
        GetTime getTime = new GetTime(pick.getNumberString());
        TimeWordsCombine timeWordsCombine = new TimeWordsCombine(getTime, pick);
        OutputFile outputFile = new OutputFile("D:\\Download\\Introduction to GitHub .bcc",timeWordsCombine);
        outputFile.output();

    }*/
    @RequestMapping("/chinese")
    public String chineseTransfer(String finalFilePath,String resourceFilePath){
        char[] chars = getFileToCharArray.fileToStream(resourceFilePath);
        outputFile.output(finalFilePath,timeWordsCombine.outputDocString(pick.getChineseList(chars),getTime.changeTimeToMM(pick.getNumberString(chars))));
        return null;
    }

    @RequestMapping("/english")

    public String englishTransfer(String finalFilePath,String resourceFilePath){
        char[] chars = getFileToCharArray.fileToStream(resourceFilePath);
        outputFile.output(finalFilePath,timeWordsCombine.outputDocString(pick.getEnglishList(chars),getTime.changeTimeToMM(pick.getNumberString(chars))));
        return null;
    }
}
