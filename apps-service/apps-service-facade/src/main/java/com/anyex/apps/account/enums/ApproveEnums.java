package com.anyex.apps.account.enums;

/**
 * ApproveEnums
 * <p>File：ApproveEnums.java</p>
 * <p>Title: ApproveEnums</p>
 * <p>Description: ApproveEnums</p>
 * <p>Copyright: Copyright (c) 2019/10/28</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public enum ApproveEnums
{
    STATUS_INIT("init", "初始化"), //
    STATUS_SUBMIT("submit", "提交待审核"), //
    STATUS_APPROVE("approve", "审核通过"), //
    STATUS_REJECT("reject", "审核拒绝");

    private String code;
    
    private String name;
    
    ApproveEnums(String code, String name)
    {
        this.code = code;
        this.name = name;
    }
    
    /**
     * 根据code取名称
     * @param code
     * @return
     */
    public String getName(String code)
    {
        String result = null;
        for (ApproveEnums c : ApproveEnums.values())
        {
            if (c.code.equals(code))
            {
                result = c.name;
                break;
            }
        }
        return result;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
