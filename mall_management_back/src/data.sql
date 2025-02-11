INSERT INTO user (id, username, realname, cellphone, enable, role_id, department_id) VALUES
                                                                                         (1, 'zhangsan', '张三', '13800138000', 0, 1, 1),
                                                                                         (2, 'lisi', '李四', '13900139001', 0, 2, 2),
                                                                                         (3, 'wangwu', '王五', '13700137002', 1, 2, 3),
                                                                                         (4, 'zhaoliu', '赵六', '13600136003', 0, 1, 4),
                                                                                         (5, 'tianqi', '田七', '13500135004', 1, 2, 5);


CREATE TABLE `department` (
                              `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `name` VARCHAR(255) NOT NULL COMMENT '部门名称',
                              `leader` VARCHAR(255) DEFAULT NULL COMMENT '部门负责人',
                              `parent_id` INT DEFAULT NULL COMMENT '父级部门ID',
                              `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

INSERT INTO department (id, name, parent_id, leader) VALUES
                                                         (1, '研发部', 0, '张三'),
                                                         (2, '市场部', 0, '李四'),
                                                         (3, '测试部', 0, '王五'),
                                                         (4, '运维部', 1, '赵六'),
                                                         (5, '后勤部', 2, '孙七');



CREATE TABLE `role` (
                        `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        `name` VARCHAR(255) NOT NULL COMMENT '角色名称',
                        `intro` VARCHAR(255) DEFAULT NULL COMMENT '角色介绍',
                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';


CREATE TABLE `menu` (
                        `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        `name` VARCHAR(255) NOT NULL COMMENT '菜单名称',
                        `url` VARCHAR(500) DEFAULT NULL COMMENT '菜单路径',
                        `type` INT NOT NULL COMMENT '菜单类型（1-一级菜单，2-二级菜单，3-三级菜单）',
                        `parent_id` INT DEFAULT NULL COMMENT '父菜单ID，NULL表示顶级菜单',
                        `sort` INT DEFAULT 0 COMMENT '排序字段',
                        `icon` VARCHAR(255) DEFAULT NULL COMMENT '一级菜单的图标',
                        `permission` VARCHAR(255) DEFAULT NULL COMMENT '权限标识，仅三级菜单设置',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';


-- 一级菜单
INSERT INTO `menu` (`id`, `name`, `url`, `type`, `parent_id`, `sort`, `icon`, `permission`) VALUES
                                                                                                (1, '系统总览', '/main/analysis', 1, NULL, 1, 'monitor', NULL),
                                                                                                (11, '系统管理', '/main/system', 1, NULL, 2, 'setting', NULL),
                                                                                                (21, '商品中心', '/main/product', 1, NULL, 3, 'goods', NULL),
                                                                                                (30, '随便聊聊', '/main/story', 1, NULL, 4, 'chat-line-round', NULL);

-- 二级菜单
INSERT INTO `menu` (`id`, `name`, `url`, `type`, `parent_id`, `sort`, `icon`, `permission`) VALUES
                                                                                                (2, '核心技术', '/main/analysis/overview', 2, 1, 1, NULL, NULL),
                                                                                                (3, '商品统计', '/main/analysis/dashboard', 2, 1, 2, NULL, NULL),
                                                                                                (12, '用户管理', '/main/system/user', 2, 11, 1, NULL, NULL),
                                                                                                (13, '部门管理', '/main/system/department', 2, 11, 2, NULL, NULL),
                                                                                                (14, '菜单管理', '/main/system/menu', 2, 11, 3, NULL, NULL),
                                                                                                (15, '角色管理', '/main/system/role', 2, 11, 4, NULL, NULL),
                                                                                                (22, '品类管理', '/main/product/category', 2, 21, 1, NULL, NULL),
                                                                                                (23, '商品管理', '/main/product/goods', 2, 21, 2, NULL, NULL),
                                                                                                (24, '品牌管理', '/main/product/brand', 2, 21, 3, NULL, NULL);

-- 三级菜单（仅为中间两个模块生成）
INSERT INTO `menu` (`id`, `name`, `url`, `type`, `parent_id`, `sort`, `icon`, `permission`) VALUES
                                                                                                (100, '添加用户', '/main/system/user/add', 3, 12, 1, NULL, 'user:add'),
                                                                                                (101, '删除用户', '/main/system/user/delete', 3, 12, 2, NULL, 'user:delete'),
                                                                                                (102, '查询用户', '/main/system/user/query', 3, 12, 3, NULL, 'user:query'),
                                                                                                (103, '添加部门', '/main/system/department/add', 3, 13, 1, NULL, 'department:add'),
                                                                                                (104, '删除部门', '/main/system/department/delete', 3, 13, 2, NULL, 'department:delete'),
                                                                                                (105, '查询部门', '/main/system/department/query', 3, 13, 3, NULL, 'department:query'),
                                                                                                (106, '添加菜单', '/main/system/menu/add', 3, 14, 1, NULL, 'menu:add'),
                                                                                                (107, '删除菜单', '/main/system/menu/delete', 3, 14, 2, NULL, 'menu:delete'),
                                                                                                (108, '查询菜单', '/main/system/menu/query', 3, 14, 3, NULL, 'menu:query'),
                                                                                                (109, '添加角色', '/main/system/role/add', 3, 15, 1, NULL, 'role:add'),
                                                                                                (110, '删除角色', '/main/system/role/delete', 3, 15, 2, NULL, 'role:delete'),
                                                                                                (111, '查询角色', '/main/system/role/query', 3, 15, 3, NULL, 'role:query'),
                                                                                                (200, '添加品类', '/main/product/category/add', 3, 22, 1, NULL, 'category:add'),
                                                                                                (201, '删除品类', '/main/product/category/delete', 3, 22, 2, NULL, 'category:delete'),
                                                                                                (202, '查询品类', '/main/product/category/query', 3, 22, 3, NULL, 'category:query'),
                                                                                                (203, '添加商品', '/main/product/goods/add', 3, 23, 1, NULL, 'goods:add'),
                                                                                                (204, '删除商品', '/main/product/goods/delete', 3, 23, 2, NULL, 'goods:delete'),
                                                                                                (205, '查询商品', '/main/product/goods/query', 3, 23, 3, NULL, 'goods:query'),
                                                                                                (206, '添加品牌', '/main/product/brand/add', 3, 24, 1, NULL, 'brand:add'),
                                                                                                (207, '删除品牌', '/main/product/brand/delete', 3, 24, 2, NULL, 'brand:delete'),
                                                                                                (208, '查询品牌', '/main/product/brand/query', 3, 24, 3, NULL, 'brand:query');



CREATE TABLE `role_menu` (
                             `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                             `role_id` INT NOT NULL COMMENT '角色ID',
                             `menu_id` INT NOT NULL COMMENT '菜单ID',
                             PRIMARY KEY (`id`),
                             KEY `idx_role_id` (`role_id`),
                             KEY `idx_menu_id` (`menu_id`),
                             CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `fk_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-菜单关联表';


-- 添加一级目录
INSERT INTO role_menu (role_id, menu_id) VALUES
                                             (2, 1),  -- 系统总览
                                             (2, 11), -- 系统管理
                                             (2, 21); -- 商品中心

-- 添加二级目录
INSERT INTO role_menu (role_id, menu_id) VALUES
                                             (2, 2),  -- 核心技术
                                             (2, 12), -- 用户管理
                                             (2, 22); -- 品类管理

-- 添加三级目录
INSERT INTO role_menu (role_id, menu_id) VALUES
                                             (2, 100), -- 添加用户
                                             (2, 200), -- 添加品类
                                             (2, 203); -- 添加商品
