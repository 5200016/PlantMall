import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysProductImage } from 'app/shared/model/sys-product-image.model';

@Component({
    selector: 'jhi-sys-product-image-detail',
    templateUrl: './sys-product-image-detail.component.html'
})
export class SysProductImageDetailComponent implements OnInit {
    sysProductImage: ISysProductImage;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysProductImage }) => {
            this.sysProductImage = sysProductImage;
        });
    }

    previousState() {
        window.history.back();
    }
}
