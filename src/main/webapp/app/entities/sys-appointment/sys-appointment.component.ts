import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISysAppointment } from 'app/shared/model/sys-appointment.model';
import { AccountService } from 'app/core';
import { SysAppointmentService } from './sys-appointment.service';

@Component({
    selector: 'jhi-sys-appointment',
    templateUrl: './sys-appointment.component.html'
})
export class SysAppointmentComponent implements OnInit, OnDestroy {
    sysAppointments: ISysAppointment[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysAppointmentService: SysAppointmentService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysAppointmentService.query().subscribe(
            (res: HttpResponse<ISysAppointment[]>) => {
                this.sysAppointments = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysAppointments();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysAppointment) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInSysAppointments() {
        this.eventSubscriber = this.eventManager.subscribe('sysAppointmentListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
