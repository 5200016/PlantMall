import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysForm } from 'app/shared/model/sys-form.model';
import { AccountService } from 'app/core';
import { SysFormService } from './sys-form.service';

@Component({
    selector: 'jhi-sys-form',
    templateUrl: './sys-form.component.html'
})
export class SysFormComponent implements OnInit, OnDestroy {
    sysForms: ISysForm[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysFormService: SysFormService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysFormService.query().subscribe(
            (res: HttpResponse<ISysForm[]>) => {
                this.sysForms = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysForms();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysForm) {
        return item.id;
    }

    registerChangeInSysForms() {
        this.eventSubscriber = this.eventManager.subscribe('sysFormListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
