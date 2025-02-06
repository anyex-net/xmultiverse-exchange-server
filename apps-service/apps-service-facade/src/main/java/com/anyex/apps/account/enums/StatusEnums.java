package com.anyex.apps.account.enums;

/**
 * StatusEnums
 * <p>File：StatusEnums.java</p>
 * <p>Title: StatusEnums</p>
 * <p>Description: StatusEnums</p>
 * <p>Copyright: Copyright (c) 2019/10/30</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public enum StatusEnums
{
    NORMAL("accountNormal", "正常"), //
    FROZEN("accountFrozen", "冻结"), //
    CLOSE("accountClose", "注销");
    
    private String code;
    
    private String name;
    
    StatusEnums(String code, String name)
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
        for (StatusEnums c : StatusEnums.values())
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
