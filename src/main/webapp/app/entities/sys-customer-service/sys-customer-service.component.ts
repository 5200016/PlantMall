import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysCustomerService } from 'app/shared/model/sys-customer-service.model';
import { AccountService } from 'app/core';
import { SysCustomerServiceService } from './sys-customer-service.service';

@Component({
    selector: 'jhi-sys-customer-service',
    templateUrl: './sys-customer-service.component.html'
})
export class SysCustomerServiceComponent implements OnInit, OnDestroy {
    sysCustomerServices: ISysCustomerService[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysCustomerServiceService: SysCustomerServiceService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysCustomerServiceService.query().subscribe(
            (res: HttpResponse<ISysCustomerService[]>) => {
                this.sysCustomerServices = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysCustomerServices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysCustomerService) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSysCustomerServices() {
        this.eventSubscriber = this.eventManager.subscribe('sysCustomerServiceListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
