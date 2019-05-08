import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';

@Component({
    selector: 'jhi-sys-coupon-classify-detail',
    templateUrl: './sys-coupon-classify-detail.component.html'
})
export class SysCouponClassifyDetailComponent implements OnInit {
    sysCouponClassify: ISysCouponClassify;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCouponClassify }) => {
            this.sysCouponClassify = sysCouponClassify;
        });
    }

    previousState() {
        window.history.back();
    }
}
