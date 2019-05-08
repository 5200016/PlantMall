import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysCoupon } from 'app/shared/model/sys-coupon.model';
import { SysCouponService } from './sys-coupon.service';

@Component({
    selector: 'jhi-sys-coupon-update',
    templateUrl: './sys-coupon-update.component.html'
})
export class SysCouponUpdateComponent implements OnInit {
    sysCoupon: ISysCoupon;
    isSaving: boolean;
    startTime: string;
    endTime: string;
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected sysCouponService: SysCouponService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysCoupon }) => {
            this.sysCoupon = sysCoupon;
            this.startTime = this.sysCoupon.startTime != null ? this.sysCoupon.startTime.format(DATE_TIME_FORMAT) : null;
            this.endTime = this.sysCoupon.endTime != null ? this.sysCoupon.endTime.format(DATE_TIME_FORMAT) : null;
            this.createTime = this.sysCoupon.createTime != null ? this.sysCoupon.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysCoupon.updateTime != null ? this.sysCoupon.updateTime.format(DATE_TIME_FORMAT) : null;
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
        this.sysCoupon.startTime = this.startTime != null ? moment(this.startTime, DATE_TIME_FORMAT) : null;
        this.sysCoupon.endTime = this.endTime != null ? moment(this.endTime, DATE_TIME_FORMAT) : null;
        this.sysCoupon.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysCoupon.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysCoupon.id !== undefined) {
            this.subscribeToSaveResponse(this.sysCouponService.update(this.sysCoupon));
        } else {
            this.subscribeToSaveResponse(this.sysCouponService.create(this.sysCoupon));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysCoupon>>) {
        result.subscribe((res: HttpResponse<ISysCoupon>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
