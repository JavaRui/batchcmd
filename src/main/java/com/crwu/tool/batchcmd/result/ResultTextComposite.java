package com.crwu.tool.batchcmd.result;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import com.bluemoon.pf.tools.extra.ssh.vo.CmdBaseVo;
import com.crwu.tool.batchcmd.Util;
import com.crwu.tool.batchcmd.infoPart.MachineInfoBean;
import com.yt.tool.swt.base.YtComposite;
import com.yt.tool.swt.util.LayoutUtil;
import com.yt.tool.swt.util.YtColorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.util.List;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/24 20:23
 */
public class ResultTextComposite extends YtComposite implements IResultPart{

    private StyledText styledText;


    public ResultTextComposite(Composite parent) {
        super(parent);
        init();
    }

    private void init(){

        Button btn = new Button(this,SWT.PUSH);
        btn.setText("清除日志");
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                styledText.setText("");
            }
        });

        Button copyBtn = new Button(this,SWT.PUSH);
        copyBtn.setText("复制报文");
        copyBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ClipboardUtil.setStr(styledText.getText());
            }
        });

        setGridLayoutByChildren(false);

        styledText = new StyledText(this, SWT.BORDER|SWT.VERTICAL|SWT.WRAP|SWT.MULTI);

        styledText.setLayoutData(LayoutUtil.createFillGrid());


        Util.addSelectAllListener(styledText);

    }


    public final String CMD_SPLIT_STR = "-----------------------------";

    @Override
    public void setResult(MachineInfoBean machineInfoBean, List<CmdBaseVo> resultList, IKeyWordSetting keyWord) {
        String sss = CMD_SPLIT_STR+machineInfoBean.getIp()+":"+machineInfoBean.getUser();
        String format = DatePattern.NORM_TIME_FORMAT.format(System.currentTimeMillis());

        String normalKey = keyWord.getNormalKey();
        int start = styledText.getText().length();
        styledText.append(sss+"\n");

        StyleRange range = new StyleRange();
        range.start = start;
        range.length = sss.length();
        range.background = YtColorUtil.regColor;
        styledText.setStyleRange(range);

        for (int i = 0; i < resultList.size(); i++) {
            CmdBaseVo cmdBaseVo = resultList.get(i);
            styledText.append(format+"  --  "+cmdBaseVo.getCmdResult()+"\n");
            String currentText = styledText.getText();

            String[] split = normalKey.split(",");
            for (String s : split) {
                boolean contains = currentText.contains(s);
                if(contains){
                    Util.addRangeAllAppear(styledText,normalKey);

                }
            }


            styledText.append("====================================\n");
        }

        if(keyWord == null){
            return ;
        }
    }

    @Override
    public void addLog(String log) {
        String format = DatePattern.NORM_TIME_FORMAT.format(System.currentTimeMillis());
        styledText.append(format+"  --  "+log+"\n");
    }
}
