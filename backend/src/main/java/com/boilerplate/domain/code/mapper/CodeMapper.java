package com.boilerplate.domain.code.mapper;

import com.boilerplate.domain.code.dto.CodeDTO;
import com.boilerplate.domain.code.dto.CodeGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {
    List<CodeDTO.ResponseCode> getCodesByGroupCode(String groupCode);

    List<CodeGroupDTO.ResponseCodeGroup> getAllCodeGroups();
}
