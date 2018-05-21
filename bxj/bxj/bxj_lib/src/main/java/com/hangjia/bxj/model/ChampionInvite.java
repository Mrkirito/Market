package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class ChampionInvite implements Serializable {
    private Long fid;

    private Long inviteUser;

    private Long beInvitedUser;

    private Integer inviteStatus;

    private Date inviteAt;

    private Date acceptAt;

    private static final long serialVersionUID = 1L;

    public ChampionInvite(Long fid, Long inviteUser, Long beInvitedUser, Integer inviteStatus, Date inviteAt, Date acceptAt) {
        this.fid = fid;
        this.inviteUser = inviteUser;
        this.beInvitedUser = beInvitedUser;
        this.inviteStatus = inviteStatus;
        this.inviteAt = inviteAt;
        this.acceptAt = acceptAt;
    }

    public ChampionInvite() {
        super();
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(Long inviteUser) {
        this.inviteUser = inviteUser;
    }

    public Long getBeInvitedUser() {
        return beInvitedUser;
    }

    public void setBeInvitedUser(Long beInvitedUser) {
        this.beInvitedUser = beInvitedUser;
    }

    public Integer getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(Integer inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public Date getInviteAt() {
        return inviteAt;
    }

    public void setInviteAt(Date inviteAt) {
        this.inviteAt = inviteAt;
    }

    public Date getAcceptAt() {
        return acceptAt;
    }

    public void setAcceptAt(Date acceptAt) {
        this.acceptAt = acceptAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ChampionInvite other = (ChampionInvite) that;
        return (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
            && (this.getInviteUser() == null ? other.getInviteUser() == null : this.getInviteUser().equals(other.getInviteUser()))
            && (this.getBeInvitedUser() == null ? other.getBeInvitedUser() == null : this.getBeInvitedUser().equals(other.getBeInvitedUser()))
            && (this.getInviteStatus() == null ? other.getInviteStatus() == null : this.getInviteStatus().equals(other.getInviteStatus()))
            && (this.getInviteAt() == null ? other.getInviteAt() == null : this.getInviteAt().equals(other.getInviteAt()))
            && (this.getAcceptAt() == null ? other.getAcceptAt() == null : this.getAcceptAt().equals(other.getAcceptAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
        result = prime * result + ((getInviteUser() == null) ? 0 : getInviteUser().hashCode());
        result = prime * result + ((getBeInvitedUser() == null) ? 0 : getBeInvitedUser().hashCode());
        result = prime * result + ((getInviteStatus() == null) ? 0 : getInviteStatus().hashCode());
        result = prime * result + ((getInviteAt() == null) ? 0 : getInviteAt().hashCode());
        result = prime * result + ((getAcceptAt() == null) ? 0 : getAcceptAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", inviteUser=").append(inviteUser);
        sb.append(", beInvitedUser=").append(beInvitedUser);
        sb.append(", inviteStatus=").append(inviteStatus);
        sb.append(", inviteAt=").append(inviteAt);
        sb.append(", acceptAt=").append(acceptAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}