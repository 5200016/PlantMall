import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';

@Component({
    selector: 'jhi-sys-shopping-product-detail',
    templateUrl: './sys-shopping-product-detail.component.html'
})
export class SysShoppingProductDetailComponent implements OnInit {
    sysShoppingProduct: ISysShoppingProduct;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysShoppingProduct }) => {
            this.sysShoppingProduct = sysShoppingProduct;
        });
    }

    previousState() {
        window.history.back();
    }
}
