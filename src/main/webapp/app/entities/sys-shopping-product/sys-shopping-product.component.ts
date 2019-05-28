import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';
import { AccountService } from 'app/core';
import { SysShoppingProductService } from './sys-shopping-product.service';

@Component({
    selector: 'jhi-sys-shopping-product',
    templateUrl: './sys-shopping-product.component.html'
})
export class SysShoppingProductComponent implements OnInit, OnDestroy {
    sysShoppingProducts: ISysShoppingProduct[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysShoppingProductService: SysShoppingProductService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysShoppingProductService.query().subscribe(
            (res: HttpResponse<ISysShoppingProduct[]>) => {
                this.sysShoppingProducts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysShoppingProducts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysShoppingProduct) {
        return item.id;
    }

    registerChangeInSysShoppingProducts() {
        this.eventSubscriber = this.eventManager.subscribe('sysShoppingProductListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
