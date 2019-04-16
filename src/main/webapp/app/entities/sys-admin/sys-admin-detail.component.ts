import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysAdmin } from 'app/shared/model/sys-admin.model';

@Component({
    selector: 'jhi-sys-admin-detail',
    templateUrl: './sys-admin-detail.component.html'
})
export class SysAdminDetailComponent implements OnInit {
    sysAdmin: ISysAdmin;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysAdmin }) => {
            this.sysAdmin = sysAdmin;
        });
    }

    previousState() {
        window.history.back();
    }
}
