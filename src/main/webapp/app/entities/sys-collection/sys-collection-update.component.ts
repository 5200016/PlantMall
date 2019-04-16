import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysCollection } from 'app/shared/model/sys-collection.model';
import { SysCollectionService } from './sys-collection.service';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';
import { ISysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from 'app/entities/sys-product';

@Component({
    selector: 'jhi-sys-collection-update',
    templateUrl: './sys-collection-update.component.html'
})
export class SysCollectionUpdateComponent implements OnInit {
    sysCollection: ISysCollection;
    isSaving: boolean;

    sysusers: ISysUser[];

    sysproducts: ISysProduct[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysCollectionService: SysCollectionService,
        protected sysUserService: SysUserService,
        protected sysProductService: SysProductService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysCollection }) => {
            this.sysCollection = sysCollection;
            this.createTime = this.sysCollection.createTime != null ? this.sysCollection.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysCollection.updateTime != null ? this.sysCollection.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysusers = res.body;
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
        this.sysCollection.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysCollection.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysCollection.id !== undefined) {
            this.subscribeToSaveResponse(this.sysCollectionService.update(this.sysCollection));
        } else {
            this.subscribeToSaveResponse(this.sysCollectionService.create(this.sysCollection));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysCollection>>) {
        result.subscribe((res: HttpResponse<ISysCollection>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysUserById(index: number, item: ISysUser) {
        return item.id;
    }

    trackSysProductById(index: number, item: ISysProduct) {
        return item.id;
    }
}
