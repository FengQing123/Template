package com.example.template.bean;

/**
 * 功能描述：
 * Created by gfq on 2020/10/16
 **/
public class Star {
    private String name;
    private String groupName;

    public Star(String name, String groupName) {
        this.name = name;
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
