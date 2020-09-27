package com.crwu.tool.batchcmd.infoPart;

import com.crwu.tool.batchcmd.item.ClickCallBack;
import com.crwu.tool.batchcmd.item.MachineInfoItem;
import com.yt.tool.swt.base.YtComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 18:34
 */
public class MachineListPart extends YtComposite implements ClickCallBack {


    private List<MachineInfoItem> itemList = new ArrayList<>();

    public MachineListPart(Composite parent, int style) {
        super(parent, style);
        setGd(true,true);
        setGridLayout();
        addItem();
    }

    @Override
    public void callBack(Object object) {
        addItem();
    }

    private void addItem(){
        itemList.add(new MachineInfoItem(this, SWT.BORDER,this));

        //重要，设置这个composite的真实尺
        this.setSize(getShell().getSize().x-100 , this.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        this.layout();

//        ((ItemScrolledComposite)this.getParent()).setContent(this);

        this.getParent().layout();

        System.out.println(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));

    }



}
