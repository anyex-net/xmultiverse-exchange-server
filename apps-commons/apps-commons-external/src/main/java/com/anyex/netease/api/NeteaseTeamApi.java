package com.anyex.netease.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.netease.config.NeteaseConfig;

import java.util.Map;

public class NeteaseTeamApi extends NeteaseApi {
    static
    {

    }

    /**
     创建高级群
     * 参数	类型	必填	说明
     * tname	String	是	群名称，最大长度 64 位字符
     * owner	String	是	群主帐号，accid，最大长度 32 位字符
     * members	String	是	邀请的群成员列表，\["aaa","bbb"\](JSONArray 对应的 accid，如果解析出错会报 414)
     * members 与 owner 总和上限为 200。members 中无需再加 owner 自己的账号
     * announcement	String	否	群公告，最大长度 1024 位字符
     * intro	String	否	群描述，最大长度 512 位字符
     * msg	String	是	邀请发送的文字，最大长度 150 位字符
     * magree	Integer	否	创建群时，若 members 不为空，那么邀请其入群是否需要同意
     * 0，不需要被邀请人同意加入群（默认）；1，需要被邀请人同意才可以加入群
     * 只有当 beinvitemode = 0 时，magree 才能设为 1，即 时，magree =1 才生效。
     * joinmode	Integer	是	群创建完成后，通过 SDK 侧操作申请入群的验证方式
     * 0，不用验证；1，需要群主或管理员的验证；2，不允许任何人加入
     * custom	String	否	自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性，建议为 JSON，最大长度 1024 位字符
     * icon	String	否	群头像，最大长度 1024 位字符
     * beinvitemode	Integer	否	群创建完成后，邀请入群时是否需要被邀请人的同意
     * 0，需要同意（默认）；1，不需要同意
     * invitemode	Integer	否	邀请权限，即谁可以邀请他人入群
     * 0，群主和管理员（默认）；1，所有人
     * uptinfomode	Integer	否	客户端修改群信息权限，即谁可以修改群信息
     * 0，群主和管理员（默认）；1，所有人
     * upcustommode	Integer	否	客户端修改群自定义属性权限，即谁可以修改群自定义属性
     * 0，群主和管理员（默认）；1，所有人
     * teamMemberLimit	Integer	否	最大群成员数（包含群主），[2，200(默认)]
     * isNotifyCloseOnline	Integer	否	是否关闭群通知消息在线发送
     * 0，否；1，是
     * isNotifyClosePersistent	Integer	否	是否关闭存储离线/漫游/历史的群通知消息
     * 0，否；1，是
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * bid	String	否	反垃圾业务 ID，JSON 字符串，{"textbid":"","picbid":""}，若不填则使用原来的反垃圾配置
     *
     * {
     *     "code":200,
     *     "tid":"11001"
     *     "faccid":{  //如果创建时邀请的成员中存在加群数量超过限制的情况，会返回faccid
     *          "accid":["a","b","c"],  //用户accid
     *          "msg":"team count exceed"
     *      }
     * }
     */
    public static JSONObject teamCreate(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tname")))
        {
            throw new BusinessException("tname不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("members")))
        {
            throw new BusinessException("members不能为空");
        }
        if(StringUtils.isBlank(body.get("msg")))
        {
            throw new BusinessException("msg不能为空");
        }
        if(StringUtils.isBlank(body.get("joinmode")))
        {
            throw new BusinessException("joinmode不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamCreateUrl,body));
        return obj;
    }


    /**
     * 拉人入群
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群组唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	邀请人的用户帐号，accid，最大长度 32 位字符，按照群属性 invitemode（0：只有群主和管理员可以邀请他人入群，1：所有人都可以邀请）配置传入
     * members	String	是	被邀请入群的用户列表，\["aaa","bbb"\](JSONArray 对应的 accid，如果解析出错会报 414)，一次最多邀请 200 个成员
     * magree	Integer	否	邀请用户入群时的验证模式
     * 0，不需要被邀请人同意加入群（默认）；1，需要被邀请人同意才可以加入群
     * 只有当 magree =1 且群属性 beinvitemode !=0 时，邀请用户入群时才需要被邀请方同意，其他情况下入群都不需要被邀请方的同意
     * msg	String	是	邀请发送的文字，最大长度 150 位字符
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     *     "faccid":{   //如果邀请的人中存在加群数量超限的情况，会返回faccid
     *          "accid":["a","b","c"],
     *          "msg":"team count exceed"
     *      }
     * }
     * @throws BusinessException
     */
    public static JSONObject teamAdd(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("members")))
        {
            throw new BusinessException("members不能为空");
        }
        if(StringUtils.isBlank(body.get("msg")))
        {
            throw new BusinessException("msg不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamAddUrl,body));
        return obj;
    }

    /**
     * 添加管理员
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主帐号，accid，最大长度 32 位字符
     * members	String	是	需要添加为管理员的用户账号，\["aaa","bbb"\](JSONArray 对应的 accid，如果解析出错会报 414)，最大长度 1024 位字符（一次最多添加 10 人）
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamAddManager(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("members")))
        {
            throw new BusinessException("members不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamaddManagerUrl,body));
        return obj;
    }

    /**
     * 删除管理员
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主帐号，accid，最大长度 32 位字符
     * members	String	是	需要移除的管理员账号，\["aaa","bbb"\](JSONArray 对应的 accid，如果解析出错会报 414)，长度最大 1024 位字符（一次最多移除 10 人）
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamRemoveManager(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("members")))
        {
            throw new BusinessException("members不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamRemoveManagerUrl,body));
        return obj;
    }

    /**
     * 转让群主
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主帐号，accid，最大长度 32 位字符
     * newowner	String	是	新群主帐号，accid，最大长度 32 位字符
     * leave	Integer	是	1，群主身份转让后离开群；2，群主身份转让后成为普通成员
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamChangeOwner(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("members")))
        {
            throw new BusinessException("members不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamChangeOwnerUrl,body));
        return obj;
    }


    /**
     * 禁言群主
     * @param body
     * 参数	类型	必须	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主账号，accid，最大长度 32 位字符
     * mute	Boolean	否	true，禁言普通成员；false，解禁
     * 当 mute 为空时则根据 muteType 取值进行处理。
     * mute 和 muteType 至少提供一个，两个都提供时，按 mute 处理；两个都不提供时，返回 414 错误码。
     * muteType	Integer	否	禁言类型
     * 0，解除禁言；1，禁言普通成员；3，禁言整个群（包括群主）
     * 只有 mute 为空时，该字段才有效。
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamMuteTlistAll(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("muteType")))
        {
            throw new BusinessException("muteType不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamMuteTlistAllUrl,body));
        return obj;
    }


    /**
     * 禁言指定群成员
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主账号，accid，最大长度 32 位字符
     * accid	String	是	禁言对象的账号，accid，最大长度 32 位字符
     * mute	Integer	是	1，禁言；0，解禁
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamMuteTlist(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("mute")))
        {
            throw new BusinessException("mute不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamMuteTlistUrl,body));
        return obj;
    }

    /**
     * 踢人出群
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主或管理员帐号，accid，最大长度 32 位字符
     * member	String	当移除单个成员时必填	被移除的用户账号 accid，最大长度 32 位字符
     * 相对于 members，优先使用 member 参数
     * members	String	当移除多个成员时必填	被移除的用户账号列表，["aaa","bbb"]（JSONArray 对应的 accid，如果解析出错，会报 414）一次最多操作 200 人
     * 当 member 和 members 都为空时，返回 414 错误码
     * 当 member 不为空，无论 members 是否空，都取 member 字段移除单个成员，忽略 members
     * 当 member 为空，members 不为空时，取 members 字段批量移除多个成员
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     *     "faccid":{   //如果踢除失败，会返回faccid
     *          "accid":["a","b","c"],  // 账号 list<String>
     *          "msg":"team count exceed"
     *      }
     * }
     * @throws BusinessException
     */
    public static JSONObject teamKick(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamKickUrl,body));
        return obj;
    }


    /**
     * 主动退群
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * accid	String	是	退群用户的账号，accid，最大长度 32 位字符
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamLeave(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamLeaveUrl,body));
        return obj;
    }

    /**
     * 修改群成员昵称
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主账号，accid，最大长度 32 位字符
     * accid	String	是	需要修改群昵称的群成员账号，accid，最大长度 32 位字符
     * nick	String	否	accid 对应的群昵称，最大长度 32 位字符
     * custom	String	否	自定义扩展字段，最大长度 1024 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamUpdateTeamNick(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        if(StringUtils.isBlank(body.get("nick")))
        {
            throw new BusinessException("nick不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamUpdateTeamNickUrl,body));
        return obj;
    }

    /**
     * 修改群组信息
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * tname	String	否	群名称，最大长度 64 位字符
     * owner	String	是	群主账号，accid，最大长度 32 位字符
     * 默认只有群主能修改群信息，若群管理员也需要该权限，可以联系商务和技术支持进行开通
     * announcement	String	否	群公告，最大长度 1024 位字符
     * intro	String	否	群描述，最大长度 512 位字符
     * icon	String	否	群头像，最大长度 1024 位字符
     * joinmode	Integer	否	群创建完成后，通过 SDK 侧操作申请入群的验证方式
     * 0，不用验证；1，需要群主或管理员的验证；2，不允许任何人加入
     * beinvitemode	Integer	否	群创建完成后，邀请入群时是否需要被邀请人的同意
     * 0，需要同意（默认）；1，不需要同意
     * invitemode	Integer	否	邀请权限，即谁可以邀请他人入群
     * 0，群主和管理员（默认）；1，所有人
     * uptinfomode	Integer	否	客户端修改群信息权限，即谁可以修改群信息
     * 0，群主和管理员（默认）；1，所有人
     * upcustommode	Integer	否	客户端修改群自定义属性权限，即谁可以修改群自定义属性
     * 0，群主和管理员（默认）；1，所有人
     * teamMemberLimit	Integer	否	群最大人数（包含群主），[2，200(默认)]
     * custom	String	否	自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性，建议为 JSON，最大长度 1024 位字符
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * bid	String	否	反垃圾业务 ID，JSON 字符串，{"textbid":"","picbid":""}，若不填则使用原来的反垃圾配置
     * @return
     * @throws BusinessException
     */
    public static JSONObject teamUpdate(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamUpdateUrl,body));
        return obj;
    }

