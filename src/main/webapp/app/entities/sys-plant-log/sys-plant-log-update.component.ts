import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysPlantLog } from 'app/shared/model/sys-plant-log.model';
import { SysPlantLogService } from './sys-plant-log.service';

@Component({
    selector: 'jhi-sys-plant-log-update',
    templateUrl: './sys-plant-log-update.component.html'
})
export class SysPlantLogUpdateComponent implements OnInit {
    sysPlantLog: ISysPlantLog;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected sysPlantLogService: SysPlantLogService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysPlantLog }) => {
            this.sysPlantLog = sysPlantLog;
            this.createTime = this.sysPlantLog.createTime != null ? this.sysPlantLog.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysPlantLog.updateTime != null ? this.sysPlantLog.updateTime.format(DATE_TIME_FORMAT) : null;
        });
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
        this.sysPlantLog.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysPlantLog.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysPlantLog.id !== undefined) {
            this.subscribeToSaveResponse(this.sysPlantLogService.update(this.sysPlantLog));
        } else {
            this.subscribeToSaveResponse(this.sysPlantLogService.create(this.sysPlantLog));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysPlantLog>>) {
        result.subscribe((res: HttpResponse<ISysPlantLog>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
