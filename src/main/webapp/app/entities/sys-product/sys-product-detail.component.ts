import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysProduct } from 'app/shared/model/sys-product.model';

@Component({
    selector: 'jhi-sys-product-detail',
    templateUrl: './sys-product-detail.component.html'
})
export class SysProductDetailComponent implements OnInit {
    sysProduct: ISysProduct;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysProduct }) => {
            this.sysProduct = sysProduct;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
