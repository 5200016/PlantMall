import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';
import { SysReceiverAddressService } from './sys-receiver-address.service';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';

@Component({
    selector: 'jhi-sys-receiver-address-update',
    templateUrl: './sys-receiver-address-update.component.html'
})
export class SysReceiverAddressUpdateComponent implements OnInit {
    sysReceiverAddress: ISysReceiverAddress;
    isSaving: boolean;

    sysusers: ISysUser[];
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected sysReceiverAddressService: SysReceiverAddressService,
        protected sysUserService: SysUserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysReceiverAddress }) => {
            this.sysReceiverAddress = sysReceiverAddress;
            this.createTime =
                this.sysReceiverAddress.createTime != null ? this.sysReceiverAddress.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.sysReceiverAddress.updateTime != null ? this.sysReceiverAddress.updateTime.format(DATE_TIME_FORMAT) : null;
        });
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
        this.sysReceiverAddress.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysReceiverAddress.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysReceiverAddress.id !== undefined) {
            this.subscribeToSaveResponse(this.sysReceiverAddressService.update(this.sysReceiverAddress));
        } else {
            this.subscribeToSaveResponse(this.sysReceiverAddressService.create(this.sysReceiverAddress));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysReceiverAddress>>) {
        result.subscribe((res: HttpResponse<ISysReceiverAddress>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
