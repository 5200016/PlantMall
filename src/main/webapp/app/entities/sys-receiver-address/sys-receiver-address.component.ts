import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';
import { AccountService } from 'app/core';
import { SysReceiverAddressService } from './sys-receiver-address.service';

@Component({
    selector: 'jhi-sys-receiver-address',
    templateUrl: './sys-receiver-address.component.html'
})
export class SysReceiverAddressComponent implements OnInit, OnDestroy {
    sysReceiverAddresses: ISysReceiverAddress[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysReceiverAddressService: SysReceiverAddressService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysReceiverAddressService.query().subscribe(
            (res: HttpResponse<ISysReceiverAddress[]>) => {
                this.sysReceiverAddresses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysReceiverAddresses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysReceiverAddress) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSysReceiverAddresses() {
        this.eventSubscriber = this.eventManager.subscribe('sysReceiverAddressListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
