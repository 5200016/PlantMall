import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysAdmin } from 'app/shared/model/sys-admin.model';
import { AccountService } from 'app/core';
import { SysAdminService } from './sys-admin.service';

@Component({
    selector: 'jhi-sys-admin',
    templateUrl: './sys-admin.component.html'
})
export class SysAdminComponent implements OnInit, OnDestroy {
    sysAdmins: ISysAdmin[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysAdminService: SysAdminService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysAdminService.query().subscribe(
            (res: HttpResponse<ISysAdmin[]>) => {
                this.sysAdmins = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysAdmins();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysAdmin) {
        return item.id;
    }

    registerChangeInSysAdmins() {
        this.eventSubscriber = this.eventManager.subscribe('sysAdminListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
