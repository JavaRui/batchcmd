package com.crwu.tool.batchcmd.result;

import com.bluemoon.pf.tools.extra.ssh.vo.CmdBaseVo;
import com.crwu.tool.batchcmd.infoPart.MachineInfoBean;

import java.util.List;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/25 09:20
 */
public interface IResultPart {

    void setResult(MachineInfoBean machineInfoBean ,  List<CmdBaseVo> resultList, IKeyWordSetting keyWord);

    void addLog(String log);

    default void start() {}

}
