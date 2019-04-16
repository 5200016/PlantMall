import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysCollection } from 'app/shared/model/sys-collection.model';
import { AccountService } from 'app/core';
import { SysCollectionService } from './sys-collection.service';

@Component({
    selector: 'jhi-sys-collection',
    templateUrl: './sys-collection.component.html'
})
export class SysCollectionComponent implements OnInit, OnDestroy {
    sysCollections: ISysCollection[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysCollectionService: SysCollectionService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysCollectionService.query().subscribe(
            (res: HttpResponse<ISysCollection[]>) => {
                this.sysCollections = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysCollections();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysCollection) {
        return item.id;
    }

    registerChangeInSysCollections() {
        this.eventSubscriber = this.eventManager.subscribe('sysCollectionListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
