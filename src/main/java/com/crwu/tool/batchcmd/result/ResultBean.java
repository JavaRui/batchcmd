package com.crwu.tool.batchcmd.result;

import com.crwu.swt.tableviewer.annotation.TableColumnSetting;
import lombok.Data;

/**
 * @author wuchengrui
 * @Description: \\TODO
 * @date 2020/9/23 18:47
 */
@Data
public class ResultBean {

    @TableColumnSetting(index = 0,columnText = "序号")

    private int id;
    @TableColumnSetting(index = 1,columnText = "机器信息",width = 300)

    private String machineInfo;
    @TableColumnSetting(index = 2,columnText = "状态")

    private String state;

    @TableColumnSetting(index = 3,columnText = "结果" ,width = 300)
    private String cmdResult;




}
