import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysOrderProduct } from 'app/shared/model/sys-order-product.model';

@Component({
    selector: 'jhi-sys-order-product-detail',
    templateUrl: './sys-order-product-detail.component.html'
})
export class SysOrderProductDetailComponent implements OnInit {
    sysOrderProduct: ISysOrderProduct;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysOrderProduct }) => {
            this.sysOrderProduct = sysOrderProduct;
        });
    }

    previousState() {
        window.history.back();
    }
}
