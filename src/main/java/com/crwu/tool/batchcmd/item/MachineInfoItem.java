package com.crwu.tool.batchcmd.item;

import com.yt.tool.swt.base.YtComposite;
import com.yt.tool.swt.ui.text.YtText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 18:22
 */
public class MachineInfoItem extends YtComposite {

    private YtText ipText ;
    private YtText userNameText;
    private YtText passwordText;

    private Button addNextBtn;
    private ClickCallBack callBack;



    public MachineInfoItem(Composite parent, int style , ClickCallBack callBack) {
        super(parent, style);
        this.callBack = callBack;
        setGd(true,false);
        init();

    }

    private void init(){
        createLabel("ip:");
        ipText = new YtText(this, SWT.BORDER);
        ipText.setGdFill(true,false);

        createLabel("用户名");
        userNameText = new YtText(this, SWT.BORDER);
        userNameText.setGdFill(true,false);

        createLabel("密码");
        passwordText = new YtText(this, SWT.BORDER);
        passwordText.setGdFill(true,false);

        addNextBtn = new Button(this,SWT.PUSH);
        addNextBtn.setText("新增");
        addNextBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                callBack.callBack(e);
            }
        });

        setGridLayoutByChildren(false);

    }




}
