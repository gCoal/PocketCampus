package com.google.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
@Table(name = "lupdpmessage", schema = "public", catalog = "LUPDP_TEST")
public class LupdpmessageEntity {
    private String id;
    private Integer cloudappid;
    private String creator;
    private Timestamp createtime;
    private String updater;
    private Timestamp updatetime;
    private String userid;
    private String messagetype;
    private String operationtype;
    private String newid;
    private String messagetoid;
    private String startid;
    private Integer isread;
    private String starttype;
    private Integer isreceive;
    private String nickname;
    private String signature;
    private String picpath;
    private String isdelete;
    private byte[] sharereason;

    @Id
    @Column(name = "id", nullable = false, length = 64)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cloudappid", nullable = true)
    public Integer getCloudappid() {
        return cloudappid;
    }

    public void setCloudappid(Integer cloudappid) {
        this.cloudappid = cloudappid;
    }

    @Basic
    @Column(name = "creator", nullable = true, length = 200)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "createtime", nullable = true)
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "updater", nullable = true, length = 200)
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    @Basic
    @Column(name = "userid", nullable = true, length = 200)
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "messagetype", nullable = true, length = 200)
    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    @Basic
    @Column(name = "operationtype", nullable = true, length = 200)
    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    @Basic
    @Column(name = "newid", nullable = true, length = 200)
    public String getNewid() {
        return newid;
    }

    public void setNewid(String newid) {
        this.newid = newid;
    }

    @Basic
    @Column(name = "messagetoid", nullable = true, length = 200)
    public String getMessagetoid() {
        return messagetoid;
    }

    public void setMessagetoid(String messagetoid) {
        this.messagetoid = messagetoid;
    }

    @Basic
    @Column(name = "startid", nullable = true, length = 200)
    public String getStartid() {
        return startid;
    }

    public void setStartid(String startid) {
        this.startid = startid;
    }

    @Basic
    @Column(name = "isread", nullable = true)
    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    @Basic
    @Column(name = "starttype", nullable = true, length = 200)
    public String getStarttype() {
        return starttype;
    }

    public void setStarttype(String starttype) {
        this.starttype = starttype;
    }

    @Basic
    @Column(name = "isreceive", nullable = true)
    public Integer getIsreceive() {
        return isreceive;
    }

    public void setIsreceive(Integer isreceive) {
        this.isreceive = isreceive;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 200)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "signature", nullable = true, length = 200)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "picpath", nullable = true, length = 200)
    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    @Basic
    @Column(name = "isdelete", nullable = true, length = 200)
    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    @Basic
    @Column(name = "sharereason", nullable = true)
    public byte[] getSharereason() {
        return sharereason;
    }

    public void setSharereason(byte[] sharereason) {
        this.sharereason = sharereason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LupdpmessageEntity that = (LupdpmessageEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (cloudappid != null ? !cloudappid.equals(that.cloudappid) : that.cloudappid != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (updater != null ? !updater.equals(that.updater) : that.updater != null) return false;
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (messagetype != null ? !messagetype.equals(that.messagetype) : that.messagetype != null) return false;
        if (operationtype != null ? !operationtype.equals(that.operationtype) : that.operationtype != null)
            return false;
        if (newid != null ? !newid.equals(that.newid) : that.newid != null) return false;
        if (messagetoid != null ? !messagetoid.equals(that.messagetoid) : that.messagetoid != null) return false;
        if (startid != null ? !startid.equals(that.startid) : that.startid != null) return false;
        if (isread != null ? !isread.equals(that.isread) : that.isread != null) return false;
        if (starttype != null ? !starttype.equals(that.starttype) : that.starttype != null) return false;
        if (isreceive != null ? !isreceive.equals(that.isreceive) : that.isreceive != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (signature != null ? !signature.equals(that.signature) : that.signature != null) return false;
        if (picpath != null ? !picpath.equals(that.picpath) : that.picpath != null) return false;
        if (isdelete != null ? !isdelete.equals(that.isdelete) : that.isdelete != null) return false;
        if (!Arrays.equals(sharereason, that.sharereason)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cloudappid != null ? cloudappid.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (messagetype != null ? messagetype.hashCode() : 0);
        result = 31 * result + (operationtype != null ? operationtype.hashCode() : 0);
        result = 31 * result + (newid != null ? newid.hashCode() : 0);
        result = 31 * result + (messagetoid != null ? messagetoid.hashCode() : 0);
        result = 31 * result + (startid != null ? startid.hashCode() : 0);
        result = 31 * result + (isread != null ? isread.hashCode() : 0);
        result = 31 * result + (starttype != null ? starttype.hashCode() : 0);
        result = 31 * result + (isreceive != null ? isreceive.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        result = 31 * result + (picpath != null ? picpath.hashCode() : 0);
        result = 31 * result + (isdelete != null ? isdelete.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(sharereason);
        return result;
    }
}
