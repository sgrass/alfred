package com.alfred.model;

public class Permission {
    private Integer id;

    private Integer permissId;

    private String permissName;

    private String permissDesc;

    private String permissRule;

    private String permModel;

    private Integer permisLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermissId() {
        return permissId;
    }

    public void setPermissId(Integer permissId) {
        this.permissId = permissId;
    }

    public String getPermissName() {
        return permissName;
    }

    public void setPermissName(String permissName) {
        this.permissName = permissName == null ? null : permissName.trim();
    }

    public String getPermissDesc() {
        return permissDesc;
    }

    public void setPermissDesc(String permissDesc) {
        this.permissDesc = permissDesc == null ? null : permissDesc.trim();
    }

    public String getPermissRule() {
        return permissRule;
    }

    public void setPermissRule(String permissRule) {
        this.permissRule = permissRule == null ? null : permissRule.trim();
    }

    public String getPermModel() {
        return permModel;
    }

    public void setPermModel(String permModel) {
        this.permModel = permModel == null ? null : permModel.trim();
    }

    public Integer getPermisLevel() {
        return permisLevel;
    }

    public void setPermisLevel(Integer permisLevel) {
        this.permisLevel = permisLevel;
    }
}