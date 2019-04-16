import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysMemberLevel } from 'app/shared/model/sys-member-level.model';
import { AccountService } from 'app/core';
import { SysMemberLevelService } from './sys-member-level.service';

@Component({
    selector: 'jhi-sys-member-level',
    templateUrl: './sys-member-level.component.html'
})
export class SysMemberLevelComponent implements OnInit, OnDestroy {
    sysMemberLevels: ISysMemberLevel[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysMemberLevelService: SysMemberLevelService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysMemberLevelService.query().subscribe(
            (res: HttpResponse<ISysMemberLevel[]>) => {
                this.sysMemberLevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysMemberLevels();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysMemberLevel) {
        return item.id;
    }

    registerChangeInSysMemberLevels() {
        this.eventSubscriber = this.eventManager.subscribe('sysMemberLevelListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
