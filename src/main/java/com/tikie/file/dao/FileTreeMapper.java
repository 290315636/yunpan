package com.tikie.file.dao;

import com.tikie.file.model.FileTree;

public interface FileTreeMapper {
    int insert(FileTree record);

    int insertSelective(FileTree record);
}