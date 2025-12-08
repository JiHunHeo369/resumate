package com.resumate.domain.resume.controller;


import com.resumate.domain.resume.dto.ResumeDTO;
import com.resumate.domain.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "이력서 API", description = "이력서 crud")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping("/")
    public ResponseEntity<List<ResumeDTO.ResumeListResponse>> getResumeList(@ModelAttribute ResumeDTO.ResumeListRequest request) {
        return ResponseEntity.ok(resumeService.getResumeList(request));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeDTO.ResumeListResponse> getResume(@PathVariable("resumeId") Integer resumeId) {
        return ResponseEntity.ok(resumeService.getResume(resumeId));
    }

    @PostMapping("/")
    public ResponseEntity<Integer> createResume(@RequestBody ResumeDTO.ResumeCreateRequest request) {
        return ResponseEntity.ok(resumeService.createResume(request));
    }
}
