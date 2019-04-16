import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysOrder } from 'app/shared/model/sys-order.model';

@Component({
    selector: 'jhi-sys-order-detail',
    templateUrl: './sys-order-detail.component.html'
})
export class SysOrderDetailComponent implements OnInit {
    sysOrder: ISysOrder;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysOrder }) => {
            this.sysOrder = sysOrder;
        });
    }

    previousState() {
        window.history.back();
    }
}
