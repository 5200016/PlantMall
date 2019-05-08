import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysCouponProduct } from 'app/shared/model/sys-coupon-product.model';

@Component({
    selector: 'jhi-sys-coupon-product-detail',
    templateUrl: './sys-coupon-product-detail.component.html'
})
export class SysCouponProductDetailComponent implements OnInit {
    sysCouponProduct: ISysCouponProduct;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCouponProduct }) => {
            this.sysCouponProduct = sysCouponProduct;
        });
    }

    previousState() {
        window.history.back();
    }
}
