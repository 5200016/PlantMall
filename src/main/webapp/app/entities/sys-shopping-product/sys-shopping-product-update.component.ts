import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';
import { SysShoppingProductService } from './sys-shopping-product.service';
import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';
import { SysShoppingCarService } from 'app/entities/sys-shopping-car';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';

@Component({
    selector: 'jhi-sys-shopping-product-update',
    templateUrl: './sys-shopping-product-update.component.html'
})
export class SysShoppingProductUpdateComponent implements OnInit {
    sysShoppingProduct: ISysShoppingProduct;
    isSaving: boolean;

    sysshoppingcars: ISysShoppingCar[];

    sysproducts: ISysProduct[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysShoppingProductService: SysShoppingProductService,
        protected sysShoppingCarService: SysShoppingCarService,
        protected sysProductService: SysProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysShoppingProduct }) => {
            this.sysShoppingProduct = sysShoppingProduct;
            this.createTime =
                this.sysShoppingProduct.createTime != null ? this.sysShoppingProduct.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.sysShoppingProduct.updateTime != null ? this.sysShoppingProduct.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysShoppingCarService.query().subscribe(
            (res: HttpResponse<ISysShoppingCar[]>) => {
                this.sysshoppingcars = res.body;
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
        this.sysShoppingProduct.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysShoppingProduct.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysShoppingProduct.id !== undefined) {
            this.subscribeToSaveResponse(this.sysShoppingProductService.update(this.sysShoppingProduct));
        } else {
            this.subscribeToSaveResponse(this.sysShoppingProductService.create(this.sysShoppingProduct));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysShoppingProduct>>) {
        result.subscribe((res: HttpResponse<ISysShoppingProduct>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysShoppingCarById(index: number, item: ISysShoppingCar) {
        return item.id;
    }

    trackSysProductById(index: number, item: ISysProduct) {
        return item.id;
    }
}
