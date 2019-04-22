import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysPlantLog } from 'app/shared/model/sys-plant-log.model';
import { AccountService } from 'app/core';
import { SysPlantLogService } from './sys-plant-log.service';

@Component({
    selector: 'jhi-sys-plant-log',
    templateUrl: './sys-plant-log.component.html'
})
export class SysPlantLogComponent implements OnInit, OnDestroy {
    sysPlantLogs: ISysPlantLog[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysPlantLogService: SysPlantLogService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysPlantLogService.query().subscribe(
            (res: HttpResponse<ISysPlantLog[]>) => {
                this.sysPlantLogs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysPlantLogs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysPlantLog) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSysPlantLogs() {
        this.eventSubscriber = this.eventManager.subscribe('sysPlantLogListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
