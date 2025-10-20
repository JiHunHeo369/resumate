package com.boilerplate.domain.code.controller;

import com.boilerplate.domain.code.dto.CodeDTO;
import com.boilerplate.domain.code.dto.CodeGroupDTO;
import com.boilerplate.domain.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/codes")
@Slf4j
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/{groupCode}")
    public ResponseEntity<List<CodeDTO.ResponseCode>> getCodesByGroupCode(@PathVariable String groupCode) {
        return ResponseEntity.ok(codeService.getCodesByGroupCode(groupCode));
    }

    @GetMapping("/group-codes/")
    public ResponseEntity<List<CodeGroupDTO.ResponseCodeGroup>> getAllCodeGroups() {
        return ResponseEntity.ok(codeService.getAllCodeGroups());
    }
}
