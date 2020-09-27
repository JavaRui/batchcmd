package com.crwu.tool.batchcmd.cmd;

import com.bluemoon.pf.tools.extra.ssh.SshUtil;
import com.bluemoon.pf.tools.extra.ssh.vo.CmdBaseVo;
import com.bluemoon.pf.tools.extra.ssh.vo.CmdSimpleProcess;
import com.crwu.tool.batchcmd.infoPart.IInfoContentPart;
import com.crwu.tool.batchcmd.infoPart.MachineInfoBean;
import com.crwu.tool.batchcmd.result.IResultPart;
import com.yt.tool.swt.base.YtComposite;
import com.yt.tool.swt.mgr.MsgDlgMgr;
import com.yt.tool.swt.ui.text.YtText;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/24 21:14
 */
@Slf4j
public class CmdContentPart extends YtComposite {

    private YtText cmdText;

    private IInfoContentPart infoContentPart ;
    private IResultPart resultPart;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,100,
                                      60L,TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());



    public CmdContentPart(Composite parent, int style , IInfoContentPart infoContentPart , IResultPart resultPart) {
        super(parent, style);
        this.infoContentPart = infoContentPart;
        this.resultPart = resultPart;
        init();
    }

    private void init(){
        cmdText = new YtText(this);
        cmdText.setGdFill(true,true);
        cmdText.setText("cd /data\n" +
                "ls \n" +
                "pwd\n");

        Button btn = new Button(this, SWT.PUSH);
        btn.setText("运行");
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<MachineInfoBean> analysis = infoContentPart.analysis();

                if(analysis.isEmpty()){
                    resultPart.addLog("ip列表为空，请检查");
                    return ;
                }

                int i = checkAndDel(analysis);
                if(i == SWT.CANCEL){
                    return ;
                }
                resultPart.start();
                resultPart.addLog("开始执行==================");
                clearDirty();
                CmdSimpleProcess[] cmds = getCmds();
                analysis.forEach(bean->{
                    threadPoolExecutor.execute(()->{
                        runCmd(bean , cmds);
                    });

                });


            }
        });

        setGridLayoutByChildren(false);

    }

    private void runCmd(MachineInfoBean bean , CmdSimpleProcess[] cmds){
        try {
            List<CmdBaseVo> list = SshUtil.execShellCmdBySSH(bean, cmds);
            resultPart.setResult(bean , list,null);
        } catch (Exception e1) {
//                        e1.printStackTrace();
            resultPart.addLog("有异常   "+e1.getLocalizedMessage()+"    \n bean"+bean+"   ");


        }
    }

    private int checkAndDel(List<MachineInfoBean> analysis){

        AtomicInteger atomicInteger = new AtomicInteger(0);
        int size = analysis.size();

        Iterator<MachineInfoBean> iterator = analysis.iterator();
        while(iterator.hasNext()){
            MachineInfoBean next = iterator.next();
            if(!next.isNormal()){
                iterator.remove();
                continue;
            }
            atomicInteger.incrementAndGet();
        }


        if(atomicInteger.get() == size){
            return SWT.OK;
        }

        int i = MsgDlgMgr.showMessbox("总数量为："+size+"  ，而正常的数量为："+atomicInteger.get()+" ,点击确定继续");
        return i;


    }

    private void clearDirty(){
        String text = cmdText.getText();
        cmdText.setText("");
        String[] split = text.split("\n");
        for (String s : split) {
            if(s.trim().length() == 0){
                continue;
            }
            cmdText.append(s+"\n");
        }
    }

    private CmdSimpleProcess[] getCmds(){
        String text = cmdText.getText();
        String[] split = text.split("\n");
        CmdSimpleProcess[] cmdSimpleProcesses = new CmdSimpleProcess[split.length];
        for (int i = 0; i < split.length; i++) {
            CmdSimpleProcess cmdSimpleProcess = new CmdSimpleProcess(split[i]);
            cmdSimpleProcesses[i] = cmdSimpleProcess;
        }
        return cmdSimpleProcesses;



    }





}
