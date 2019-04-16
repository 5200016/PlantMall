import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysBanner } from 'app/shared/model/sys-banner.model';
import { AccountService } from 'app/core';
import { SysBannerService } from './sys-banner.service';

@Component({
    selector: 'jhi-sys-banner',
    templateUrl: './sys-banner.component.html'
})
export class SysBannerComponent implements OnInit, OnDestroy {
    sysBanners: ISysBanner[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysBannerService: SysBannerService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysBannerService.query().subscribe(
            (res: HttpResponse<ISysBanner[]>) => {
                this.sysBanners = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysBanners();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysBanner) {
        return item.id;
    }

    registerChangeInSysBanners() {
        this.eventSubscriber = this.eventManager.subscribe('sysBannerListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
