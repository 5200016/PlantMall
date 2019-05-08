import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';
import { AccountService } from 'app/core';
import { SysCouponClassifyService } from './sys-coupon-classify.service';

@Component({
    selector: 'jhi-sys-coupon-classify',
    templateUrl: './sys-coupon-classify.component.html'
})
export class SysCouponClassifyComponent implements OnInit, OnDestroy {
    sysCouponClassifies: ISysCouponClassify[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysCouponClassifyService: SysCouponClassifyService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysCouponClassifyService.query().subscribe(
            (res: HttpResponse<ISysCouponClassify[]>) => {
                this.sysCouponClassifies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysCouponClassifies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysCouponClassify) {
        return item.id;
    }

    registerChangeInSysCouponClassifies() {
        this.eventSubscriber = this.eventManager.subscribe('sysCouponClassifyListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
