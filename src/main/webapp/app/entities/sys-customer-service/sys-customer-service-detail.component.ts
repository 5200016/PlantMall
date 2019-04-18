import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysCustomerService } from 'app/shared/model/sys-customer-service.model';

@Component({
    selector: 'jhi-sys-customer-service-detail',
    templateUrl: './sys-customer-service-detail.component.html'
})
export class SysCustomerServiceDetailComponent implements OnInit {
    sysCustomerService: ISysCustomerService;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCustomerService }) => {
            this.sysCustomerService = sysCustomerService;
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
