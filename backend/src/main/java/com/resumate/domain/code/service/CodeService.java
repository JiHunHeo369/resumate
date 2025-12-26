package com.resumate.domain.code.service;

import com.resumate.domain.code.dto.CodeDTO;
import com.resumate.domain.code.repository.CodeRepository;
import com.resumate.common.entity.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    // 코드 전체 조회
    public List<CodeDTO.CodeListResponse> getCodeList() {
        List<Code> codes = codeRepository.findAll();

        return codes.stream()
                .map(code -> CodeDTO.CodeListResponse.builder()
                        .id(code.getId())
                        .groupCode(code.getGroupCode())
                        .code(code.getCode())
                        .name(code.getName())
                        .createdBy(code.getCreatedBy())
                        .createdAt(code.getCreatedAt())
                        .updatedBy(code.getUpdatedBy())
                        .updatedAt(code.getUpdatedAt())
                        .build())
                .toList();
    }
}

