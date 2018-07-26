package com.tikie.file.dao;

import com.github.pagehelper.Page;
import com.tikie.file.model.FileTree;
import com.tikie.file.model.SuperTreeVo;

import java.util.List;

public interface FileTreeMapper {
    int insert(FileTree record);

    int insertSelective(FileTree record);

    List<SuperTreeVo> selectListTreeBySuper();
}