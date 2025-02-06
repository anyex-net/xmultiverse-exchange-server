//package com.anyex.apps.account.model;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * CertificationParams
// * <p>File：CertificationParams.java</p>
// * <p>Title: CertificationParams</p>
// * <p>Description: CertificationParams</p>
// * <p>Copyright: Copyright (c) 2019/10/23</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class CertificationParams implements Serializable
//{
//    private Long       id;
//
//    /**
//     * 姓名
//     */
//    @NotNull(message = "姓名不可为空")
//    @ApiModelProperty(value = "姓名", required = true)
//    private String     realName;
//
//    /**
//     * 证件号码
//     */
//    @NotNull(message = "证件号码不可为空")
//    @ApiModelProperty(value = "证件号码", required = true)
//    private String     idcard;
//
//    /**
//     * 文件上传成功后的地址
//     */
//    @ApiModelProperty(value = "附件信息")
//    private Attachment attachment;
//
//    // 正面
//    @NotNull(message = "正面图片不可为空")
//    @ApiModelProperty(value = "正面")
//    private String            frontage;
//
//    // 反面
//    @NotNull(message = "反而图片不可为空")
//    @ApiModelProperty(value = "反面")
//    private String            opposite;
//
//    /**
//     * 附件对象
//     */
//    @Data
//    @ApiModel(description = "附件对象")
//    public static class Attachment implements Serializable
//    {
//        private static final long serialVersionUID = 1L;
//
//        // 封面
//        @ApiModelProperty(value = "封面")
//        private String            cover;
//
//        // 正面
//        @ApiModelProperty(value = "正面")
//        private String            frontage;
//
//        // 反面
//        @ApiModelProperty(value = "反面")
//        private String            opposite;
//    }
//}
