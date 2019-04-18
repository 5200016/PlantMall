import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysRecharge } from 'app/shared/model/sys-recharge.model';

@Component({
    selector: 'jhi-sys-recharge-detail',
    templateUrl: './sys-recharge-detail.component.html'
})
export class SysRechargeDetailComponent implements OnInit {
    sysRecharge: ISysRecharge;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysRecharge }) => {
            this.sysRecharge = sysRecharge;
        });
    }

    previousState() {
        window.history.back();
    }
}
