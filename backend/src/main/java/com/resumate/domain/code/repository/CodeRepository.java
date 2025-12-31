package com.resumate.domain.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resumate.domain.code.entitiy.Code;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Integer> {
    List<Code> findByGroupCode(String groupCode);
}
