import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';
import { AccountService } from 'app/core';
import { SysShoppingCarService } from './sys-shopping-car.service';

@Component({
    selector: 'jhi-sys-shopping-car',
    templateUrl: './sys-shopping-car.component.html'
})
export class SysShoppingCarComponent implements OnInit, OnDestroy {
    sysShoppingCars: ISysShoppingCar[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysShoppingCarService: SysShoppingCarService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysShoppingCarService.query().subscribe(
            (res: HttpResponse<ISysShoppingCar[]>) => {
                this.sysShoppingCars = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysShoppingCars();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysShoppingCar) {
        return item.id;
    }

    registerChangeInSysShoppingCars() {
        this.eventSubscriber = this.eventManager.subscribe('sysShoppingCarListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
