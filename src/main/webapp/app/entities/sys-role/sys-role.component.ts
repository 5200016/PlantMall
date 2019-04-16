import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysRole } from 'app/shared/model/sys-role.model';
import { AccountService } from 'app/core';
import { SysRoleService } from './sys-role.service';

@Component({
    selector: 'jhi-sys-role',
    templateUrl: './sys-role.component.html'
})
export class SysRoleComponent implements OnInit, OnDestroy {
    sysRoles: ISysRole[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysRoleService: SysRoleService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysRoleService.query().subscribe(
            (res: HttpResponse<ISysRole[]>) => {
                this.sysRoles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysRoles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysRole) {
        return item.id;
    }

    registerChangeInSysRoles() {
        this.eventSubscriber = this.eventManager.subscribe('sysRoleListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
