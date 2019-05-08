import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysCouponUser } from 'app/shared/model/sys-coupon-user.model';
import { SysCouponUserService } from './sys-coupon-user.service';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';
import { ISysCouponProduct } from 'app/shared/model/sys-coupon-product.model';
import { SysCouponProductService } from 'app/entities/sys-coupon-product';
import { ISysCoupon } from 'app/shared/model/sys-coupon.model';
import { SysCouponService } from 'app/entities/sys-coupon';

@Component({
    selector: 'jhi-sys-coupon-user-update',
    templateUrl: './sys-coupon-user-update.component.html'
})
export class SysCouponUserUpdateComponent implements OnInit {
    sysCouponUser: ISysCouponUser;
    isSaving: boolean;

    sysusers: ISysUser[];

    syscouponproducts: ISysCouponProduct[];

    syscoupons: ISysCoupon[];
    createTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysCouponUserService: SysCouponUserService,
        protected sysUserService: SysUserService,
        protected sysCouponProductService: SysCouponProductService,
        protected sysCouponService: SysCouponService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysCouponUser }) => {
            this.sysCouponUser = sysCouponUser;
            this.createTime = this.sysCouponUser.createTime != null ? this.sysCouponUser.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysCouponUser.updateTime != null ? this.sysCouponUser.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysCouponProductService.query().subscribe(
            (res: HttpResponse<ISysCouponProduct[]>) => {
                this.syscouponproducts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sysCouponService.query().subscribe(
            (res: HttpResponse<ISysCoupon[]>) => {
                this.syscoupons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysCouponUser.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysCouponUser.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysCouponUser.id !== undefined) {
            this.subscribeToSaveResponse(this.sysCouponUserService.update(this.sysCouponUser));
        } else {
            this.subscribeToSaveResponse(this.sysCouponUserService.create(this.sysCouponUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysCouponUser>>) {
        result.subscribe((res: HttpResponse<ISysCouponUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSysCouponProductById(index: number, item: ISysCouponProduct) {
        return item.id;
    }

    trackSysCouponById(index: number, item: ISysCoupon) {
        return item.id;
    }
}
