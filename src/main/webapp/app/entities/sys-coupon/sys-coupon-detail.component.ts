import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysCoupon } from 'app/shared/model/sys-coupon.model';

@Component({
    selector: 'jhi-sys-coupon-detail',
    templateUrl: './sys-coupon-detail.component.html'
})
export class SysCouponDetailComponent implements OnInit {
    sysCoupon: ISysCoupon;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCoupon }) => {
            this.sysCoupon = sysCoupon;
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
