import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISysCouponProduct } from 'app/shared/model/sys-coupon-product.model';
import { AccountService } from 'app/core';
import { SysCouponProductService } from './sys-coupon-product.service';

@Component({
    selector: 'jhi-sys-coupon-product',
    templateUrl: './sys-coupon-product.component.html'
})
export class SysCouponProductComponent implements OnInit, OnDestroy {
    sysCouponProducts: ISysCouponProduct[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected sysCouponProductService: SysCouponProductService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.sysCouponProductService.query().subscribe(
            (res: HttpResponse<ISysCouponProduct[]>) => {
                this.sysCouponProducts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSysCouponProducts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISysCouponProduct) {
        return item.id;
    }

    registerChangeInSysCouponProducts() {
        this.eventSubscriber = this.eventManager.subscribe('sysCouponProductListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
