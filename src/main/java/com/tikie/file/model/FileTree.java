package com.tikie.file.model;

import java.io.Serializable;

public class FileTree implements Serializable {
    private String id;

    private Boolean isFile;

    private String name;

    private int sort;

    private String ctime;

    private String utime;

    private String fileId;

    private String pid;

    private String reback;

    private String size;

    private String thumbnail;
    
    private String type;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Boolean getIsFile() {
        return isFile;
    }

    public void setIsFile(Boolean isFile) {
        this.isFile = isFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getReback() {
        return reback;
    }

    public void setReback(String reback) {
        this.reback = reback == null ? null : reback.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
        FileTree other = (FileTree) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsFile() == null ? other.getIsFile() == null : this.getIsFile().equals(other.getIsFile()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSort() == other.getSort())
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getReback() == null ? other.getReback() == null : this.getReback().equals(other.getReback()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getThumbnail() == null ? other.getThumbnail() == null : this.getThumbnail().equals(other.getThumbnail()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsFile() == null) ? 0 : getIsFile().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + getSort();
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getReback() == null) ? 0 : getReback().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getThumbnail() == null) ? 0 : getThumbnail().hashCode());
        return result;
    }

	@Override
	public String toString() {
		return "FileTree [id=" + id + ", isFile=" + isFile + ", name=" + name + ", sort=" + sort + ", ctime=" + ctime
				+ ", utime=" + utime + ", fileId=" + fileId + ", pid=" + pid + ", reback=" + reback + ", size=" + size
				+ ", thumbnail=" + thumbnail + ", type=" + type + "]";
	}

}