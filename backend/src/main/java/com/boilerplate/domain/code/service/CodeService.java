package com.boilerplate.domain.code.service;

import com.boilerplate.common.CommonApiException;
import com.boilerplate.common.ErrorCode;
import com.boilerplate.domain.code.dto.CodeDTO;
import com.boilerplate.domain.code.dto.CodeGroupDTO;
import com.boilerplate.domain.code.mapper.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeMapper codeMapper;

    public List<CodeDTO.ResponseCode> getCodesByGroupCode(String groupCode) {
        return codeMapper.getCodesByGroupCode(groupCode);
    }

    public List<CodeGroupDTO.ResponseCodeGroup> getAllCodeGroups() {
        return codeMapper.getAllCodeGroups();
    }
}
