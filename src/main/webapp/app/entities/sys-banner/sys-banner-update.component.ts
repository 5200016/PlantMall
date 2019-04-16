import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISysBanner } from 'app/shared/model/sys-banner.model';
import { SysBannerService } from './sys-banner.service';

@Component({
    selector: 'jhi-sys-banner-update',
    templateUrl: './sys-banner-update.component.html'
})
export class SysBannerUpdateComponent implements OnInit {
    sysBanner: ISysBanner;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(protected sysBannerService: SysBannerService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysBanner }) => {
            this.sysBanner = sysBanner;
            this.createTime = this.sysBanner.createTime != null ? this.sysBanner.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysBanner.updateTime != null ? this.sysBanner.updateTime.format(DATE_TIME_FORMAT) : null;
        });
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
}
