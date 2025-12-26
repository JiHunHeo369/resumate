package com.resumate.domain.code.controller;

import com.resumate.domain.code.dto.CodeDTO;
import com.resumate.domain.code.service.CodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "코드 API", description = "코드 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/code")
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/")
    public ResponseEntity<List<CodeDTO.CodeListResponse>> getCodeList() {
        return ResponseEntity.ok(codeService.getCodeList());
    }
}

