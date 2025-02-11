package com.mall_management.service;

import com.mall_management.dao.model.Department;
import com.mall_management.dto.department.addDepartReq;
import com.mall_management.dto.department.getDepartLIstReq;
import com.mall_management.dto.department.updateDepartReq;

import java.util.List;

public interface DepartService {

    List<Department> getDepartList(getDepartLIstReq req);


    void getDepartData(int id);


    void addDepart( addDepartReq req);



    void updateDepart(int id, updateDepartReq req);

    void deleteDepart(int id);
}
