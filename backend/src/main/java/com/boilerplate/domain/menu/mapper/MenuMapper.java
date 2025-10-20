package com.boilerplate.domain.menu.mapper;

import com.boilerplate.domain.menu.dto.MenuDTO;
import com.boilerplate.domain.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuDTO.ResponseMenu> getAllMenusByUserId(Integer userId);
}
