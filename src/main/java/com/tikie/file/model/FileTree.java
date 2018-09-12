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
    
    private String rebackType;

    private String size;

    private String thumbnail;
    
    private String type;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.name = name;
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
        this.fileId = fileId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getReback() {
        return reback;
    }

    public void setReback(String reback) {
        this.reback = reback;
    }

    public String getRebackType() {
        return rebackType;
    }

    public void setRebackType(String rebackType) {
        this.rebackType = rebackType;
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
        this.thumbnail = thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FileTree() {
        super();
    }

    public FileTree(String id, Boolean isFile, String name, int sort, String ctime, String utime, String fileId,
            String pid, String reback, String rebackType, String size, String thumbnail, String type) {
        super();
        this.id = id;
        this.isFile = isFile;
        this.name = name;
        this.sort = sort;
        this.ctime = ctime;
        this.utime = utime;
        this.fileId = fileId;
        this.pid = pid;
        this.reback = reback;
        this.rebackType = rebackType;
        this.size = size;
        this.thumbnail = thumbnail;
        this.type = type;
    }

    @Override
    public String toString() {
        return "FileTree [id=" + id + ", isFile=" + isFile + ", name=" + name + ", sort=" + sort + ", ctime=" + ctime
                + ", utime=" + utime + ", fileId=" + fileId + ", pid=" + pid + ", reback=" + reback + ", rebackType="
                + rebackType + ", size=" + size + ", thumbnail=" + thumbnail + ", type=" + type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ctime == null) ? 0 : ctime.hashCode());
        result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isFile == null) ? 0 : isFile.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pid == null) ? 0 : pid.hashCode());
        result = prime * result + ((reback == null) ? 0 : reback.hashCode());
        result = prime * result + ((rebackType == null) ? 0 : rebackType.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + sort;
        result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((utime == null) ? 0 : utime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FileTree other = (FileTree) obj;
        if (ctime == null) {
            if (other.ctime != null)
                return false;
        } else if (!ctime.equals(other.ctime))
            return false;
        if (fileId == null) {
            if (other.fileId != null)
                return false;
        } else if (!fileId.equals(other.fileId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isFile == null) {
            if (other.isFile != null)
                return false;
        } else if (!isFile.equals(other.isFile))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pid == null) {
            if (other.pid != null)
                return false;
        } else if (!pid.equals(other.pid))
            return false;
        if (reback == null) {
            if (other.reback != null)
                return false;
        } else if (!reback.equals(other.reback))
            return false;
        if (rebackType == null) {
            if (other.rebackType != null)
                return false;
        } else if (!rebackType.equals(other.rebackType))
            return false;
        if (size == null) {
            if (other.size != null)
                return false;
        } else if (!size.equals(other.size))
            return false;
        if (sort != other.sort)
            return false;
        if (thumbnail == null) {
            if (other.thumbnail != null)
                return false;
        } else if (!thumbnail.equals(other.thumbnail))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (utime == null) {
            if (other.utime != null)
                return false;
        } else if (!utime.equals(other.utime))
            return false;
        return true;
    }

}