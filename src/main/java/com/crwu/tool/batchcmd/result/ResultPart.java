package com.crwu.tool.batchcmd.result;

import com.bluemoon.pf.tools.extra.ssh.vo.CmdBaseVo;
import com.crwu.tool.batchcmd.infoPart.MachineInfoBean;
import com.yt.tool.swt.base.SwtVoid;
import com.yt.tool.swt.base.YtComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import java.util.List;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 19:36
 */
public class ResultPart extends YtComposite implements IResultPart , IKeyWordSetting{
    private ResultTextComposite resultTextComposite;
    private IKeyWordSetting keyWordSetting;
    private TableComposite tableComposite;


    public ResultPart(Composite parent, int style) {
        super(parent, style);

        init();

    }

    private void init(){
        setFillLayout();
        // 新建选项卡容�?
        CTabFolder tabFolder = new CTabFolder(this, SWT.BORDER | SWT.Close |SWT.Move);
        // 设置布局
        tabFolder.setLayout(new FillLayout());
        // 设置�?大化按钮可见，默认不可见
//        tabFolder.setMaximizeVisible(true);

        // 设置�?小化按钮不可�?,默认不可�?
        // tabFolder.setMinimizeVisible(false);
        // 设置是否为简单样式，�?单样式，item是方形的
//                tabFolder.setSimple(false);
        // 设置item是否可关�?
        tabFolder.setUnselectedCloseVisible(true);
        // 创建�?个item
        CTabItem item1 = new CTabItem(tabFolder, SWT.MULTI | SWT.V_SCROLL| SWT.Close |SWT.Move);
//        item1.setShowClose(true);
        item1.setText("结果");

         resultTextComposite = new ResultTextComposite(tabFolder);

        // 控制这个btn
        item1.setControl(resultTextComposite);



        // 创建�?个item
        CTabItem item2 = new CTabItem(tabFolder, SWT.MULTI | SWT.V_SCROLL|SWT.Move);
        item2.setText("表格");

//        goodsBeanTable.setSelectBack(new INBack() {
//
//            @Override
//            public void callBack(Object o) {
//                lll("选择了：    "+o.toString());
//            }
//        });
        tableComposite = new TableComposite(tabFolder,0);
        // 控制这个btn
        item2.setControl(tableComposite);

        CTabItem item3 = new CTabItem(tabFolder, SWT.MULTI | SWT.V_SCROLL|SWT.Move);
        item3.setText("配置");
        keyWordSetting = new KeyWordSetting(tabFolder,0);
        // 控制这个btn
        item3.setControl((Composite)keyWordSetting);



        // 设置默认选择
        tabFolder.setSelection(0);
    }

    @Override
    public void setResult(MachineInfoBean machineInfoBean , List<CmdBaseVo> resultList , IKeyWordSetting keyWord) {
        SwtVoid.delayAsy(0, ()->{

            resultTextComposite.setResult(machineInfoBean , resultList,keyWordSetting);
            tableComposite.setResult(machineInfoBean , resultList,keyWordSetting);



        });
    }

    @Override
    public void addLog(String log) {
        SwtVoid.delayAsy(0, ()->{
            resultTextComposite.addLog(log);

        });

    }

    @Override
    public String getNormalKey() {
        return keyWordSetting.getNormalKey();
    }

    @Override
    public String getErrorKey() {
        return keyWordSetting.getErrorKey();
    }

    @Override
    public void start() {
        tableComposite.start();
    }
}
