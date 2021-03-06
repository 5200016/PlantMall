import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';
import { SysMaintenancePersonnelService } from './sys-maintenance-personnel.service';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';

@Component({
    selector: 'jhi-sys-maintenance-personnel-update',
    templateUrl: './sys-maintenance-personnel-update.component.html'
})
export class SysMaintenancePersonnelUpdateComponent implements OnInit {
    sysMaintenancePersonnel: ISysMaintenancePersonnel;
    isSaving: boolean;

    sysusers: ISysUser[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysMaintenancePersonnelService: SysMaintenancePersonnelService,
        protected sysUserService: SysUserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysMaintenancePersonnel }) => {
            this.sysMaintenancePersonnel = sysMaintenancePersonnel;
            this.createTime =
                this.sysMaintenancePersonnel.createTime != null ? this.sysMaintenancePersonnel.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.sysMaintenancePersonnel.updateTime != null ? this.sysMaintenancePersonnel.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysMaintenancePersonnel.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysMaintenancePersonnel.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysMaintenancePersonnel.id !== undefined) {
            this.subscribeToSaveResponse(this.sysMaintenancePersonnelService.update(this.sysMaintenancePersonnel));
        } else {
            this.subscribeToSaveResponse(this.sysMaintenancePersonnelService.create(this.sysMaintenancePersonnel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysMaintenancePersonnel>>) {
        result.subscribe(
            (res: HttpResponse<ISysMaintenancePersonnel>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackSysUserById(index: number, item: ISysUser) {
        return item.id;
    }
}
