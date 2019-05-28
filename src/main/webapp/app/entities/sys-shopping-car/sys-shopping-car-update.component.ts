import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';
import { SysShoppingCarService } from './sys-shopping-car.service';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';

@Component({
    selector: 'jhi-sys-shopping-car-update',
    templateUrl: './sys-shopping-car-update.component.html'
})
export class SysShoppingCarUpdateComponent implements OnInit {
    sysShoppingCar: ISysShoppingCar;
    isSaving: boolean;

    sysusers: ISysUser[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysShoppingCarService: SysShoppingCarService,
        protected sysUserService: SysUserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysShoppingCar }) => {
            this.sysShoppingCar = sysShoppingCar;
            this.createTime = this.sysShoppingCar.createTime != null ? this.sysShoppingCar.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysShoppingCar.updateTime != null ? this.sysShoppingCar.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysShoppingCar.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysShoppingCar.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysShoppingCar.id !== undefined) {
            this.subscribeToSaveResponse(this.sysShoppingCarService.update(this.sysShoppingCar));
        } else {
            this.subscribeToSaveResponse(this.sysShoppingCarService.create(this.sysShoppingCar));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysShoppingCar>>) {
        result.subscribe((res: HttpResponse<ISysShoppingCar>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
