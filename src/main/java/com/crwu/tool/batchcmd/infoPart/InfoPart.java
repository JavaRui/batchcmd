package com.crwu.tool.batchcmd.infoPart;

import com.yt.tool.swt.base.YtComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 19:36
 */
public class InfoPart extends YtComposite {

    public InfoPart(Composite parent, int style) {
        super(parent, style);

        init();

    }

    private void init(){
        // 新建选项卡容�?
        CTabFolder tabFolder = new CTabFolder(this, SWT.BORDER | SWT.Close |SWT.Move);
        // 设置布局
        tabFolder.setLayout(new FillLayout());
        // 设置�?大化按钮可见，默认不可见
        tabFolder.setMaximizeVisible(true);

        // 设置�?小化按钮不可�?,默认不可�?
        // tabFolder.setMinimizeVisible(false);
        // 设置是否为简单样式，�?单样式，item是方形的
//                tabFolder.setSimple(false);
        // 设置item是否可关�?
        tabFolder.setUnselectedCloseVisible(true);
        // 创建�?个item
        CTabItem item1 = new CTabItem(tabFolder, SWT.MULTI | SWT.V_SCROLL| SWT.Close |SWT.Move);
        item1.setShowClose(true);
        item1.setText("单一机器输入");
        Button btn1 = new Button(tabFolder, SWT.PUSH);
        btn1.setText("aaaa");
        // 控制这个btn
        item1.setControl(btn1);



        // 创建�?个item
        CTabItem item2 = new CTabItem(tabFolder, SWT.MULTI | SWT.V_SCROLL|SWT.Move);
        item2.setText("item2");
        Button btn2 = new Button(tabFolder, SWT.PUSH);
        btn2.setText("2222");
        // 控制这个btn
        item2.setControl(btn2);



        // 设置默认选择
        tabFolder.setSelection(0);
    }

}
