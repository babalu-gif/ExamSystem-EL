package com.my.entity;

/**
 * 用户类
 */
public class Users
{
    private Integer user_Id;
    private String user_Name;
    private String user_Password;
    private String user_Sex;
    private String user_Email;

    // 构造方法
    public Users()
    {
    }

    public Users(Integer user_Id, String user_Name, String user_Password, String user_Sex, String user_Email)
    {
        this.user_Id = user_Id;
        this.user_Name = user_Name;
        this.user_Password = user_Password;
        this.user_Sex = user_Sex;
        this.user_Email = user_Email;
    }

    // getter and setter


    public Integer getUser_Id()
    {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id)
    {
        this.user_Id = user_Id;
    }

    public String getUser_Name()
    {
        return user_Name;
    }

    public void setUser_Name(String user_Name)
    {
        this.user_Name = user_Name;
    }

    public String getUser_Password()
    {
        return user_Password;
    }

    public void setUser_Password(String user_Password)
    {
        this.user_Password = user_Password;
    }

    public String getUser_Sex()
    {
        return user_Sex;
    }

    public void setUser_Sex(String user_Sex)
    {
        this.user_Sex = user_Sex;
    }

    public String getUser_Email()
    {
        return user_Email;
    }

    public void setUser_Email(String user_Email)
    {
        this.user_Email = user_Email;
    }
}
