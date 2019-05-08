import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';
import { SysCouponClassifyService } from './sys-coupon-classify.service';
import { ISysCoupon } from 'app/shared/model/sys-coupon.model';
import { SysCouponService } from 'app/entities/sys-coupon';
import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from 'app/entities/sys-classify';

@Component({
    selector: 'jhi-sys-coupon-classify-update',
    templateUrl: './sys-coupon-classify-update.component.html'
})
export class SysCouponClassifyUpdateComponent implements OnInit {
    sysCouponClassify: ISysCouponClassify;
    isSaving: boolean;

    syscoupons: ISysCoupon[];

    sysclassifies: ISysClassify[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysCouponClassifyService: SysCouponClassifyService,
        protected sysCouponService: SysCouponService,
        protected sysClassifyService: SysClassifyService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysCouponClassify }) => {
            this.sysCouponClassify = sysCouponClassify;
            this.createTime = this.sysCouponClassify.createTime != null ? this.sysCouponClassify.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysCouponClassify.updateTime != null ? this.sysCouponClassify.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysCouponService.query().subscribe(
            (res: HttpResponse<ISysCoupon[]>) => {
                this.syscoupons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysClassifyService.query().subscribe(
            (res: HttpResponse<ISysClassify[]>) => {
                this.sysclassifies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysCouponClassify.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysCouponClassify.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysCouponClassify.id !== undefined) {
            this.subscribeToSaveResponse(this.sysCouponClassifyService.update(this.sysCouponClassify));
        } else {
            this.subscribeToSaveResponse(this.sysCouponClassifyService.create(this.sysCouponClassify));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysCouponClassify>>) {
        result.subscribe((res: HttpResponse<ISysCouponClassify>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysCouponById(index: number, item: ISysCoupon) {
        return item.id;
    }

    trackSysClassifyById(index: number, item: ISysClassify) {
        return item.id;
    }
}
