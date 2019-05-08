import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysCouponUser } from 'app/shared/model/sys-coupon-user.model';
import { AccountService } from 'app/core';
import { SysCouponUserService } from './sys-coupon-user.service';

@Component({
    selector: 'jhi-sys-coupon-user',
    templateUrl: './sys-coupon-user.component.html'
})
export class SysCouponUserComponent implements OnInit, OnDestroy {
    sysCouponUsers: ISysCouponUser[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysCouponUserService: SysCouponUserService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysCouponUserService.query().subscribe(
            (res: HttpResponse<ISysCouponUser[]>) => {
                this.sysCouponUsers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysCouponUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysCouponUser) {
        return item.id;
    }

    registerChangeInSysCouponUsers() {
        this.eventSubscriber = this.eventManager.subscribe('sysCouponUserListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
