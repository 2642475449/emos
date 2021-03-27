package com.example.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author ：降蓝
 * @description：后端验证功能 接收客户端提交的数据
 * @date ：2021/3/27 16:59
 */
@Data
@ApiModel
public class TestSayHelloForm {
    @NotBlank
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{2,25}$")
    @ApiModelProperty("姓名")
    private String name;

}
