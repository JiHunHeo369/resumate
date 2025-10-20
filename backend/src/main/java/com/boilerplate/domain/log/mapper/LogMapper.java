package com.boilerplate.domain.log.mapper;

import com.boilerplate.domain.log.entity.ErrorLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    void insertErrorLog(ErrorLog errorLog);
}
