import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';
import { SysMaintenanceFinishService } from './sys-maintenance-finish.service';
import { ISysOrder } from 'app/shared/model/sys-order.model';
import { SysOrderService } from 'app/entities/sys-order';

@Component({
    selector: 'jhi-sys-maintenance-finish-update',
    templateUrl: './sys-maintenance-finish-update.component.html'
})
export class SysMaintenanceFinishUpdateComponent implements OnInit {
    sysMaintenanceFinish: ISysMaintenanceFinish;
    isSaving: boolean;

    sysorders: ISysOrder[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysMaintenanceFinishService: SysMaintenanceFinishService,
        protected sysOrderService: SysOrderService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysMaintenanceFinish }) => {
            this.sysMaintenanceFinish = sysMaintenanceFinish;
            this.createTime =
                this.sysMaintenanceFinish.createTime != null ? this.sysMaintenanceFinish.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.sysMaintenanceFinish.updateTime != null ? this.sysMaintenanceFinish.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysOrderService.query().subscribe(
            (res: HttpResponse<ISysOrder[]>) => {
                this.sysorders = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysMaintenanceFinish.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysMaintenanceFinish.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysMaintenanceFinish.id !== undefined) {
            this.subscribeToSaveResponse(this.sysMaintenanceFinishService.update(this.sysMaintenanceFinish));
        } else {
            this.subscribeToSaveResponse(this.sysMaintenanceFinishService.create(this.sysMaintenanceFinish));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysMaintenanceFinish>>) {
        result.subscribe(
            (res: HttpResponse<ISysMaintenanceFinish>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackSysOrderById(index: number, item: ISysOrder) {
        return item.id;
    }
}
