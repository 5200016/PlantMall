package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.SysForm;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.*;
import com.ybb.mall.service.wx.WXFormService;
import com.ybb.mall.web.rest.controller.wx.vm.WXFormVM;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description : 微信小程序-微信formId管理
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

@Service
@Transactional
public class WXFormServiceImpl implements WXFormService {

    private final FormRepository formRepository;

    public WXFormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public ResultObj insertForm(WXFormVM wxFormVM) {
        if(!TypeUtils.isEmpty(wxFormVM.getFormId()) && !"the formId is a mock one".equals(wxFormVM.getFormId())){
            SysForm form = new SysForm();
            form.setFormId(wxFormVM.getFormId());
            form.setValidity(6);

            SysUser user = new SysUser();
            user.setId(wxFormVM.getUserId());
            form.setUser(user);

            formRepository.save(form);
            return ResultObj.backCRUDSuccess("新增成功");
        }else {
            return ResultObj.backCRUDError("新增失败");
        }

    }
}
