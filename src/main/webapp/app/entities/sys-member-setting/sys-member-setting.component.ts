import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysMemberSetting } from 'app/shared/model/sys-member-setting.model';
import { AccountService } from 'app/core';
import { SysMemberSettingService } from './sys-member-setting.service';

@Component({
    selector: 'jhi-sys-member-setting',
    templateUrl: './sys-member-setting.component.html'
})
export class SysMemberSettingComponent implements OnInit, OnDestroy {
    sysMemberSettings: ISysMemberSetting[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysMemberSettingService: SysMemberSettingService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysMemberSettingService.query().subscribe(
            (res: HttpResponse<ISysMemberSetting[]>) => {
                this.sysMemberSettings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysMemberSettings();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysMemberSetting) {
        return item.id;
    }

    registerChangeInSysMemberSettings() {
        this.eventSubscriber = this.eventManager.subscribe('sysMemberSettingListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
