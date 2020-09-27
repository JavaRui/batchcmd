package com.crwu.tool.batchcmd.item;

import com.crwu.tool.batchcmd.infoPart.MachineListPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 19:10
 */
public class ItemScrolledComposite extends ScrolledComposite {
    public ItemScrolledComposite(Composite parent, int style) {
        super(parent, style);

        setLayout(new FillLayout());

        init();

    }

    private void init(){
        MachineListPart part = new MachineListPart(this, SWT.BORDER);
        setContent(part);

    }

}
