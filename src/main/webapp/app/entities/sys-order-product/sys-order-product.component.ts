import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysOrderProduct } from 'app/shared/model/sys-order-product.model';
import { AccountService } from 'app/core';
import { SysOrderProductService } from './sys-order-product.service';

@Component({
    selector: 'jhi-sys-order-product',
    templateUrl: './sys-order-product.component.html'
})
export class SysOrderProductComponent implements OnInit, OnDestroy {
    sysOrderProducts: ISysOrderProduct[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysOrderProductService: SysOrderProductService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysOrderProductService.query().subscribe(
            (res: HttpResponse<ISysOrderProduct[]>) => {
                this.sysOrderProducts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysOrderProducts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysOrderProduct) {
        return item.id;
    }

    registerChangeInSysOrderProducts() {
        this.eventSubscriber = this.eventManager.subscribe('sysOrderProductListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
