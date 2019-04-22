import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from './sys-product.service';
import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from 'app/entities/sys-classify';

@Component({
    selector: 'jhi-sys-product-update',
    templateUrl: './sys-product-update.component.html'
})
export class SysProductUpdateComponent implements OnInit {
    sysProduct: ISysProduct;
    isSaving: boolean;

    sysclassifies: ISysClassify[];
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected sysProductService: SysProductService,
        protected sysClassifyService: SysClassifyService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysProduct }) => {
            this.sysProduct = sysProduct;
            this.createTime = this.sysProduct.createTime != null ? this.sysProduct.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysProduct.updateTime != null ? this.sysProduct.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysClassifyService.query().subscribe(
            (res: HttpResponse<ISysClassify[]>) => {
                this.sysclassifies = res.body;
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
        this.sysProduct.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysProduct.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysProduct.id !== undefined) {
            this.subscribeToSaveResponse(this.sysProductService.update(this.sysProduct));
        } else {
            this.subscribeToSaveResponse(this.sysProductService.create(this.sysProduct));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysProduct>>) {
        result.subscribe((res: HttpResponse<ISysProduct>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysClassifyById(index: number, item: ISysClassify) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
