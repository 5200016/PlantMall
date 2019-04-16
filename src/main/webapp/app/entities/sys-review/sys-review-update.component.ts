import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysReview } from 'app/shared/model/sys-review.model';
import { SysReviewService } from './sys-review.service';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';

@Component({
    selector: 'jhi-sys-review-update',
    templateUrl: './sys-review-update.component.html'
})
export class SysReviewUpdateComponent implements OnInit {
    sysReview: ISysReview;
    isSaving: boolean;

    sysproducts: ISysProduct[];
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected sysReviewService: SysReviewService,
        protected sysProductService: SysProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysReview }) => {
            this.sysReview = sysReview;
            this.createTime = this.sysReview.createTime != null ? this.sysReview.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysReview.updateTime != null ? this.sysReview.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysProductService.query().subscribe(
            (res: HttpResponse<ISysProduct[]>) => {
                this.sysproducts = res.body;
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
        this.sysReview.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysReview.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysReview.id !== undefined) {
            this.subscribeToSaveResponse(this.sysReviewService.update(this.sysReview));
        } else {
            this.subscribeToSaveResponse(this.sysReviewService.create(this.sysReview));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysReview>>) {
        result.subscribe((res: HttpResponse<ISysReview>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
