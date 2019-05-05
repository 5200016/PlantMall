import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysOrder } from 'app/shared/model/sys-order.model';
import { SysOrderService } from './sys-order.service';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';
import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';
import { SysReceiverAddressService } from 'app/entities/sys-receiver-address';
import { ISysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';
import { SysMaintenancePersonnelService } from 'app/entities/sys-maintenance-personnel';

@Component({
    selector: 'jhi-sys-order-update',
    templateUrl: './sys-order-update.component.html'
})
export class SysOrderUpdateComponent implements OnInit {
    sysOrder: ISysOrder;
    isSaving: boolean;

    sysusers: ISysUser[];

    sysreceiveraddresses: ISysReceiverAddress[];

    sysmaintenancepersonnels: ISysMaintenancePersonnel[];
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected sysOrderService: SysOrderService,
        protected sysUserService: SysUserService,
        protected sysReceiverAddressService: SysReceiverAddressService,
        protected sysMaintenancePersonnelService: SysMaintenancePersonnelService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysOrder }) => {
            this.sysOrder = sysOrder;
            this.createTime = this.sysOrder.createTime != null ? this.sysOrder.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysOrder.updateTime != null ? this.sysOrder.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysReceiverAddressService.query().subscribe(
            (res: HttpResponse<ISysReceiverAddress[]>) => {
                this.sysreceiveraddresses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysMaintenancePersonnelService.query().subscribe(
            (res: HttpResponse<ISysMaintenancePersonnel[]>) => {
                this.sysmaintenancepersonnels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysOrder.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysOrder.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysOrder.id !== undefined) {
            this.subscribeToSaveResponse(this.sysOrderService.update(this.sysOrder));
        } else {
            this.subscribeToSaveResponse(this.sysOrderService.create(this.sysOrder));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysOrder>>) {
        result.subscribe((res: HttpResponse<ISysOrder>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysReceiverAddressById(index: number, item: ISysReceiverAddress) {
        return item.id;
    }

    trackSysMaintenancePersonnelById(index: number, item: ISysMaintenancePersonnel) {
        return item.id;
    }
}
