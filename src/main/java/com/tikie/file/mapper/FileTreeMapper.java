package com.tikie.file.mapper;

import com.tikie.file.bean.FileTree;

public interface FileTreeMapper {
    int insert(FileTree record);

    int insertSelective(FileTree record);
}