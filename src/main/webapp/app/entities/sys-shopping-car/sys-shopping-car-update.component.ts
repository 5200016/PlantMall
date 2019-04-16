import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';
import { SysShoppingCarService } from './sys-shopping-car.service';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';

@Component({
    selector: 'jhi-sys-shopping-car-update',
    templateUrl: './sys-shopping-car-update.component.html'
})
export class SysShoppingCarUpdateComponent implements OnInit {
    sysShoppingCar: ISysShoppingCar;
    isSaving: boolean;

    sysproducts: ISysProduct[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysShoppingCarService: SysShoppingCarService,
        protected sysProductService: SysProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysShoppingCar }) => {
            this.sysShoppingCar = sysShoppingCar;
            this.createTime = this.sysShoppingCar.createTime != null ? this.sysShoppingCar.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysShoppingCar.updateTime != null ? this.sysShoppingCar.updateTime.format(DATE_TIME_FORMAT) : null;
        });
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
        this.sysShoppingCar.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysShoppingCar.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysShoppingCar.id !== undefined) {
            this.subscribeToSaveResponse(this.sysShoppingCarService.update(this.sysShoppingCar));
        } else {
            this.subscribeToSaveResponse(this.sysShoppingCarService.create(this.sysShoppingCar));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysShoppingCar>>) {
        result.subscribe((res: HttpResponse<ISysShoppingCar>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
