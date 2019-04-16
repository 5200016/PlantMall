import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysClassify } from 'app/shared/model/sys-classify.model';

@Component({
    selector: 'jhi-sys-classify-detail',
    templateUrl: './sys-classify-detail.component.html'
})
export class SysClassifyDetailComponent implements OnInit {
    sysClassify: ISysClassify;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysClassify }) => {
            this.sysClassify = sysClassify;
        });
    }

    previousState() {
        window.history.back();
    }
}
