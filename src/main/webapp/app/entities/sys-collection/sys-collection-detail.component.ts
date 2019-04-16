import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysCollection } from 'app/shared/model/sys-collection.model';

@Component({
    selector: 'jhi-sys-collection-detail',
    templateUrl: './sys-collection-detail.component.html'
})
export class SysCollectionDetailComponent implements OnInit {
    sysCollection: ISysCollection;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCollection }) => {
            this.sysCollection = sysCollection;
        });
    }

    previousState() {
        window.history.back();
    }
}
