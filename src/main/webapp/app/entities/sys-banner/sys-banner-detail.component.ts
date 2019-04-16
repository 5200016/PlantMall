import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysBanner } from 'app/shared/model/sys-banner.model';

@Component({
    selector: 'jhi-sys-banner-detail',
    templateUrl: './sys-banner-detail.component.html'
})
export class SysBannerDetailComponent implements OnInit {
    sysBanner: ISysBanner;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysBanner }) => {
            this.sysBanner = sysBanner;
        });
    }

    previousState() {
        window.history.back();
    }
}
