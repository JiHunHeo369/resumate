package com.boilerplate.domain.menu.controller;

import com.boilerplate.domain.menu.dto.MenuDTO;
import com.boilerplate.domain.menu.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

	private final MenuService menuService;

	@GetMapping("/")
	public ResponseEntity<List<MenuDTO.ResponseMenu>> getAllMenus(HttpServletRequest request) {
		return ResponseEntity.ok(menuService.getAllMenus(request));
	}

}
