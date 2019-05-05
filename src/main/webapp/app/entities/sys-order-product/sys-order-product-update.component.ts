import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysOrderProduct } from 'app/shared/model/sys-order-product.model';
import { SysOrderProductService } from './sys-order-product.service';
import { ISysOrder } from 'app/shared/model/sys-order.model';
import { SysOrderService } from 'app/entities/sys-order';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';

@Component({
    selector: 'jhi-sys-order-product-update',
    templateUrl: './sys-order-product-update.component.html'
})
export class SysOrderProductUpdateComponent implements OnInit {
    sysOrderProduct: ISysOrderProduct;
    isSaving: boolean;

    sysorders: ISysOrder[];

    sysproducts: ISysProduct[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysOrderProductService: SysOrderProductService,
        protected sysOrderService: SysOrderService,
        protected sysProductService: SysProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysOrderProduct }) => {
            this.sysOrderProduct = sysOrderProduct;
            this.createTime = this.sysOrderProduct.createTime != null ? this.sysOrderProduct.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysOrderProduct.updateTime != null ? this.sysOrderProduct.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysOrderService.query().subscribe(
            (res: HttpResponse<ISysOrder[]>) => {
                this.sysorders = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysProductService.query().subscribe(
            (res: HttpResponse<ISysProduct[]>) => {
                this.sysproducts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysOrderProduct.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysOrderProduct.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysOrderProduct.id !== undefined) {
            this.subscribeToSaveResponse(this.sysOrderProductService.update(this.sysOrderProduct));
        } else {
            this.subscribeToSaveResponse(this.sysOrderProductService.create(this.sysOrderProduct));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysOrderProduct>>) {
        result.subscribe((res: HttpResponse<ISysOrderProduct>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysOrderById(index: number, item: ISysOrder) {
        return item.id;
    }

    trackSysProductById(index: number, item: ISysProduct) {
        return item.id;
    }
}
