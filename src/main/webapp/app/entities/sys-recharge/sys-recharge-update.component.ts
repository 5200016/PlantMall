import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISysRecharge } from 'app/shared/model/sys-recharge.model';
import { SysRechargeService } from './sys-recharge.service';

@Component({
    selector: 'jhi-sys-recharge-update',
    templateUrl: './sys-recharge-update.component.html'
})
export class SysRechargeUpdateComponent implements OnInit {
    sysRecharge: ISysRecharge;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(protected sysRechargeService: SysRechargeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysRecharge }) => {
            this.sysRecharge = sysRecharge;
            this.createTime = this.sysRecharge.createTime != null ? this.sysRecharge.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysRecharge.updateTime != null ? this.sysRecharge.updateTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysRecharge.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysRecharge.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysRecharge.id !== undefined) {
            this.subscribeToSaveResponse(this.sysRechargeService.update(this.sysRecharge));
        } else {
            this.subscribeToSaveResponse(this.sysRechargeService.create(this.sysRecharge));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysRecharge>>) {
        result.subscribe((res: HttpResponse<ISysRecharge>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
