import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysClassify } from 'app/shared/model/sys-classify.model';
import { AccountService } from 'app/core';
import { SysClassifyService } from './sys-classify.service';

@Component({
    selector: 'jhi-sys-classify',
    templateUrl: './sys-classify.component.html'
})
export class SysClassifyComponent implements OnInit, OnDestroy {
    sysClassifies: ISysClassify[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysClassifyService: SysClassifyService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysClassifyService.query().subscribe(
            (res: HttpResponse<ISysClassify[]>) => {
                this.sysClassifies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysClassifies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysClassify) {
        return item.id;
    }

    registerChangeInSysClassifies() {
        this.eventSubscriber = this.eventManager.subscribe('sysClassifyListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
