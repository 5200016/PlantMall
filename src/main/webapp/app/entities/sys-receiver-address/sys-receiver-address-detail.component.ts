import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';

@Component({
    selector: 'jhi-sys-receiver-address-detail',
    templateUrl: './sys-receiver-address-detail.component.html'
})
export class SysReceiverAddressDetailComponent implements OnInit {
    sysReceiverAddress: ISysReceiverAddress;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysReceiverAddress }) => {
            this.sysReceiverAddress = sysReceiverAddress;
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
