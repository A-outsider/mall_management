package com.mall_management.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mall_management.dao.model.Department;
import com.mall_management.dto.department.addDepartReq;
import com.mall_management.dto.department.getDepartLIstReq;
import com.mall_management.dto.department.updateDepartReq;
import com.mall_management.dto.user.addUserReq;
import com.mall_management.service.impl.DepartServiceImpl;
import com.mall_management.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartServiceImpl departService;
    @PostMapping("/list")
    @SaCheckLogin
    public Res<List<Department>> getDepartList(@RequestBody @Validated getDepartLIstReq req) {
        try {
            List<Department> res = departService.getDepartList(req);
            return Res.success(res);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Res.error(500 , e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public void getDepartData(@PathVariable("id") int id) {

    }

    @PostMapping("")
    public Res addDepart(@RequestBody  @Validated addDepartReq req) {
        try {
            departService.addDepart(req);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @SaCheckLogin
    public Res updateDepart(@PathVariable("id") int id , @RequestBody @Validated updateDepartReq req) {
        try {
            departService.updateDepart(id ,req);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @SaCheckLogin
    public Res deleteDepart(@PathVariable("id") int id) {
        try {
            departService.deleteDepart(id);
            return Res.success();
        } catch (Exception e) {
            return Res.error(e.getMessage());
        }
    }

}
