import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';
import { AccountService } from 'app/core';
import { SysMaintenancePersonnelService } from './sys-maintenance-personnel.service';

@Component({
    selector: 'jhi-sys-maintenance-personnel',
    templateUrl: './sys-maintenance-personnel.component.html'
})
export class SysMaintenancePersonnelComponent implements OnInit, OnDestroy {
    sysMaintenancePersonnels: ISysMaintenancePersonnel[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysMaintenancePersonnelService: SysMaintenancePersonnelService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysMaintenancePersonnelService.query().subscribe(
            (res: HttpResponse<ISysMaintenancePersonnel[]>) => {
                this.sysMaintenancePersonnels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysMaintenancePersonnels();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysMaintenancePersonnel) {
        return item.id;
    }

    registerChangeInSysMaintenancePersonnels() {
        this.eventSubscriber = this.eventManager.subscribe('sysMaintenancePersonnelListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
