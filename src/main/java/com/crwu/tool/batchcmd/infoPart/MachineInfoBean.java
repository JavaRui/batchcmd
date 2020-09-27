package com.crwu.tool.batchcmd.infoPart;

import com.bluemoon.pf.tools.extra.ssh.SshAuth;
import lombok.Data;
import lombok.ToString;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 20:14
 */
@Data
@ToString(callSuper = true)
public class MachineInfoBean extends SshAuth {

    private boolean normal = false;

}
