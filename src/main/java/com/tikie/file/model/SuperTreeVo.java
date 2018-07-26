package com.tikie.file.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangshitai
 * @date 2018-07-25
 */
@Data
public class SuperTreeVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "是否为文件")
    private Boolean isFile;

    @ApiModelProperty(value = "全文件名文件")
    private String name;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "修改时间")
    private String utime;
}
