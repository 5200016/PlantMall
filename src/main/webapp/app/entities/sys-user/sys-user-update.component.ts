import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from './sys-user.service';
import { ISysMemberLevel } from 'app/shared/model/sys-member-level.model';
import { SysMemberLevelService } from 'app/entities/sys-member-level';
import { ISysRole } from 'app/shared/model/sys-role.model';
import { SysRoleService } from 'app/entities/sys-role';

@Component({
    selector: 'jhi-sys-user-update',
    templateUrl: './sys-user-update.component.html'
})
export class SysUserUpdateComponent implements OnInit {
    sysUser: ISysUser;
    isSaving: boolean;

    sysmemberlevels: ISysMemberLevel[];

    sysroles: ISysRole[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysUserService: SysUserService,
        protected sysMemberLevelService: SysMemberLevelService,
        protected sysRoleService: SysRoleService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysUser }) => {
            this.sysUser = sysUser;
            this.createTime = this.sysUser.createTime != null ? this.sysUser.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysUser.updateTime != null ? this.sysUser.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysMemberLevelService.query().subscribe(
            (res: HttpResponse<ISysMemberLevel[]>) => {
                this.sysmemberlevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysRoleService.query().subscribe(
            (res: HttpResponse<ISysRole[]>) => {
                this.sysroles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysUser.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysUser.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysUser.id !== undefined) {
            this.subscribeToSaveResponse(this.sysUserService.update(this.sysUser));
        } else {
            this.subscribeToSaveResponse(this.sysUserService.create(this.sysUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysUser>>) {
        result.subscribe((res: HttpResponse<ISysUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSysMemberLevelById(index: number, item: ISysMemberLevel) {
        return item.id;
    }

    trackSysRoleById(index: number, item: ISysRole) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
