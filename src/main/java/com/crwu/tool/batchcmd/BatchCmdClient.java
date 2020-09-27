package com.crwu.tool.batchcmd;

import com.crwu.tool.batchcmd.cmd.CmdContentPart;
import com.crwu.tool.batchcmd.infoPart.InfoContentPart;
import com.crwu.tool.batchcmd.result.ResultPart;
import com.yt.tool.swt.base.SwtVoid;
import com.yt.tool.swt.base.YtComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 09:06
 */
public class BatchCmdClient extends YtComposite {

    public static void main(String[] args) {

        SwtVoid.createSwt((shell)->{
            shell.setSize(600,700);
            shell.setText("批量执行命令");
            new BatchCmdClient(shell,0);
        });
    }

    public BatchCmdClient(Composite parent, int style) {
        super(parent, style);
        setFillLayout();


        SashForm sh = new SashForm(this,SWT.VERTICAL);

//        sh.setLayout(new FillLayout(SWT.HORIZONTAL));

//        ItemScrolledComposite machineListPart = new ItemScrolledComposite(sh,SWT.BORDER|SWT.V_SCROLL | SWT.H_SCROLL);

        InfoContentPart infoContentPart = new InfoContentPart(sh,SWT.BORDER);



        ResultPart resultPart = new ResultPart(sh,0);
        CmdContentPart cmdContentPart = new CmdContentPart(sh,SWT.BORDER,infoContentPart,resultPart);
        sh.setWeights(new int[]{30,40,30});


    }
}
