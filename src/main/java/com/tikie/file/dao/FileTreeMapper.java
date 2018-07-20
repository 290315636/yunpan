package com.tikie.file.dao;

import com.tikie.file.model.FileTreeDTO;

public interface FileTreeMapper {
    int insert(FileTreeDTO record);

    int insertSelective(FileTreeDTO record);
}