    /**
     * 设置群消息提醒开关
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务端产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * accid	String	是	需要设置提醒开关的群成员账号，accid，最大长度 32 位字符
     * ope	Integer	是	1，关闭消息提醒；2，开启消息提醒
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamMuteTeam(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamMuteTeamUrl,body));
        return obj;
    }


    /**
     * 解散群组
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主帐号，accid，最大长度 32 位字符
     * attach	String	否	自定义扩展字段，最大长度 512 位字符
     * @return
     * {
     *     "code":200
     * }
     * @throws BusinessException
     */
    public static JSONObject teamRemove(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamRemoveUrl,body));
        return obj;
    }

    /**
     * 获取群组详细信息
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * @return
     * 返回参数
     * 参数	类型	说明
     * code	Integer	状态码
     * tinfo	String	群组的详细信息
     * tinfo 中的参数说明
     *
     * 参数	类型	说明
     * icon	String	群头像
     * announcement	String	群公告
     * uptinfomode	Integer	客户端修改群信息权限
     * 0，群主和管理员（默认）；1，所有人
     * intro	String	群介绍
     * maxusers	Integer	群成员最大数量
     * upcustommode	Integer	修客户端改群自定义属性的权限
     * 0，群主和管理员（默认）；1，所有人
     * tname	String	群名称
     * beinvitemode	Integer	邀请入群的验证方式，即邀请入群后，是否需要被邀请人的同意
     * 0，需要同意（默认）；1，不需要同意
     * joinmode	Integer	申请入群的验证方式
     * 0，不用验证；1，需要群主或管理员的验证；2，不允许任何人加入
     * tid	Long	群 ID
     * invitemode	Integer	邀请入群的权限，即谁可以邀请他人入群
     * 0，群主和管理员（默认）；1，所有人
     * mute	Boolean	是否全员禁言
     * muteType	Integer	禁言类型
     * 0，解除禁言；1，禁言普通成员；3，禁言整个群（包括群主）
     * custom	String	自定义高级群扩展属性
     * clientCustom	String	客户端自定义字段
     * createtime	Long	创建时间
     * updatetime	Long	更新时间
     * isNotifyCloseOnline	Boolean	群通知消息是否关闭在线发送（开启该功能才会有该字段）
     * isNotifyClosePersistent	Boolean	群通知消息是否关闭持久化存储（开启该功能才会有该字段）
     * owner	String	群主信息，包含群昵称（nick）、用户 ID（accid）、是否被禁言（mute）等信息
     * admins	String	管理员信息，JSON，包含群昵称（nick）、用户 ID（accid）、是否被禁言（mute）等信息
     * members	String	群成员信息，JSON，包含群昵称（nick）、用户 ID（accid）、是否被禁言（mute）等信息
     * @throws BusinessException
     */
    public static JSONObject teamQueryDetail(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamQueryDetailUrl,body));
        return obj;
    }


    /**
     *获取群组禁言列表
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * owner	String	是	群主账号，accid，最大长度 32 位字符
     * @return
     * 返回参数
     * 参数	类型	说明
     * code	Integer	状态码
     * mutes	String	被禁言的群成员列表，JSON
     * mutes 中的参数说明
     *
     * 参数	类型	说明
     * nick	String	群成员昵称
     * accid	String	群成员账号，accid
     * tid	Long	群 ID
     * type	Integer	群成员类型
     * 0，普通成员；1，群主；2，管理员
     * @throws BusinessException
     */
    public static JSONObject teamListTeamMute(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("owner")))
        {
            throw new BusinessException("owner不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamListTeamMuteUrl,body));
        return obj;
    }

    /**
     * 获取群消息已读未读详情
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * msgid	Long	是	发送群已读业务消息时服务器返回的消息 ID，最大长度 64 位长整型
     * fromAccid	String	是	消息发送者账号，accid，最大长度 32 位字符
     * snapshot	Boolean	否	是否返回已读、未读成员的 IM 账号（accid）列表，默认为 false
     * @return
     * @throws BusinessException
     */
    public static JSONObject teamGetMarkReadInfoMute(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        if(StringUtils.isBlank(body.get("msgid")))
        {
            throw new BusinessException("msgid不能为空");
        }
        if(StringUtils.isBlank(body.get("fromAccid")))
        {
            throw new BusinessException("fromAccid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamGetMarkReadInfoMuteUrl,body));
        return obj;
    }


    /**
     * 获取用户已加入的群组信息
     * @param body
     * 参数	类型	必填	说明
     * accid	String	是	需要查询的用户账户，accid，最大长度 32 位字符
     * @return
     *返回参数
     * 参数	类型	说明
     * code	Integer	状态码，请求成功则返回 200
     * count	Integer	该用户已加入的群组的数量
     * infos	String	该用户已加入的群组的详细信息，JSONArray 格式
     * infos 中的参数说明
     *
     * 参数	类型	说明
     * owner	String	群主账户，accid
     * tname	String	群名称
     * maxusers	Integer	群人数上限
     * tid	Long	群 ID
     * size	Integer	当前群成员数量
     * custom	String	群组自定义信息
     * @throws BusinessException
     */
    public static JSONObject teamJoinTeams(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamJoinTeamsUrl,body));
        return obj;
    }

    /**
     * 获取用户已加入的群组的所有群成员信息
     * @param body
     * 参数	类型	必填	说明
     * accid	String	是	需要查询的用户账号，accid，最大长度 32 位字符
     * @return
     * 返回参数
     * 参数	类型	说明
     * code	Integer	状态码
     * data	String	加入群的所有群成员信息
     * data 中的参数说明
     *
     * 参数	类型	说明
     * tid	Long	群 ID
     * accid	String	群成员账号 ID
     * nick	String	群昵称
     * mute	Boolean	是否禁言
     * custom	String	自定义扩展信息
     * managerPushEnable	Boolean	是否接收管理员消息推送
     * pushEnable	Boolean	是否接收推送
     * createtime	Long	创建时间
     * updatetime	Long	更新时间
     * @throws BusinessException
     */
    public static JSONObject teamListMemberInfo(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("accid")))
        {
            throw new BusinessException("accid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamListMemberInfoUrl,body));
        return obj;
    }


    /**
     * 获取群组的在线成员列表
     * @param body
     * 参数	类型	必填	说明
     * tid	Long	是	云信服务器产生，群唯一标识，创建群时会返回，最大长度 64 位长整型
     * @return
     * 返回参数
     * 参数	类型	说明
     * code	Integer	状态码
     * data	String	在线群成员信息
     * data 中的参数说明
     *
     * 参数	类型	说明
     * count	Integer	在线群成员数量
     * status	String	在线成员状态，包含登录时间（loginTime）和在线属性（客户端类型，clientType）
     * clientType 说明：UNSET(0)；Android(1)；IOS(2)；PC(4)；WINPHONE(8)；WEB(16)；REST(32)；MAC(64)
     * @throws BusinessException
     */
    public static JSONObject teamListOnlineUsers(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tid")))
        {
            throw new BusinessException("tid不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamListOnlineUsersUrl,body));
        return obj;
    }

    /**
     *批量获取群组信息与成员列表
     * @param body
     * 参数	类型	必填	说明
     * tids	String	是	群 ID 列表，如["3083","3084"]，一次最多查询 10 个群，最大长度 1024 位字符
     * ope	Integer	是	1，表示带上群成员列表；0，表示不带群成员列表，只返回群信息
     * @return
     * 返回参数
     * 参数	类型	说明
     * code	Integer	状态码
     * tinfos	String	群组信息，JSONArray 格式
     * tinfos中的参数说明
     *
     * 参数	类型	说明
     * tname	String	群名称
     * icon	String	群头像
     * owner	String	群主用户帐号
     * maxusers	Integer	群成员最大数量
     * tid	Long	群 ID
     * size	Integer	当前群成员数量
     * announcement	String	群公告
     * intro	String	群介绍
     * joinmode	Integer	申请入群的验证方式
     * 0，不用验证；1，需要验证；2，不允许任何人加入
     * beinvitemode	Integer	群创建完成后，邀请入群时是否需要被邀请人的同意
     * 0，需要同意（默认）；1，不需要同意
     * invitemode	Integer	邀请权限，即谁可以邀请他人入群
     * 0，群主和管理员（默认）；1，所有人
     * uptinfomode	Integer	客户端修改群信息权限，即谁可以修改群信息
     * 0，群主和管理员（默认）；1，所有人
     * upcustommode	Integer	客户端修改群自定义属性权限，即谁可以修改群自定义属性
     * 0，群主和管理员（默认）；1，所有人
     * muteType	Integer	群禁言类型
     * 0，解除禁言；1，禁言普通成员；3，禁言整个群（包括群主）
     * isNotifyCloseOnline	Boolean	群通知消息是否关闭在线发送（开启该功能才会有该字段）
     * isNotifyClosePersistent	Boolean	群通知消息是否关闭持久化存储（开启该功能才会有该字段）
     * custom	String	自定义高级群扩展属性
     * clientCustom	String	客户端自定义字段
     * mute	Boolean	是否全员禁言
     * admins	String	管理员账号
     * members	String	群成员列表
     * createtime	Long	创建时间
     * updatetime	Long	更新时间
     * @throws BusinessException
     */
    public static JSONObject teamQuery(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tids")))
        {
            throw new BusinessException("tids不能为空");
        }
        if(StringUtils.isBlank(body.get("ope")))
        {
            throw new BusinessException("ope不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamQueryUrl,body));
        return obj;
    }


    /**
     * 批量获取群组的在线成员数量
     * @param body
     * 参数	类型	必填	说明
     * tids	String	是	群 ID 列表，JSONArray，每一项表示一个 tid，最多查询 100 个群组，单个 tid 最大长度 64 位长整型
     * @return
     * 返回参数
     * 参数	类型	说明
     * code	Integer	状态码
     * data	String	在线群成员数量
     * data 中的参数说明
     *
     * 参数	类型	说明
     * tid	Long	群 ID
     * onlineUserCount	Integer	在线成员数量
     * offlineUserCount	Integer	非在线成员数量
     * @throws BusinessException
     */
    public static JSONObject teamListOnlineUserCount(Map<String, String> body) throws BusinessException
    {
        if(StringUtils.isBlank(body.get("tids")))
        {
            throw new BusinessException("tids不能为空");
        }
        JSONObject obj = JSON.parseObject(post(NeteaseConfig.teamListOnlineUserCountUrl,body));
        return obj;
    }
}
