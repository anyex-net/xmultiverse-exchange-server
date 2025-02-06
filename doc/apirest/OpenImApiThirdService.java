package com.anyex.openim;

import com.anyex.openim.api.OpenImApiThirdRest;
import com.anyex.openim.api.third.req.*;
import com.anyex.openim.api.third.resp.*;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.base.OpenImToken;
import com.anyex.openim.utils.ValidatorUtils;


public class OpenImApiThirdService {

    private OpenImApiThirdRest openImApiThirdRest = new OpenImApiThirdRest();

    /**
     * fcmUpdateToken
     * routePath=/third/fcm_update_token
     *
     * @param req
     * @return
     */
    public OpenImResult<String> fcmUpdateToken(OpenImToken openImToken, FcmUpdateTokenReq req) {
        ValidatorUtils.validate(req);
        return openImApiThirdRest.fcmUpdateToken(openImToken, req);
    }

    /**
     * setAppBadge
     * routePath=/third/set_app_badge
     *
     * @param req
     * @return
     */
    public OpenImResult<String> setAppBadge(OpenImToken openImToken, SetAppBadgeReq req) {
        ValidatorUtils.validate(req);
        return openImApiThirdRest.setAppBadge(openImToken, req);
    }

    /**
     * routePath=/third/object/part_limit
     *
     * @param req
     * @return
     */
    public OpenImResult<PartLimitResp> partLimit(OpenImToken openImToken, PartLimitReq req) {
        ValidatorUtils.validate(req);
        return openImApiThirdRest.partLimit(openImToken, req);
    }

    /**
     * routePath=/third/object/part_size
     *
     * @param req
     * @return
     */
    public OpenImResult<PartSizeResp> partSize(OpenImToken openImToken, PartSizeReq req) {
        ValidatorUtils.validate(req);
        return openImApiThirdRest.partSize(openImToken, req);
    }

    /**
     * initiateMultipartUpload
     * routePath=/third/object/initiate_multipart_upload
     *
     * @param req
     * @return
     */
    public OpenImResult<InitiateMultipartUploadResp> initiateMultipartUpload(OpenImToken openImToken, InitiateMultipartUploadReq req) {
        ValidatorUtils.validate(req);
        return openImApiThirdRest.initiateMultipartUpload(openImToken, req);
    }

    /**
     * completeMultipartUpload
     * routePath=/third/object/complete_multipart_upload
     *
     * @param req
     * @return
     */
    public OpenImResult<CompleteMultipartUploadResp> completeMultipartUpload(OpenImToken openImToken, CompleteMultipartUploadReq req) {
        ValidatorUtils.validate(req);
        return openImApiThirdRest.completeMultipartUpload(openImToken, req);
    }

    /**
     * initiateFormData
     * routePath=/third/object/initiate_form_data
     *
     * @param req
     * @return
     */
    public OpenImResult<InitiateFormDataResp> initiateFormData(OpenImToken openImToken, InitiateFormDataReq req){
        ValidatorUtils.validate(req);
        return openImApiThirdRest.initiateFormData(openImToken, req);
    }

    /**
     * completeFormData
     * routePath=/third/object/complete_form_data
     *
     * @param req
     * @return
     */
    public OpenImResult<CompleteFormDataResp> completeFormData(OpenImToken openImToken, CompleteFormDataReq req){
        ValidatorUtils.validate(req);
        return openImApiThirdRest.completeFormData(openImToken, req);
    }

    /**
     * accessURL
     * routePath=/third/object/access_url
     *
     * @param req
     * @return
     */
    public OpenImResult<AccessURLResp> accessURL(OpenImToken openImToken, AccessURLReq req) {
        ValidatorUtils.validate(req);
        return openImApiThirdRest.accessURL(openImToken, req);
    }
}
