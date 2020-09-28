package com.crwu.tool.batchcmd.infoPart;

import com.crwu.tool.batchcmd.Util;
import com.crwu.tool.batchcmd.config.BatchCmdConfig;
import com.yt.tool.swt.base.YtComposite;
import com.yt.tool.swt.ui.text.YtText;
import com.yt.tool.swt.util.LayoutUtil;
import com.yt.tool.swt.util.YtColorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 19:49
 */
public class InfoContentPart extends YtComposite implements IInfoContentPart{

    private YtText spText ;

    private StyledText contentText;

    public InfoContentPart(Composite parent, int style) {
        super(parent, style);
        init();
    }

    @Override
    protected void afterInit() {
        BatchCmdConfig.readTxt(contentText , BatchCmdConfig.INFO_PATH);
        BatchCmdConfig.readTxt(spText , BatchCmdConfig.INFO_SP_PATH);

        analysis();
    }

    private void init(){
        YtComposite spComp = new YtComposite(this, SWT.BORDER);
        spComp.createLabel("使用分隔符，如有多个用,分隔");

        spText = new YtText(spComp,SWT.BORDER);
        spText.setText(":,：,\t,\\|");
        spText.setGdFill(true,false);
        spComp.setGd(true,false);

        Button btn = new Button(spComp,SWT.PUSH);
        btn.setText("解析");
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                analysis();

            }
        });

        Button save = new Button(spComp,SWT.PUSH);
        save.setText("保存");
        save.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
//                analysis();
                BatchCmdConfig.writeTxt(contentText , BatchCmdConfig.INFO_PATH);
                BatchCmdConfig.writeTxt(spText , BatchCmdConfig.INFO_SP_PATH);
            }
        });


        spComp.setGridLayoutByChildren(false);

        contentText = new StyledText(this,2882);
//        contentText.setGdFill(true,true);
        contentText.setLayoutData(LayoutUtil.createFillGrid());
        this.setGridLayout();

        Util.addSelectAllListener(contentText);

    }

    public String getSpText(){
        return spText.getText();
    }

    @Override
    public List<MachineInfoBean> analysis(){
        clearRange();

        List<MachineInfoBean> infoBeanList = new ArrayList<>();

        String text = contentText.getText().trim();
        String[] infos = text.split("\n");

        for (String info : infos) {
            info = info.trim();
            if(info.length() == 0){
                continue;
            }
            MachineInfoBean bean = new MachineInfoBean();

            if(Util.isIp(info)){
                bean.setIp(info);
                bean.setUser("appadm");
                bean.setPwd("bluemoon2016#");
                bean.setNormal(true);
                bean.setPort(22);
                infoBeanList.add(bean);
                Util.addRange(contentText , info);
                continue;
            }

            String[] split = spText.getText().trim().split(",");

            for (String s : split) {
                String[] split1 = info.split(s);
                if(split1.length == 3){

                    bean.setIp(split1[0]);

                    bean.setUser(split1[1]);
                    bean.setPwd(split1[2]);
                    bean.setPort(22);
                    if(!Util.isIp(split1[0])){
                        continue;
                    }

                    bean.setNormal(true);

                    //上色
                    Util.addRange(contentText , info);

                    break;
                }
            }
            infoBeanList.add(bean);

        }
        return infoBeanList;
    }

    private void clearRange(){
        StyleRange range = new StyleRange();
        range.start=0;
        range.length = contentText.getText().length();
        range.background = YtColorUtil.whiteColor;
        contentText.setStyleRange(range);
    }


}
