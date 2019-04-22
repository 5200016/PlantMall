import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from './sys-classify.service';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';

@Component({
    selector: 'jhi-sys-classify-update',
    templateUrl: './sys-classify-update.component.html'
})
export class SysClassifyUpdateComponent implements OnInit {
    sysClassify: ISysClassify;
    isSaving: boolean;

    sysproducts: ISysProduct[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysClassifyService: SysClassifyService,
        protected sysProductService: SysProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysClassify }) => {
            this.sysClassify = sysClassify;
            this.createTime = this.sysClassify.createTime != null ? this.sysClassify.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysClassify.updateTime != null ? this.sysClassify.updateTime.format(DATE_TIME_FORMAT) : null;
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
        this.sysClassify.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysClassify.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysClassify.id !== undefined) {
            this.subscribeToSaveResponse(this.sysClassifyService.update(this.sysClassify));
        } else {
            this.subscribeToSaveResponse(this.sysClassifyService.create(this.sysClassify));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysClassify>>) {
        result.subscribe((res: HttpResponse<ISysClassify>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
