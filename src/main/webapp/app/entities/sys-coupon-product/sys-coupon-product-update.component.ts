import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysCouponProduct } from 'app/shared/model/sys-coupon-product.model';
import { SysCouponProductService } from './sys-coupon-product.service';
import { ISysCoupon } from 'app/shared/model/sys-coupon.model';
import { SysCouponService } from 'app/entities/sys-coupon';

@Component({
    selector: 'jhi-sys-coupon-product-update',
    templateUrl: './sys-coupon-product-update.component.html'
})
export class SysCouponProductUpdateComponent implements OnInit {
    sysCouponProduct: ISysCouponProduct;
    isSaving: boolean;

    syscoupons: ISysCoupon[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysCouponProductService: SysCouponProductService,
        protected sysCouponService: SysCouponService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysCouponProduct }) => {
            this.sysCouponProduct = sysCouponProduct;
            this.createTime = this.sysCouponProduct.createTime != null ? this.sysCouponProduct.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysCouponProduct.updateTime != null ? this.sysCouponProduct.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysCouponService.query().subscribe(
            (res: HttpResponse<ISysCoupon[]>) => {
                this.syscoupons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysCouponProduct.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysCouponProduct.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysCouponProduct.id !== undefined) {
            this.subscribeToSaveResponse(this.sysCouponProductService.update(this.sysCouponProduct));
        } else {
            this.subscribeToSaveResponse(this.sysCouponProductService.create(this.sysCouponProduct));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysCouponProduct>>) {
        result.subscribe((res: HttpResponse<ISysCouponProduct>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
