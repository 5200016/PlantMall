import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysOrder } from 'app/shared/model/sys-order.model';
import { AccountService } from 'app/core';
import { SysOrderService } from './sys-order.service';

@Component({
    selector: 'jhi-sys-order',
    templateUrl: './sys-order.component.html'
})
export class SysOrderComponent implements OnInit, OnDestroy {
    sysOrders: ISysOrder[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysOrderService: SysOrderService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysOrderService.query().subscribe(
            (res: HttpResponse<ISysOrder[]>) => {
                this.sysOrders = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysOrders();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysOrder) {
        return item.id;
    }

    registerChangeInSysOrders() {
        this.eventSubscriber = this.eventManager.subscribe('sysOrderListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
