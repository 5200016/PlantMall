import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysBanner } from 'app/shared/model/sys-banner.model';
import { SysBannerService } from './sys-banner.service';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';
import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from 'app/entities/sys-classify';

@Component({
    selector: 'jhi-sys-banner-update',
    templateUrl: './sys-banner-update.component.html'
})
export class SysBannerUpdateComponent implements OnInit {
    sysBanner: ISysBanner;
    isSaving: boolean;

    sysproducts: ISysProduct[];

    sysclassifies: ISysClassify[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysBannerService: SysBannerService,
        protected sysProductService: SysProductService,
        protected sysClassifyService: SysClassifyService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysBanner }) => {
            this.sysBanner = sysBanner;
            this.createTime = this.sysBanner.createTime != null ? this.sysBanner.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysBanner.updateTime != null ? this.sysBanner.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysProductService.query().subscribe(
            (res: HttpResponse<ISysProduct[]>) => {
                this.sysproducts = res.body;
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
        this.sysBanner.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysBanner.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysBanner.id !== undefined) {
            this.subscribeToSaveResponse(this.sysBannerService.update(this.sysBanner));
        } else {
            this.subscribeToSaveResponse(this.sysBannerService.create(this.sysBanner));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysBanner>>) {
        result.subscribe((res: HttpResponse<ISysBanner>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysProductById(index: number, item: ISysProduct) {
        return item.id;
    }

    trackSysClassifyById(index: number, item: ISysClassify) {
        return item.id;
    }
}
