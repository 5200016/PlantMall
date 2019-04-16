import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from './sys-classify.service';

@Component({
    selector: 'jhi-sys-classify-update',
    templateUrl: './sys-classify-update.component.html'
})
export class SysClassifyUpdateComponent implements OnInit {
    sysClassify: ISysClassify;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(protected sysClassifyService: SysClassifyService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysClassify }) => {
            this.sysClassify = sysClassify;
            this.createTime = this.sysClassify.createTime != null ? this.sysClassify.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysClassify.updateTime != null ? this.sysClassify.updateTime.format(DATE_TIME_FORMAT) : null;
        });
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
}
