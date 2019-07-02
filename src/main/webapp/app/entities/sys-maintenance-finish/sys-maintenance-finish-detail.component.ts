import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';

@Component({
    selector: 'jhi-sys-maintenance-finish-detail',
    templateUrl: './sys-maintenance-finish-detail.component.html'
})
export class SysMaintenanceFinishDetailComponent implements OnInit {
    sysMaintenanceFinish: ISysMaintenanceFinish;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMaintenanceFinish }) => {
            this.sysMaintenanceFinish = sysMaintenanceFinish;
        });
    }

    previousState() {
        window.history.back();
    }
}
