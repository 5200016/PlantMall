import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysCouponUser } from 'app/shared/model/sys-coupon-user.model';

@Component({
    selector: 'jhi-sys-coupon-user-detail',
    templateUrl: './sys-coupon-user-detail.component.html'
})
export class SysCouponUserDetailComponent implements OnInit {
    sysCouponUser: ISysCouponUser;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCouponUser }) => {
            this.sysCouponUser = sysCouponUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
