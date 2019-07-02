import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';
import { AccountService } from 'app/core';
import { SysMaintenanceFinishService } from './sys-maintenance-finish.service';

@Component({
    selector: 'jhi-sys-maintenance-finish',
    templateUrl: './sys-maintenance-finish.component.html'
})
export class SysMaintenanceFinishComponent implements OnInit, OnDestroy {
    sysMaintenanceFinishes: ISysMaintenanceFinish[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysMaintenanceFinishService: SysMaintenanceFinishService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysMaintenanceFinishService.query().subscribe(
            (res: HttpResponse<ISysMaintenanceFinish[]>) => {
                this.sysMaintenanceFinishes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysMaintenanceFinishes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysMaintenanceFinish) {
        return item.id;
    }

    registerChangeInSysMaintenanceFinishes() {
        this.eventSubscriber = this.eventManager.subscribe('sysMaintenanceFinishListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
