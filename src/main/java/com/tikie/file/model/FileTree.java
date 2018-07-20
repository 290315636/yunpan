package com.tikie.file.model;

import java.io.Serializable;

public class FileTree implements Serializable {
    private String id;

    private String fileId;

    private int relateCount;

    private int downloadCount;

    private int shareCount;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public int getRelateCount() {
        return relateCount;
    }

    public void setRelateCount(int relateCount) {
        this.relateCount = relateCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
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
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getRelateCount() == other.getRelateCount())
            && (this.getDownloadCount() == other.getDownloadCount())
            && (this.getShareCount() == other.getShareCount());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + getRelateCount();
        result = prime * result + getDownloadCount();
        result = prime * result + getShareCount();
        return result;
    }
}