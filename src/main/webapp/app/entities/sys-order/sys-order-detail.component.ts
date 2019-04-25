import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysOrder } from 'app/shared/model/sys-order.model';

@Component({
    selector: 'jhi-sys-order-detail',
    templateUrl: './sys-order-detail.component.html'
})
export class SysOrderDetailComponent implements OnInit {
    sysOrder: ISysOrder;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysOrder }) => {
            this.sysOrder = sysOrder;
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
