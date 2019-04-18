import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysCustomerService } from 'app/shared/model/sys-customer-service.model';
import { SysCustomerServiceService } from './sys-customer-service.service';

@Component({
    selector: 'jhi-sys-customer-service-update',
    templateUrl: './sys-customer-service-update.component.html'
})
export class SysCustomerServiceUpdateComponent implements OnInit {
    sysCustomerService: ISysCustomerService;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected sysCustomerServiceService: SysCustomerServiceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysCustomerService }) => {
            this.sysCustomerService = sysCustomerService;
            this.createTime =
                this.sysCustomerService.createTime != null ? this.sysCustomerService.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.sysCustomerService.updateTime != null ? this.sysCustomerService.updateTime.format(DATE_TIME_FORMAT) : null;
        });
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
        this.sysCustomerService.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysCustomerService.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysCustomerService.id !== undefined) {
            this.subscribeToSaveResponse(this.sysCustomerServiceService.update(this.sysCustomerService));
        } else {
            this.subscribeToSaveResponse(this.sysCustomerServiceService.create(this.sysCustomerService));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysCustomerService>>) {
        result.subscribe((res: HttpResponse<ISysCustomerService>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
