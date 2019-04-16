import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysUser } from 'app/shared/model/sys-user.model';
import { AccountService } from 'app/core';
import { SysUserService } from './sys-user.service';

@Component({
    selector: 'jhi-sys-user',
    templateUrl: './sys-user.component.html'
})
export class SysUserComponent implements OnInit, OnDestroy {
    sysUsers: ISysUser[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysUserService: SysUserService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysUsers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysUser) {
        return item.id;
    }

    registerChangeInSysUsers() {
        this.eventSubscriber = this.eventManager.subscribe('sysUserListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
