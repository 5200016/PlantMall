import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysCustomerService } from 'app/shared/model/sys-customer-service.model';

@Component({
    selector: 'jhi-sys-customer-service-detail',
    templateUrl: './sys-customer-service-detail.component.html'
})
export class SysCustomerServiceDetailComponent implements OnInit {
    sysCustomerService: ISysCustomerService;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCustomerService }) => {
            this.sysCustomerService = sysCustomerService;
        });
    }

    previousState() {
        window.history.back();
    }
}
