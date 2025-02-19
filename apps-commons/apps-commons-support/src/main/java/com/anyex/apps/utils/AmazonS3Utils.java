package com.anyex.apps.utils;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.anyex.apps.amazon.*;
import com.anyex.apps.consts.AppsConst;
import com.anyex.apps.consts.CharsetConst;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;

import java.io.*;
import java.util.Date;
import java.util.Map;

/**
 * 亚马逊文件存储
 * <p>File：AmazonS3Utils.java</p>
 * <p>Title: AmazonS3Utils</p>
 * <p>Description: AmazonS3Utils</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class AmazonS3Utils
{
    /* The default credentials provider */
    private static CredentialsProvider credsProvider = null;
    
    public static AmazonS3             amazonS3      = null;

    static
    {
        credsProvider = new DefaultCredentialProvider(AppsConst.AWS_ACCESS_KEY_ID, AppsConst.AWS_SECRET_ACCESS_KEY);
        initAmazonS3(); // 初始化亚马逊文件存储
    }
    
    /**
     * 获取文件上传策略
     * @param dir 上传文件的前缀
     * @param host 上传地址
     * @return
     */
    public static Map<String, Object> getPostPolicy(String dir, String host)
    {
        Map<String, Object> map = Maps.newLinkedHashMap();
        long expireEndTime = System.currentTimeMillis() + 60 * 1000;
        Date expiration = new Date(expireEndTime);
        try
        {
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            policyConds.addConditionItem(MatchMode.StartWith, "success_action_status", "");
            policyConds.addConditionItem(MatchMode.StartWith, "callback", "");
            policyConds.addConditionItem(MatchMode.StartWith, "name", "");
            policyConds.addConditionItem("bucket", AppsConst.BUCKET_TEMP_NAME);
            policyConds.addConditionItem("acl", "public-read");
            String postPolicy = generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = Base64.toBase64String(binaryData);
            String postSignature = calculatePostSignature(postPolicy);
            map.put("dir", dir);
            map.put("host", host);
            map.put("acl", "public-read");
            map.put("expire", expireEndTime);
            map.put("accessid", AppsConst.AWS_ACCESS_KEY_ID);
            map.put("policy", encodedPolicy);
            map.put("signature", postSignature);
        }
        catch (IOException e)
        {
        }
        return map;
    }
    
    public static String generatePostPolicy(Date expiration, PolicyConditions conds)
    {
        String formatedExpiration = DateUtils.formatIso8601Date(expiration);
        String jsonizedExpiration = String.format("\"expiration\":\"%s\"", formatedExpiration);
        String jsonizedConds = conds.jsonize();
        StringBuilder postPolicy = new StringBuilder();
        postPolicy.append(String.format("{%s,%s}", jsonizedExpiration, jsonizedConds));
        return postPolicy.toString();
    }
    
    public static String calculatePostSignature(String postPolicy)
    {
        try
        {
            byte[] binaryData = postPolicy.getBytes(CharsetConst.CHARSET_UT);
            String encPolicy = Base64.toBase64String(binaryData);
            return ServiceSignature.create().computeSignature(credsProvider.getCredentials().getSecretAccessKey(), encPolicy);
        }
        catch (UnsupportedEncodingException ex)
        {
            log.error("Unsupported charset: " + ex.getMessage());
        }
        return null;
    }
    
    /**
     * 本地上传文件
     * @param path 路径
     * @param fileName 文件名
     */
    public static void upload(String path, String fileName)
    {
        try
        {
            getAmazonS3().putObject(new PutObjectRequest(AppsConst.BUCKET_TEMP_NAME, fileName, new File(path)).withCannedAcl(CannedAccessControlList.PublicRead));
        }
        catch (Throwable e)
        {
            log.error("文件上传失败：{} ", e.getLocalizedMessage());
        }
    }
    
    /**
     * 创建虚拟文件夹
     * @param bucketName
     * @param folderName
     */
    public static boolean createFolder(String bucketName, String folderName)
    {
        boolean flag = true;
        try
        {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(0);
            InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, emptyContent, metadata);
            getAmazonS3().putObject(putObjectRequest);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("转移文件失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 转移临时空间中的文件到正式空间
     * <p>
     *     将真实有性的文件转移到正式空间，转移完毕之后自动清理临时文件。
     *     另：临时空间可做定期清理以减少资料开销
     * </p>
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    public static boolean moveObject(String fileName)
    {
        boolean flag = true;
        try
        {
            getAmazonS3().copyObject(new CopyObjectRequest(AppsConst.BUCKET_TEMP_NAME, fileName, AppsConst.BUCKET_BITMS_NAME, fileName).withCannedAccessControlList(CannedAccessControlList.PublicRead));
            getAmazonS3().deleteObject(AppsConst.BUCKET_TEMP_NAME, fileName);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("转移文件失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 移动目标文件夹信息到目标容器
     * @param objects
     * @param destinationBucket
     * @return
     */
    public static Boolean moveForder(ObjectListing objects, String destinationBucket)
    {
        String bucketName = objects.getBucketName();
        do
        {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries())
            {
                CopyObjectResult result = getAmazonS3().copyObject(bucketName, objectSummary.getKey(), destinationBucket, objectSummary.getKey());
                deleteObject(bucketName, objectSummary.getKey());
            }
            objects = getAmazonS3().listNextBatchOfObjects(objects);
        }
        while (objects.isTruncated());
        return true;
    }
    
    /**
     * 删除文件夹内容（必须先遍历删除文件夹内的内容）
     * @param objects
     * @return
     */
    public static Boolean deleteForder(ObjectListing objects)
    {
        String bucketName = objects.getBucketName();
        do
        {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries())
            {
                deleteObject(bucketName, objectSummary.getKey());
            }
            objects = getAmazonS3().listNextBatchOfObjects(objects);
        }
        while (objects.isTruncated());
        return true;
    }
    
    /**
     * 获取某个文件（前缀路径）下的所有信息
     * @param bucketName
     * @param prefix
     * @param isDelimiter
     * @return
     */
    public static ObjectListing getBacketObjects(String bucketName, String prefix, Boolean isDelimiter)
    {
        if (bucketName == null || bucketName.isEmpty()) { return null; }
        ListObjectsRequest objectsRequest = new ListObjectsRequest().withBucketName(bucketName);
        if (prefix != null && !prefix.isEmpty())
        {
            objectsRequest = objectsRequest.withPrefix(prefix);
        }
        if (isDelimiter)
        {
            objectsRequest = objectsRequest.withDelimiter("/");
        }
        ObjectListing objects = getAmazonS3().listObjects(objectsRequest);
        return objects;
    }
    
    /**
     * 删除临时空间中的文件
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    public static boolean deleteObject(String fileName)
    {
        return deleteObject(AppsConst.BUCKET_TEMP_NAME, fileName);
    }
    
    /**
     * 删除文件
     * @param bucketName 存储桶
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    public static boolean deleteObject(String bucketName, String fileName)
    {
        boolean flag = true;
        try
        {
            getAmazonS3().deleteObject(bucketName, fileName);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("删除文件失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 删除临时空间中的文件
     * @param bucketName 存储桶
     * @return {@link Boolean}
     */
    public static boolean deleteBucket(String bucketName)
    {
        boolean flag = true;
        try
        {
            getAmazonS3().deleteBucket(bucketName);
        }
        catch (RuntimeException e)
        {
            flag = false;
            log.error("删除存储桶失败：{} ", e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 获取亚马逊文件上传服务
     * @return
     */
    public static AmazonS3 getAmazonS3()
    {
        if (null == amazonS3)
        {// 初始化
            initAmazonS3();
        }
        return amazonS3;
    }

    /**
     * 初始化亚马逊文件上传服务
     */
    static void initAmazonS3()
    {
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTPS);
        Region region = Region.getRegion(Regions.US_EAST_1);
        AWSCredentials credentials = new BasicAWSCredentials(AppsConst.AWS_ACCESS_KEY_ID, AppsConst.AWS_SECRET_ACCESS_KEY);
        amazonS3 = AmazonS3ClientBuilder.standard()//
                .withCredentials(new AWSStaticCredentialsProvider(credentials))// 凭证
                .withClientConfiguration(clientConfig)// 连接配置
                .withRegion(region.getName()).build(); // 区域配置
    }
}
