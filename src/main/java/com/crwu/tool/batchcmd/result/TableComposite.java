package com.crwu.tool.batchcmd.result;

import cn.hutool.core.swing.clipboard.ClipboardUtil;
import com.bluemoon.pf.tools.extra.ssh.vo.CmdBaseVo;
import com.crwu.swt.tableviewer.product.YtCheckBoxTable;
import com.crwu.tool.batchcmd.infoPart.MachineInfoBean;
import com.yt.tool.swt.base.YtComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/27 11:41
 */
public class TableComposite extends YtComposite implements IResultPart{

    private Button getCheckBtn;

    private YtCheckBoxTable goodsBeanTable;

    public TableComposite(Composite parent, int style) {
        super(parent, style);
        init();
    }

    private void init(){
        getCheckBtn = new Button(this, SWT.PUSH);
        getCheckBtn.setText("获取勾选的数据");

        getCheckBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<ResultBean> check = (List<ResultBean>) goodsBeanTable.getCheck();
                StringBuilder sb = new StringBuilder();
                check.forEach(resultBean -> {
                    sb.append(resultBean.getId()+"|"+resultBean.getMachineInfo()+"|"+resultBean.getState()+"|"+resultBean.getCmdResult()+"\n");
                });
                ClipboardUtil.setStr(sb.toString());
            }
        });


        setGridLayoutByChildren(false);


        goodsBeanTable = YtCheckBoxTable.newCheckBox(this, ResultBean.class);
        goodsBeanTable.setEoList(new ArrayList<ResultBean>());
        goodsBeanTable.setGd(true,true);

    }


    @Override
    public void setResult(MachineInfoBean machineInfoBean, List<CmdBaseVo> resultList, IKeyWordSetting keyWord) {
        ResultBean resultBean = new ResultBean();
        resultBean.setMachineInfo(machineInfoBean.getIp()+":"+machineInfoBean.getUser()+":"+machineInfoBean.getPwd());

        StringBuilder cmdResult = new StringBuilder();
        try{
            resultList.forEach(cmdBaseVo -> {
                cmdResult.append(cmdBaseVo.getCmdResult());
            });

        }catch (Exception e){
            e.printStackTrace();
        }

        resultBean.setCmdResult(cmdResult.toString());
        resultBean.setState("fail");

        String[] split = keyWord.getNormalKey().split(",");
        for (String s : split) {
            if(s.length() == 0){
                continue;
            }
            boolean contains = resultBean.getCmdResult().contains(s);
            if(contains){
                resultBean.setState("sc");
            }
        }
        if(split.length == 0){
            resultBean.setState("unknown");
        }
        if(split.length == 1 && split[0].length() == 0){
            resultBean.setState("unknown");
        }

//            goodsBeanTable.setEoList(beanList);
        int size = goodsBeanTable.getEoList().size();
        resultBean.setId(size+1);
        goodsBeanTable.add(resultBean);
    }

    @Override
    public void addLog(String log) {

    }

    @Override
    public void start() {
        goodsBeanTable.clear();
    }
}
