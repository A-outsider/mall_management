package com.mall_management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall_management.dao.mapper.DepartmentMapper;
import com.mall_management.dao.model.Department;
import com.mall_management.dto.department.addDepartReq;
import com.mall_management.dto.department.getDepartLIstReq;
import com.mall_management.dto.department.updateDepartReq;
import com.mall_management.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartmentMapper departMapper;
    @Override
    public List<Department> getDepartList(getDepartLIstReq req) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<Department>();

        // 动态查询
        if (req.getName() != null && !req.getName().isEmpty()) {
            queryWrapper.eq("name", req.getName());
        }

        if (req.getLeader() != null && !req.getLeader().isEmpty()) {
            queryWrapper.eq("leader", req.getLeader());
        }


        return departMapper.selectList(queryWrapper);

    }

    @Override
    public void getDepartData(int id) {

    }

    @Override
    public void addDepart( addDepartReq req) {

        Department depart = new Department(req.getName(), req.getLeader(), req.getParentId());
        departMapper.insert(depart);
    }

    @Override
    public void updateDepart(int id, updateDepartReq req) {
         Department existingDepart =  departMapper.selectById(id);
         if(req.getName() != null) existingDepart.setName(req.getName());
         if(req.getLeader() != null) existingDepart.setLeader(req.getLeader());
         if(req.getParentId() != null) existingDepart.setParentId(req.getParentId());

         departMapper.updateById(existingDepart);
    }

    @Override
    public void deleteDepart(int id) {
        departMapper.deleteById(id);
    }
}
