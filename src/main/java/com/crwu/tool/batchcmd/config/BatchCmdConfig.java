package com.crwu.tool.batchcmd.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;

import java.io.File;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/25 10:08
 */
@Slf4j
public class BatchCmdConfig {

    public static final String USER_DIR = System.getProperty("user.dir")+ File.separatorChar;

    public static final String INFO_PATH = USER_DIR+"user/info.txt";
    public static final String INFO_SP_PATH = USER_DIR+"user/infoSp.txt";

    public static final String RESULT_KEY = USER_DIR+"user/key.txt";


    public static void readTxt(Text spText , String path){

        try{
            FileReader fileReader2 = new FileReader(path);
            String s2 = fileReader2.readString();
            spText.setText(s2);

        }catch (Exception e){
            log.info("文件不存在:   "+e.getLocalizedMessage());
        }
    }

    public static void readTxt(StyledText spText , String path){

        try{
            FileReader fileReader2 = new FileReader(path);
            String s2 = fileReader2.readString();
            spText.setText(s2);

        }catch (Exception e){

            log.info("文件不存在ss:   "+e.getLocalizedMessage());
        }
    }

    public static void writeTxt(StyledText spText , String path){

        try{
            FileUtil.mkParentDirs(path);

            String text = spText.getText();
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(text);

        }catch (Exception e){

            log.info("文件不存在ss:   "+e.getLocalizedMessage());
        }
    }

    public static void writeTxt(Text text , String path){

        try{
            FileUtil.mkParentDirs(path);

            String ss = text.getText();
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(ss);

        }catch (Exception e){

            log.info("文件不存在ss:   "+e.getLocalizedMessage());
        }
    }
}
