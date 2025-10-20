package com.boilerplate.domain.menu.dto;

import lombok.Data;

public class MenuDTO {

    @Data
    public static class ResponseMenu {
        private Integer id;
        private Integer upMenuId;
        private String name;
        private String path;
        private Integer sortOrder;
        private String namePath;
        private Integer depth;
        private String icon;
    }
}
