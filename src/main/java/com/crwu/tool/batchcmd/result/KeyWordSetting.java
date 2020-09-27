package com.crwu.tool.batchcmd.result;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import com.alibaba.fastjson.JSONObject;
import com.crwu.swt.common.base.YtComposite;
import com.crwu.tool.batchcmd.config.BatchCmdConfig;
import com.yt.tool.swt.ui.text.YtText;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/25 15:06
 */
@Slf4j
public class KeyWordSetting extends YtComposite implements IKeyWordSetting{

    private YtText errorKeyText;
    private YtText normalKeyText;

    public KeyWordSetting(Composite parent, int style) {
        super(parent, style);
        init();
    }

    @Override
    protected void afterInit() {

        boolean exist = FileUtil.exist(BatchCmdConfig.RESULT_KEY);
        if(!exist){
            log.info("文件不存在：   "+BatchCmdConfig.RESULT_KEY);
            return ;
        }

        FileReader fileReader2 = new FileReader(BatchCmdConfig.RESULT_KEY);
        String s2 = fileReader2.readString();
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(s2);

        errorKeyText.setText(jsonObject.getString("error"));
        normalKeyText.setText(jsonObject.getString("normal"));
    }

    private void init(){

        createLabel("包含这些字段判断为失败：用\",\"隔开");
        errorKeyText = new YtText(this, SWT.BORDER);
        errorKeyText.setGdFill(true,false);

        setGridLayout(2,false);

        createLabel("包含这些字段判断为成功：用\",\"隔开");
        normalKeyText = new YtText(this, SWT.BORDER);
        normalKeyText.setGdFill(true,false);


        Button saveBtn = new Button(this,SWT.PUSH);
        saveBtn.setText("保存设置");
        saveBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String text = errorKeyText.getText();
                String text1 = normalKeyText.getText();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("error",text);
                jsonObject.put("normal",text1);

                FileUtil.mkParentDirs(BatchCmdConfig.RESULT_KEY);

                FileWriter fileWriter = new FileWriter(BatchCmdConfig.RESULT_KEY);
                fileWriter.write(jsonObject.toString());


            }
        });


    }


    @Override
    public String getNormalKey() {
        return normalKeyText.getText();
    }

    @Override
    public String getErrorKey() {
        return errorKeyText.getText();
    }
}
