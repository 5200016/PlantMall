import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysCoupon } from 'app/shared/model/sys-coupon.model';
import { AccountService } from 'app/core';
import { SysCouponService } from './sys-coupon.service';

@Component({
    selector: 'jhi-sys-coupon',
    templateUrl: './sys-coupon.component.html'
})
export class SysCouponComponent implements OnInit, OnDestroy {
    sysCoupons: ISysCoupon[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysCouponService: SysCouponService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysCouponService.query().subscribe(
            (res: HttpResponse<ISysCoupon[]>) => {
                this.sysCoupons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysCoupons();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysCoupon) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSysCoupons() {
        this.eventSubscriber = this.eventManager.subscribe('sysCouponListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
