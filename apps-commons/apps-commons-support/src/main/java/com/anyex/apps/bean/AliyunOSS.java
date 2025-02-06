package com.anyex.apps.bean;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.google.common.collect.Maps;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * 阿里云OSS存储服务
 * <p>File：AliyunOSSService.java</p>
 * <p>Title: AliyunOSSService</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class AliyunOSS
{
    @Autowired
    private GlobalProperies properies;
    
    /**
     * 获取阿里云OSS上传策略
     * <p>
     * 临时空间地址：http://apps-temp.oss-cn-hongkong.aliyuncs.com
     * </p>
     *
     * @param dir  上传路径
     * @param host 服务器上传地址
     * @return {@link String}
     */
    public Map getPostPolicy(String dir, String host)
    {
        Map policy = null;
        Aliyun aliyun = properies.getAliyun();
        OSSClient ossClient = new OSSClient(aliyun.getEndPoint(), aliyun.getAccessKey(), aliyun.getSecretKey());
        try
        {
            long expireEndTime = System.currentTimeMillis() + 60 * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 5368709120L);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
            Map<String, String> respMap = Maps.newLinkedHashMap();
            respMap.put("dir", dir);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("accessid", aliyun.getAccessKey());
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            policy = respMap;
        }
        catch (Exception e)
        {
            log.error("获取策略失败");
            //log.error("获取策略失败：{} ", e.getLocalizedMessage());
        }
        finally
        {
            ossClient.shutdown();
        }
        return policy;
    }
    
    /**
     * 断点续传本地文件
     * <p>
     * 默认采用客户端直传的方式，断点续传只是做一个JAVA端的示例。
     * 不允许在WEB环境中直接使用
     * </p>
     *
     * @param path     路径
     * @param fileName 文件名
     */
    public void upload(String path, String fileName)
    {
        Aliyun aliyun = properies.getAliyun();
        com.anyex.apps.bean.Bucket bucket = aliyun.getBucket();
        OSSClient ossClient = new OSSClient(aliyun.getEndPoint(), aliyun.getAccessKey(), aliyun.getSecretKey());
        try
        {
            // 设置断点续传请求
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucket.getTempName(), fileName);
            // 指定上传的本地文件
            uploadFileRequest.setUploadFile(path);
            // 指定上传并发线程数
            uploadFileRequest.setTaskNum(5);
            // 指定上传的分片大小
            uploadFileRequest.setPartSize(1 * 1024 * 1024);
            // 开启断点续传
            uploadFileRequest.setEnableCheckpoint(true);
            // 断点续传上传
            ossClient.uploadFile(uploadFileRequest);
        }
        catch (Throwable e)
        {
            //log.error("文件上传失败：{} ", e.getLocalizedMessage());
            log.error("文件上传失败 ");
        }
        finally
        {
            ossClient.shutdown();
        }
    }
    
    /**
     * 转移临时空间中的文件到正式空间
     * <p>
     * 将真实有性的文件转移到正式空间，转移完毕之后自动清理临时文件。
     * 另：临时空间可做定期清理以减少资料开销
     * </p>
     *
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    @Async
    public void transferObject(String fileName)
    {
        Aliyun aliyun = properies.getAliyun();
        com.anyex.apps.bean.Bucket bucket = aliyun.getBucket();
        OSSClient ossClient = new OSSClient(aliyun.getEndPoint(), aliyun.getAccessKey(), aliyun.getSecretKey());
        try
        {
            ossClient.copyObject(bucket.getTempName(), fileName, bucket.getReleaseName(), fileName);
            ossClient.deleteObject(bucket.getTempName(), fileName);
        }
        catch (RuntimeException e)
        {
            //log.error("转移文件失败：{} ", e.getLocalizedMessage());
            log.error("转移文件失败");
        }
        finally
        {
            ossClient.shutdown();
        }
    }
    
    /**
     * 转移临时空间中的文件到正式空间
     * <p>
     * 将真实有性的文件转移到正式空间，转移完毕之后自动清理临时文件。
     * 另：临时空间可做定期清理以减少资料开销
     * </p>
     *
     * @param fileNames
     */
    @Async
    public void transferObjects(String ... fileNames)
    {
        Aliyun aliyun = properies.getAliyun();
        com.anyex.apps.bean.Bucket bucket = aliyun.getBucket();
        OSSClient ossClient = new OSSClient(aliyun.getEndPoint(), aliyun.getAccessKey(), aliyun.getSecretKey());
        try
        {
            for (String fileName : fileNames)
            {
                if (ossClient.doesObjectExist(bucket.getTempName(), fileName))
                {
                    ossClient.copyObject(bucket.getTempName(), fileName, bucket.getReleaseName(), fileName);
                    ossClient.deleteObject(bucket.getTempName(), fileName);
                }
            }
        }
        catch (RuntimeException e)
        {
            //log.error("转移文件失败：{} ", e.getLocalizedMessage());
            log.error("转移文件失败");
        }
        finally
        {
            ossClient.shutdown();
        }
    }
    
    /**
     * 删除临时空间中的文件
     *
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    @Async
    public void deleteObject(String fileName)
    {
        Aliyun aliyun = properies.getAliyun();
        OSSClient ossClient = new OSSClient(aliyun.getEndPoint(), aliyun.getAccessKey(), aliyun.getSecretKey());
        try
        {
            Bucket bucket = aliyun.getBucket();
            ossClient.deleteObject(bucket.getTempName(), fileName);
        }
        catch (RuntimeException e)
        {
            //log.error("删除临时文件失败：{} ", e.getLocalizedMessage());
            log.error("删除临时文件失败");
        }
        finally
        {
            ossClient.shutdown();
        }
    }
    
    /**
     * 上传图片至OSS
     *
     * @return key
     * @author:yukai
     * @since 04.25.2018
     */
    public String[] uploadObject2OSS(MultipartFile[] multipartFile)
    {
        Aliyun aliyun = properies.getAliyun();
        OSSClient ossClient = new OSSClient(aliyun.getEndPoint(), aliyun.getAccessKey(), aliyun.getSecretKey());
        String[] keys = new String[2];
        for (int i = 0; i < multipartFile.length; i++)
        {
            String resultStr = null;
            try
            {
                // 以输入流的形式上传文件
                InputStream is = multipartFile[i].getInputStream();
                // 创建上传Object的Metadata
                ObjectMetadata metadata = new ObjectMetadata();
                // 上传的文件的长度
                metadata.setContentLength(is.available());
                // 指定该Object被下载时的网页的缓存行为
                metadata.setCacheControl("no-cache");
                // 指定该Object下设置Header
                metadata.setHeader("Pragma", "no-cache");
                // 指定该Object被下载时的内容编码格式
                metadata.setContentEncoding("utf-8");
                // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
                // 如果没有扩展名则填默认值application/octet-stream
                metadata.setContentType(multipartFile[i].getContentType());
                // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
                metadata.setContentDisposition("filename/filesize=" + multipartFile[i].getName() + "/" + multipartFile[i].getSize() + "Byte.");
                // 上传文件 (上传文件流的形式)
                String key = getRandomKey(10) + getFileType(multipartFile[i].getOriginalFilename());
                PutObjectResult putResult = ossClient.putObject(aliyun.getBucket().getTempName(), key, is, metadata);
                // 解析结果
                resultStr = key;
                keys[i] = resultStr;
                // resultStr = putResult.getETag();
            }
            catch (Exception e)
            {
                log.error("上传阿里云OSS服务器异常");
                //log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
                e.printStackTrace();
            }
        }
        return keys;
    }
    
    /**
     * 获取随机key
     *
     * @param length
     * @return
     */
    public String getRandomKey(int length)
    {
        length = length == 0 ? 32 : length;
        String chars = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
        int maxPos = chars.length();
        String pwd = "";
        for (int i = 0; i < length; i++)
        {
            pwd += chars.charAt((int) Math.floor(Math.random() * maxPos));
        }
        return pwd;
    }
    
    /**
     * 获取文件类型和校验文件格式
     *
     * @param fileName
     * @return
     */
    public String getFileType(String fileName) throws BusinessException
    {
        // 文件的后缀名
        if (!fileName.contains("."))
        { throw new BusinessException("Unsupported picture formats"); }
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (fileExtension.length() == 0)
        { throw new BusinessException("Unsupported picture formats"); }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)
                || ".tif".equalsIgnoreCase(fileExtension) || ".raw".equalsIgnoreCase(fileExtension) || ".bmp".equalsIgnoreCase(fileExtension)
                || ".gif".equalsIgnoreCase(fileExtension))
        {
            return fileExtension;
        }
        else
        {
            throw new BusinessException("Unsupported picture formats");
        }
    }
}
