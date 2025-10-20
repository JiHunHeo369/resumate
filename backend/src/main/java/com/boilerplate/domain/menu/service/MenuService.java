package com.boilerplate.domain.menu.service;

import com.boilerplate.domain.menu.dto.MenuDTO;
import com.boilerplate.domain.menu.mapper.MenuMapper;
import com.boilerplate.util.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuService {

	private final JwtProvider jwtProvider;
	private final MenuMapper menuMapper;

	public List<MenuDTO.ResponseMenu> getAllMenus(HttpServletRequest request) {
		Integer userId = Integer.parseInt(jwtProvider.getUserId(request));

		return menuMapper.getAllMenusByUserId(userId);
	}
}
