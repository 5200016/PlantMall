import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysAppointment } from 'app/shared/model/sys-appointment.model';
import { SysAppointmentService } from './sys-appointment.service';
import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';
import { SysReceiverAddressService } from 'app/entities/sys-receiver-address';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';

@Component({
    selector: 'jhi-sys-appointment-update',
    templateUrl: './sys-appointment-update.component.html'
})
export class SysAppointmentUpdateComponent implements OnInit {
    sysAppointment: ISysAppointment;
    isSaving: boolean;

    sysreceiveraddresses: ISysReceiverAddress[];

    sysusers: ISysUser[];
    time: string;
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected sysAppointmentService: SysAppointmentService,
        protected sysReceiverAddressService: SysReceiverAddressService,
        protected sysUserService: SysUserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysAppointment }) => {
            this.sysAppointment = sysAppointment;
            this.time = this.sysAppointment.time != null ? this.sysAppointment.time.format(DATE_TIME_FORMAT) : null;
            this.createTime = this.sysAppointment.createTime != null ? this.sysAppointment.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysAppointment.updateTime != null ? this.sysAppointment.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysReceiverAddressService.query().subscribe(
            (res: HttpResponse<ISysReceiverAddress[]>) => {
                this.sysreceiveraddresses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysusers = res.body;
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
        this.sysAppointment.time = this.time != null ? moment(this.time, DATE_TIME_FORMAT) : null;
        this.sysAppointment.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysAppointment.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysAppointment.id !== undefined) {
            this.subscribeToSaveResponse(this.sysAppointmentService.update(this.sysAppointment));
        } else {
            this.subscribeToSaveResponse(this.sysAppointmentService.create(this.sysAppointment));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysAppointment>>) {
        result.subscribe((res: HttpResponse<ISysAppointment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysReceiverAddressById(index: number, item: ISysReceiverAddress) {
        return item.id;
    }

    trackSysUserById(index: number, item: ISysUser) {
        return item.id;
    }
}
