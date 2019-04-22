import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysProductImage } from 'app/shared/model/sys-product-image.model';
import { SysProductImageService } from './sys-product-image.service';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';

@Component({
    selector: 'jhi-sys-product-image-update',
    templateUrl: './sys-product-image-update.component.html'
})
export class SysProductImageUpdateComponent implements OnInit {
    sysProductImage: ISysProductImage;
    isSaving: boolean;

    sysproducts: ISysProduct[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysProductImageService: SysProductImageService,
        protected sysProductService: SysProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysProductImage }) => {
            this.sysProductImage = sysProductImage;
            this.createTime = this.sysProductImage.createTime != null ? this.sysProductImage.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysProductImage.updateTime != null ? this.sysProductImage.updateTime.format(DATE_TIME_FORMAT) : null;
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
        this.sysProductImage.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysProductImage.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysProductImage.id !== undefined) {
            this.subscribeToSaveResponse(this.sysProductImageService.update(this.sysProductImage));
        } else {
            this.subscribeToSaveResponse(this.sysProductImageService.create(this.sysProductImage));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysProductImage>>) {
        result.subscribe((res: HttpResponse<ISysProductImage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
