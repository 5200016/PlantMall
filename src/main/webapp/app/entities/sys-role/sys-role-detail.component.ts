import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysRole } from 'app/shared/model/sys-role.model';

@Component({
    selector: 'jhi-sys-role-detail',
    templateUrl: './sys-role-detail.component.html'
})
export class SysRoleDetailComponent implements OnInit {
    sysRole: ISysRole;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysRole }) => {
            this.sysRole = sysRole;
        });
    }

    previousState() {
        window.history.back();
    }
}
