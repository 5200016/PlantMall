import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysModule } from 'app/shared/model/sys-module.model';
import { SysModuleService } from './sys-module.service';
import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { SysClassifyService } from 'app/entities/sys-classify';

@Component({
    selector: 'jhi-sys-module-update',
    templateUrl: './sys-module-update.component.html'
})
export class SysModuleUpdateComponent implements OnInit {
    sysModule: ISysModule;
    isSaving: boolean;

    sysclassifies: ISysClassify[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysModuleService: SysModuleService,
        protected sysClassifyService: SysClassifyService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysModule }) => {
            this.sysModule = sysModule;
            this.createTime = this.sysModule.createTime != null ? this.sysModule.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysModule.updateTime != null ? this.sysModule.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysClassifyService.query().subscribe(
            (res: HttpResponse<ISysClassify[]>) => {
                this.sysclassifies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysModule.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysModule.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysModule.id !== undefined) {
            this.subscribeToSaveResponse(this.sysModuleService.update(this.sysModule));
        } else {
            this.subscribeToSaveResponse(this.sysModuleService.create(this.sysModule));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysModule>>) {
        result.subscribe((res: HttpResponse<ISysModule>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
