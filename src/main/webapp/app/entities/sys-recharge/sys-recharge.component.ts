import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysRecharge } from 'app/shared/model/sys-recharge.model';
import { AccountService } from 'app/core';
import { SysRechargeService } from './sys-recharge.service';

@Component({
    selector: 'jhi-sys-recharge',
    templateUrl: './sys-recharge.component.html'
})
export class SysRechargeComponent implements OnInit, OnDestroy {
    sysRecharges: ISysRecharge[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysRechargeService: SysRechargeService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysRechargeService.query().subscribe(
            (res: HttpResponse<ISysRecharge[]>) => {
                this.sysRecharges = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysRecharges();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysRecharge) {
        return item.id;
    }

    registerChangeInSysRecharges() {
        this.eventSubscriber = this.eventManager.subscribe('sysRechargeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
